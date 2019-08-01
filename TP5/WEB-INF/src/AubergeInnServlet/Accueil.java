package AubergeInnServlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;



/**
 * Servlet qui gère la page d'accueil
 * </pre>
 */

public class Accueil extends HttpServlet
{
    private static final long serialVersionUID = 1L;


    // amène au menu sauf si on n'est pas connecté. get de l'adresse tp5/Accueil
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        System.out.println("Servlet Accueil : GET");
        // Si on a déjà entré les informations de connexion valide
        if (AubergeInnHelper.peutProceder(getServletContext(), request, response))
        {
            AubergeInnHelper.DispatchToMenu(request, response);
        	
        }

    }

} // class
