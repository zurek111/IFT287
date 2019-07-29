package AubergeInnServlet;
import AubergeInn.*;
import AubergeInn.Gestionnaire.GestionAubergeInn;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AubergeInnHelper
{
    public static boolean infoBDValide(ServletContext c)
    {
        return c.getAttribute("serveur") != null;
    }
    
    public static boolean peutProceder(ServletContext c, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        if(infoBDValide(c))
        {
        	return true;
        }
        else
        {
            DispatchToBDConnect(request, response);
            return false;
        }
    }
    
    /*public static boolean peutProcederLogin(ServletContext c, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        if(infoBDValide(c))
        {
            HttpSession session = request.getSession(false);
            if (session != null)
            {
                session.invalidate();
            }
            return true;
        }
        else
        {
            DispatchToBDConnect(request, response);
            return false;
        }
    }*/
    
    /*public static void DispatchToLoginServlet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        HttpSession session = request.getSession(false);
        if (AubergeInnHelper.estConnecte(session))
        {
            session.invalidate();
        }
        // Afficher le menu de connexion principal de l'application
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Login");
        dispatcher.forward(request, response);
    }
    
    public static void DispatchToLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        HttpSession session = request.getSession(false);
        if (AubergeInnHelper.estConnecte(session))
        {
            session.invalidate();
        }
        // Afficher le menu de connexion principal de l'application
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/login.jsp");
        dispatcher.forward(request, response);
    }*/
    
    public static void DispatchToMenu(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        
        // Afficher le menu principal de l'application
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/menu.jsp");
        dispatcher.forward(request, response);
    }
    
    public static void DispatchToReservations(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        
        // Amène aux réservations
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/reservations.jsp");
        dispatcher.forward(request, response);
    }
    
    public static void DispatchToClients(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        
        // Amène aux réservations
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/clients.jsp");
        dispatcher.forward(request, response);
    }
    
    public static void DispatchToBDConnect(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        HttpSession session = request.getSession(false);
        if (AubergeInnHelper.estConnecte(session))
        {
            session.invalidate();
        }
        // Afficher le menu de connexion principal de l'application
        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
    }
    
    public static boolean estConnecte(HttpSession session)
    {
        if(session == null)
            return false;
        return session.getAttribute("etat") != null;
    }
    
    public static boolean gestionnairesCrees(HttpSession session)
    {
        if(session == null)
            return false;
        return session.getAttribute("aubergeInnInterrogation") != null;
    }
    
    public static void creerGestionnaire(ServletContext c, HttpSession s) throws SQLException, IFT287Exception
    {
        String serveur = (String) c.getAttribute("serveur");
        String bd = (String) c.getAttribute("bd");
        String userIdBD = (String) c.getAttribute("user");
        String pass = (String) c.getAttribute("pass");

        GestionAubergeInn aubergeInnInterrogation = new GestionAubergeInn(serveur, bd, userIdBD, pass);
        aubergeInnInterrogation.getConnexion().setIsolationReadCommited();
        s.setAttribute("aubergeInnInterrogation", aubergeInnInterrogation);
        GestionAubergeInn aubergeInnUpdate = new GestionAubergeInn(serveur, bd, userIdBD, pass);
        s.setAttribute("aubergeInnUpdate", aubergeInnUpdate);
    }
    
    public static GestionAubergeInn getAubergeInnInterro(HttpSession session)
    {
        return (GestionAubergeInn)session.getAttribute("aubergeInnInterrogation");
    }
    
    public static GestionAubergeInn getAubergeInnUpdate(HttpSession session)
    {
        return (GestionAubergeInn)session.getAttribute("aubergeInnUpdate");
    }
    
    
    public static int ConvertirInt(String v, String nom) throws IFT287Exception
    {
        try
        {
            return Integer.parseInt(v);
        }
        catch(Exception e)
        {
            throw new IFT287Exception(nom + " ne doit être composé que de chiffre.");
        }
    }
    
    public static long ConvertirLong(String v, String nom) throws IFT287Exception
    {
        try
        {
            return Long.parseLong(v);
        }
        catch(Exception e)
        {
            throw new IFT287Exception(nom + " ne doit être composé que de chiffre.");
        }
    }
}
