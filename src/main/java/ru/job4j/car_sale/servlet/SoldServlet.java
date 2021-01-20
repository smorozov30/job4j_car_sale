package ru.job4j.car_sale.servlet;

import ru.job4j.car_sale.store.HibernateStore;
import ru.job4j.car_sale.store.MemStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SoldServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        MemStore.instOf().setSoldById(id);
    }
}
