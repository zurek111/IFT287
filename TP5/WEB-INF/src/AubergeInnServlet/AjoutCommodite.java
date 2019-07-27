package AubergeInnServlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;


/**
 * Servlet qui g�re l'ajout d'une commodité au syst�me de gestion d'aubergeInn
 * 
 * <pre>
 * Simon Cesare-Zurek et Maxime Paré
 * Universit� de Sherbrooke
 * IFT287 - Exploitation de BD relationnelles et OO
 * </pre>
 */

public class AjoutCommodite extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        System.out.println("Servlet Inscription : POST");
        if (!AubergeInnHelper.peutProceder(getServletContext(), request, response))
        {
            // Le dispatch vers le login se fait dans BiblioHelper.peutProceder
            return;
        }

        System.out.println("Servlet Inscription : POST dispatch vers ajoutCommodite.jsp");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/ajoutCommodite.jsp");
        dispatcher.forward(request, response);
    }

    // Dans les formulaires, on utilise la m�thode POST
    // donc, si le servlet est appel� avec la m�thode GET
    // s'est qu'on a �crit l'adresse directement dans la barre d'adresse.
    // On proc�de si on est connect� correctement, sinon, on retourne au login
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        System.out.println("Servlet Inscription : GET");
        // Si on a d�j� entr� les informations de connexion valide

        if (AubergeInnHelper.peutProceder(getServletContext(), request, response))
        {
            System.out.println("Servlet Inscription : GET dispatch vers ajoutCommodite.jsp");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/ajoutCommodite.jsp");
            dispatcher.forward(request, response);
        }
    }

} // class
