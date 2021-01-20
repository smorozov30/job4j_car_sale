package ru.job4j.car_sale.servlet;

import ru.job4j.car_sale.model.User;
import ru.job4j.car_sale.store.HibernateStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("reg.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User newUser = User.of(name, email, password);
        if (HibernateStore.instOf().getUser(email) == null) {
            HibernateStore.instOf().addUser(newUser);
            resp.sendRedirect(req.getContextPath() + "/login.html");
        } else {
            doGet(req, resp);
        }
    }
}
