package AubergeInnServlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * Classe pour logout système de gestion de l'aubergeInn
 * <P>
 * Système de gestion de l'aubergeInn &copy; 2004 Marc Frappier, Université de
 * Sherbrooke
 * Adapté Par Maxime Paré et Simon Cesare-Zurek
 */

public class Logout extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // invalider la session pour libérer les ressources associées à la
        // session
        request.getSession().invalidate();
        getServletContext().removeAttribute("serveur");
        getServletContext().removeAttribute("bd");
        getServletContext().removeAttribute("user");
        getServletContext().removeAttribute("pass");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Login");
        dispatcher.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }
}
