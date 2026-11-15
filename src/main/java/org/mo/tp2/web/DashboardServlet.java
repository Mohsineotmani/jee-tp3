package org.mo.tp2.web;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException  , ServletException {

        HttpSession session = req.getSession();
        String user = (String) session.getAttribute("user");

        if (user == null) {
            resp.sendRedirect("/tp2/login");
            return;
        }

        req.setAttribute("user" , user);

        req.getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(req,resp);


    }
}
