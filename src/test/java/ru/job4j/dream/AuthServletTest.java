package ru.job4j.dream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.job4j.dream.model.User;
import ru.job4j.dream.servlet.AuthServlet;
import ru.job4j.dream.store.FakeStore;
import ru.job4j.dream.store.PsqlStore;
import ru.job4j.dream.store.Store;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RunWith(PowerMockRunner.class)
@PrepareForTest(PsqlStore.class)

public class AuthServletTest {

    @Test
    public void whenAuthCorrectUser() throws ServletException, IOException {
        User user = new User();
        Store store = new FakeStore(user);
        PowerMockito.mockStatic(PsqlStore.class);
        HttpSession session = Mockito.spy(HttpSession.class);
        Mockito.when(PsqlStore.instOf()).thenReturn(store);
        HttpServletRequest request = Mockito.spy(HttpServletRequest.class);
        HttpServletResponse response = Mockito.spy(HttpServletResponse.class);
        Mockito.when(request.getParameter("email")).thenReturn("email@mail.com");
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(request.getParameter("password")).thenReturn("password");
        new AuthServlet().doPost(request, response);
        Mockito.verify(session).setAttribute("user", user);
    }
}
