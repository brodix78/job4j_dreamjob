package ru.job4j.dream.servlet;

import ru.job4j.dream.store.PsqlStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class DownloadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.valueOf(req.getParameter("photoId"));
        String link = PsqlStore.instOf().photoById(id);
        if (link != null) {
            String name = link.substring(link.lastIndexOf(File.separator) + 1);
            System.out.println(name);
            resp.setContentType("name=" + name);
            resp.setContentType("image");
            resp.setHeader("Content-Disposition", "attachment; filename=\"" + name + "\"");


            File file = new File(link);
            try (FileInputStream in = new FileInputStream(file)) {
                resp.getOutputStream().write(in.readAllBytes());
            }
        }
    }
}
