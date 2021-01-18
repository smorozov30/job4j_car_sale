package ru.job4j.car_sale.servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.car_sale.model.Model;
import ru.job4j.car_sale.store.HibernateStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ModelServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.excludeFieldsWithoutExposeAnnotation().create();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(req.getParameter("id"));
        List<Model> models = HibernateStore.instOf().getModelsByMarkId(id);
        String jsonResp = gson.toJson(models);
        resp.getWriter().write(jsonResp);
    }
}
