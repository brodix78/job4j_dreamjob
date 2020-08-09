package ru.job4j.dream.servlet;

import ru.job4j.dream.model.User;
import ru.job4j.dream.store.PsqlStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        if (email != null && email.length() >= 5 && email.contains("@")
                && password != null && password.length() > 4
                && name != null && name.length() >= 1) {
            User user = new User();
            user.setName(name);
            user.setEmail(email);
            user.setPassword(password);
            PsqlStore.instOf().createUser(user);
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        } else {
            req.setAttribute("error", "ошибка заполнения полей");
            req.getRequestDispatcher("reg.jsp").forward(req, resp);
        }
    }
}
