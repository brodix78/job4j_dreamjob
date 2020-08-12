package ru.job4j.dream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.job4j.dream.model.User;
import ru.job4j.dream.servlet.RegServlet;
import ru.job4j.dream.store.FakeStore;
import ru.job4j.dream.store.PsqlStore;
import ru.job4j.dream.store.Store;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RunWith(PowerMockRunner.class)
@PrepareForTest(PsqlStore.class)
public class RegServletTest {

    @Test
    public void whenSendFullDataWhenAddUser() throws ServletException, IOException {
        User user = new User();
        user.setName("Name");
        user.setEmail("email@email.com");
        user.setPassword("password");
        PowerMockito.mockStatic(PsqlStore.class);
        Store store = Mockito.spy(FakeStore.class);
        Mockito.when(PsqlStore.instOf()).thenReturn(store);
        HttpServletResponse response = Mockito.spy(HttpServletResponse.class);
        HttpServletRequest request = Mockito.spy(HttpServletRequest.class);
        Mockito.when(request.getParameter("name")).thenReturn("Name");
        Mockito.when(request.getParameter("email")).thenReturn("email@email.com");
        Mockito.when(request.getParameter("password")).thenReturn("password");
        RequestDispatcher rd = Mockito.spy(RequestDispatcher.class);
        Mockito.when(request.getRequestDispatcher("login.jsp")).thenReturn(rd);
        new RegServlet().doPost(request, response);
        Mockito.verify(store).createUser(user);
    }

    @Test
    public void whenSendNotFullDataWhenReturnError() throws ServletException, IOException {
        PowerMockito.mockStatic(PsqlStore.class);
        Store store = Mockito.spy(FakeStore.class);
        Mockito.when(PsqlStore.instOf()).thenReturn(store);
        HttpServletResponse response = Mockito.spy(HttpServletResponse.class);
        HttpServletRequest request = Mockito.spy(HttpServletRequest.class);
        Mockito.when(request.getParameter("name")).thenReturn("Name");
        Mockito.when(request.getParameter("email")).thenReturn("email");
        Mockito.when(request.getParameter("password")).thenReturn("password");
        RequestDispatcher rd = Mockito.spy(RequestDispatcher.class);
        Mockito.when(request.getRequestDispatcher("reg.jsp")).thenReturn(rd);
        new RegServlet().doPost(request, response);
        Mockito.verify(request).setAttribute("error", "ошибка заполнения полей");
    }

}
