package AubergeInnServlet;

import javax.servlet.http.*;

import AubergeInn.Gestionnaire.GestionAubergeInn;

import java.sql.*;

/**
 * Classe pour gestion des sessions
 * <P>
 * Système de gestion de bibliothèque &copy; 2004 Marc Frappier, Université de
 * Sherbrooke
 */

public class AubergeInnSessionListener implements HttpSessionListener
{
    public void sessionCreated(HttpSessionEvent se)
    {
    }

    public void sessionDestroyed(HttpSessionEvent se)
    {
        System.out.println("Session détruite pour l'utilisateur " + se.getSession().getAttribute("userID"));
        
        GestionAubergeInn biblioInterrogation = (GestionAubergeInn)se.getSession().getAttribute("biblioInterrogation");
        if (biblioInterrogation != null)
        {
            try
            {
                System.out.println("Fermeture de la connexion d'interrogation...");
                biblioInterrogation.fermer();
            }
            catch (SQLException e)
            {
                System.out.println("Impossible de fermer la connexion");
                e.printStackTrace();
            }
        }
        else
        {
            System.out.println("Aucun gestionnaire d'interrogation n'avait encore été créé.");
        }
        
        GestionAubergeInn biblioUpdate = (GestionAubergeInn)se.getSession().getAttribute("biblioUpdate");
        if (biblioUpdate != null)
        {
            try
            {
                System.out.println("Fermeture de la connexion de mise à jour...");
                biblioUpdate.fermer();
            }
            catch (SQLException e)
            {
                System.out.println("Impossible de fermer la connexion");
                e.printStackTrace();
            }
        }
        else
        {
            System.out.println("Aucun gestionnaire de mise à jour n'avait encore été créé.");
        }
    }
}
