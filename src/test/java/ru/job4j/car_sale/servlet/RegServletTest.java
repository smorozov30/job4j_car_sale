package ru.job4j.car_sale.servlet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.job4j.car_sale.store.HibernateStore;
import ru.job4j.car_sale.store.MemStore;
import ru.job4j.car_sale.store.Store;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(HibernateStore.class)
public class RegServletTest {

    @Test
    public void doGet() throws ServletException, IOException {
        Store store = MemStore.instOf();
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        PowerMockito.mockStatic(HibernateStore.class);
        when(HibernateStore.instOf()).thenReturn(store);
        when(req.getRequestDispatcher("reg.html")).thenReturn(dispatcher);

        new RegServlet().doGet(req, resp);

        Mockito.verify(req).getRequestDispatcher("reg.html");
        Mockito.verify(dispatcher).forward(req, resp);
    }

    @Test
    public void doPost() throws ServletException, IOException {
        Store memStore = MemStore.instOf();
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);

        PowerMockito.mockStatic(HibernateStore.class);
        when(HibernateStore.instOf()).thenReturn(memStore);
        when(req.getParameter("name")).thenReturn("sergey");
        when(req.getParameter("email")).thenReturn("email@email");
        when(req.getParameter("password")).thenReturn("password");

        new RegServlet().doPost(req, resp);
        assertNotNull(memStore.getUser("email@email"));
    }
}