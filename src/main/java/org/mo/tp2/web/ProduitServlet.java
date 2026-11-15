package org.mo.tp2.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.mo.tp2.model.Produit;
import org.mo.tp2.model.ProduitDAO;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/produits")
public class ProduitServlet extends HttpServlet {
    private static final String FORM = "/WEB-INF/views/produits/form.jsp";
    private static final String LIST = "/WEB-INF/views/produits/list.jsp";
    private ProduitDAO dao;

    @Override
    public void init() {
        // TODO : récupérer le DAO dans le ServletContext
        dao = (ProduitDAO) getServletContext().getAttribute("produitDAO");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        // TODO : selon action -> lister / formulaire vide / formulaire pré-rempli
        // id invalide ou inconnu -> resp.sendError(404)
        if (action == null || action.isBlank() || action.equals("list")) {
            req.setAttribute("produit", new Produit());
            req.getRequestDispatcher(FORM).forward(req, resp);

        }
        else if ("edit".equals(action)) {
            Long id = Long.valueOf(req.getParameter("id").trim());
            Produit p = dao.findById(id);
            if (p == null) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
            }
            req.setAttribute("produit", p);
            req.getRequestDispatcher(FORM).forward(req, resp);}
        else {
            List<Produit> produits = dao.findAll();
            req.setAttribute("produits", produits);
            req.getRequestDispatcher(LIST).forward(req, resp);
        }
    }

    // ------------------------------------------------------------ utilitaires

    private static String val(String s) {
        return s == null ? "" : s.trim();
    }

    private static void flash(HttpServletRequest req, String message) {
        req.getSession().setAttribute("flash", message);
    }

    // ------------------------------------------------------------ POST
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        // ---- Suppression
        if ("delete".equals(req.getParameter("action"))) {
            Long id = Long.valueOf(req.getParameter("id").trim());
            Produit p = dao.findById(id);
            if (p == null) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
            }
            dao.delete(id);
            flash(req, "Produit « " + p.getNom() + " » supprimé.");
            resp.sendRedirect(req.getContextPath() + "/produits");
            return;
        }

        // ---- Creation / modification
        String idStr = val(req.getParameter("id"));
        String nom = val(req.getParameter("nom"));
        String description = val(req.getParameter("description"));
        String prixStr = val(req.getParameter("prix"));
        String stockStr = val(req.getParameter("stock"));

        Long id = null;
        if (!idStr.isEmpty()) {
            id = Long.valueOf(req.getParameter("id").trim());
            if (dao.findById(id) == null) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
            }
        }

        Map<String, String> erreurs = new LinkedHashMap<>();

        // nom : obligatoire, 2 a 50 caracteres
        if (nom.isEmpty()) {
            erreurs.put("nom", "Le nom est obligatoire.");
        } else if (nom.length() < 2 || nom.length() > 50) {
            erreurs.put("nom", "Le nom doit contenir entre 2 et 50 caractères.");
        }

        // prix : obligatoire, decimal strictement positif
        BigDecimal prix = null;
        if (prixStr.isEmpty()) {
            erreurs.put("prix", "Le prix est obligatoire.");
        } else {
            try {
                prix = new BigDecimal(prixStr.replace(',', '.'));
                if (prix.signum() <= 0) {
                    erreurs.put("prix", "Le prix doit être strictement positif.");
                } else {
                    prix = prix.setScale(2, RoundingMode.HALF_UP);
                }
            } catch (NumberFormatException e) {
                erreurs.put("prix", "Le prix doit être un nombre décimal (ex. 99.90).");
            }
        }

        // stock : entier >= 0 (vide = 0)
        int stock = 0;
        if (!stockStr.isEmpty()) {
            try {
                stock = Integer.parseInt(stockStr);
                if (stock < 0) {
                    erreurs.put("stock", "Le stock doit être un entier positif ou nul.");
                }
            } catch (NumberFormatException e) {
                erreurs.put("stock", "Le stock doit être un nombre entier.");
            }
        }

        // description : facultative, 255 caracteres max
        if (description.length() > 255) {
            erreurs.put("description", "La description ne doit pas dépasser 255 caractères.");
        }

        Produit produit = new Produit(id, nom, description, prix, stock);

        if (!erreurs.isEmpty()) {
            req.setAttribute("erreurs", erreurs);
            req.setAttribute("produit", produit);
            // valeurs brutes saisies (un prix invalide ne tient pas dans un BigDecimal)
            req.setAttribute("prixSaisi", prixStr);
            req.setAttribute("stockSaisi", stockStr);
            req.getRequestDispatcher(FORM).forward(req, resp);
            return;
        }

        dao.save(produit);
        flash(req, "Produit « " + produit.getNom() + " » enregistré.");
        resp.sendRedirect(req.getContextPath() + "/produits"); // PRG
    }

}




