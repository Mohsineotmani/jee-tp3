package org.mo.tp2.web;


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
            throws IOException {
        HttpSession session = req.getSession();
        String user = (String) session.getAttribute("user");

        if (user == null) {

            //ist not depend on the physical path its depend on Servlet(/path)
            resp.sendRedirect("/tp2/login");
            return;
        }

        resp.setContentType("text/html");
        resp.getWriter().println("<h1>Dashboard de " + user + "</h1> ," + req.getContextPath());
        // deetion the settion
        // logout buttun
        resp.getWriter().println(
                "<form action='" + req.getContextPath() + "/logout' method='POST'>" +
                        "<button type='submit'>Logout</button>" +
                        "</form>"
        );


    }
}
