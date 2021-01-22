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
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MainAdsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.excludeFieldsWithoutExposeAnnotation().create();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        String filter = req.getParameter("filter");
        List<Ad> ads = getAdsByCondition(HibernateStore.instOf().getAds(), filter);
        String jsonResp = gson.toJson(ads);
        resp.getWriter().write(jsonResp);
    }

    private List<Ad> getAdsByCondition(List<Ad> ads, String filter) {
        Predicate<Ad> condition = null;
        switch (filter) {
            case "all":
                condition = ad -> true;
                break;
            case "lastDay":
                condition = ad -> {
                    Date today = new Date();
                    today.setHours(0);
                    today.setMinutes(0);
                    today.setSeconds(0);
                    return ad.getCreated().after(today);
                };
                break;
            case "onlyPhoto":
                condition = ad -> !ad.getPhoto().isEmpty();
                break;
            default:
                condition = ad -> ad.getCar().getMake().getName().equals(filter);
        }
        return ads.stream().filter(condition).collect(Collectors.toList());
    }
}