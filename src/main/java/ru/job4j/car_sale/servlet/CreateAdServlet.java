package ru.job4j.car_sale.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import ru.job4j.car_sale.model.*;
import ru.job4j.car_sale.store.HibernateStore;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class CreateAdServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("add.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletContext servletContext = this.getServletConfig().getServletContext();
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(repository);
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            List<FileItem> items = upload.parseRequest(req);
            String description = new String(items.get(0).get(), StandardCharsets.UTF_8);
            String makeName = new String(items.get(1).get(), StandardCharsets.UTF_8);
            String modelName = new String(items.get(2).get(), StandardCharsets.UTF_8);
            String bodyName = new String(items.get(3).get(), StandardCharsets.UTF_8);
            String engineName = new String(items.get(4).get(), StandardCharsets.UTF_8);
            String transmissionName = new String(items.get(5).get(), StandardCharsets.UTF_8);
            Car car = HibernateStore.instOf().designCar(makeName, modelName, bodyName, engineName, transmissionName);
            Ad ad = Ad.of(car.getId(), description, car, false);
            File folder = new File("\\images");
            if (!folder.exists()) {
                folder.mkdir();
            }
            for (FileItem item : items) {
                if (!item.isFormField()) {
                    String name = item.getName();
                    File file = new File(folder + File.separator + name);
                    try (FileOutputStream out = new FileOutputStream(file)) {
                        out.write(item.getInputStream().readAllBytes());
                    }
                    ad.addPhoto(name);
                }
            }
            HibernateStore.instOf().addAd(ad);
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        doGet(req, resp);
    }
}
