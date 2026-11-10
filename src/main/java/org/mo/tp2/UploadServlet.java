package org.mo.tp2;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/upload")
@MultipartConfig
public class UploadServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException , ServletException {
        // Afficher le formulaire
        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().println("<h1> upload  </h1>");

        req.getRequestDispatcher("/upload.html").forward(req,resp);

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {

        Part filePart = req.getPart("file");
        String fileName = filePart.getSubmittedFileName();

        String uploadPath = "C:\\2_eniad\\tp_jee\\tp2\\uploads\\" + fileName;
        filePart.write(uploadPath);

        resp.setContentType("text/html");
        resp.getWriter().println("Fichier uploade: " + fileName);
    }
}

