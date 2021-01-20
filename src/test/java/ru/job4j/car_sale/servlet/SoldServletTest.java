package ru.job4j.car_sale.servlet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.job4j.car_sale.model.Ad;
import ru.job4j.car_sale.store.HibernateStore;
import ru.job4j.car_sale.store.MemStore;
import ru.job4j.car_sale.store.Store;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(HibernateStore.class)
public class SoldServletTest {

    @Test
    public void doPost() throws ServletException, IOException {
        Store memStore = MemStore.instOf();
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);

        PowerMockito.mockStatic(HibernateStore.class);
        when(HibernateStore.instOf()).thenReturn(memStore);
        when(req.getParameter("id")).thenReturn("1");

        new SoldServlet().doPost(req, resp);
        long expectedSize = memStore.getAds().stream().filter(Ad::isSold).count();
        assertThat(expectedSize, is(1L));
    }
}