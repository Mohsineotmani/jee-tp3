package org.mo.tp2;


import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException {

        response.setContentType("text/plain");
        response.getWriter().println("Hello from Java!");
        response.setHeader(
                "Access-Control-Allow-Origin",
                "http://localhost:5173"
        );
    }
}