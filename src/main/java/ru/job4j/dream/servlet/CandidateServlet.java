package ru.job4j.dream.servlet;

import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.store.PsqlStore;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CandidateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("candidates", PsqlStore.instOf().findAllCandidates());
        req.getRequestDispatcher("candidates.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        if ("delete".equals(req.getParameter("action"))) {
            doDelete(req, resp);
        } else {
            if (!req.getParameter("name").replace(" ", "").equals("")) {
                Candidate candidate = new Candidate(Integer.valueOf(req.getParameter("id")),
                        req.getParameter("name"));
                candidate.setPhotoId(Integer.valueOf(req.getParameter("photoId")));
                PsqlStore.instOf().saveCandidate(candidate);
            }
            resp.sendRedirect(req.getContextPath() + "/candidates.do");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        PsqlStore.instOf().removeCandidate(Integer.valueOf(req.getParameter("id")));
        resp.sendRedirect(req.getContextPath() + "/candidates.do");
    }
}
