package ru.job4j.car_sale.servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.car_sale.model.Ad;
import ru.job4j.car_sale.store.HibernateStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MainAdsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.excludeFieldsWithoutExposeAnnotation().create();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        String filter = req.getParameter("filter");
        List<Ad> ads = getAdsByFilter(filter);
        String jsonResp = gson.toJson(ads);
        resp.getWriter().write(jsonResp);
    }

    private List<Ad> getAdsByFilter(String filter) {
        return switch (filter) {
            case "all" -> HibernateStore.instOf().getAds();
            case "lastDay" -> HibernateStore.instOf().getAdsForLastDay();
            case "onlyPhoto" -> HibernateStore.instOf().getAdsWithPhoto();
            default -> HibernateStore.instOf().getAdsByMake(filter);
        };
    }
}