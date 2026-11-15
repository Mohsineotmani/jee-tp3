package org.mo.tp2.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException , ServletException {


        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req,resp);
//        req.getRequestDispatcher("/login.html").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // Vérifier (exemple simplifié)
        if ("admin".equals(username) && "password".equals(password)) {
            HttpSession session = req.getSession();
            session.setAttribute("user", username);
            session.setMaxInactiveInterval(30 * 60); // 30 min
            resp.sendRedirect("/tp2/dashboard");
        } else {
            resp.sendRedirect("/tp2/login?erreur=1");
//            resp.sendRedirect("/tp2/dashboard");
        }
    }
}