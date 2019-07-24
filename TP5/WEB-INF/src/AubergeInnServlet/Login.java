package AubergeInnServlet;

import java.util.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import AubergeInn.Connexion;
import AubergeInn.IFT287Exception;


/**
 * Servlet qui gère l'accès à la base de donnée
 * du système de gestion de bibliothèque
 * 
 * Si les informations entrées sont erronnées, on retourne à la page de connexion
 * Sinon, on affiche la page principale de l'application
 * 
 * <pre>
 * Marc Frappier
 * Université de Sherbrooke
 * Version 2.0 - 13 novembre 2004
 * IFT287 - Exploitation de BD relationnelles et OO
 * 
 * Vincent Ducharme
 * Université de Sherbrooke
 * Version 3.0 - 11 novembre 2018
 * IFT287 - Exploitation de BD relationnelles et OO
 * </pre>
 */

public class Login extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try
        {
            System.out.println("Servlet Login : POST");
            // Si on a d�j� entr� les informations de connexion valide
            if (BiblioHelper.infoBDValide(getServletContext()))
            {
                BiblioHelper.DispatchToLogin(request, response);
                return;
            }
            
            // lecture des param�tres du formulaire login.jsp
            String userId = request.getParameter("userIdBD");
            String motDePasse = request.getParameter("motDePasseBD");
            String serveur = request.getParameter("serveur");
            String bd = request.getParameter("bd");
            
            request.setAttribute("userIdBD", userId);
            request.setAttribute("motDePasseBD", motDePasse);
            request.setAttribute("serveur", serveur);
            request.setAttribute("bd", bd);
                        
            if(userId == null || userId.equals(""))
                throw new IFT287Exception("Vous devez entrer un nom d'utilisateur.");
            
            if(motDePasse == null || motDePasse.equals(""))
                throw new IFT287Exception("Vous devez entrer un mot de passe.");
            
            if(bd == null || bd.equals(""))
                throw new IFT287Exception("Vous devez entrer un nom de base de donn�e.");

            if (serveur == null || serveur.equals(""))
            {
                throw new IFT287Exception("Vous devez choisir un serveur.");
            }
            
            try
            {
                // Valider que les informations entr�es sont les bonnes
                Connexion cx = new Connexion(serveur, bd, userId, motDePasse);
                cx.fermer();
                
                // Sauvegarder les informations de connexion dans le contexte pour les r�utiliser
                // pour chaque client connect�                    
                getServletContext().setAttribute("serveur", serveur);
                getServletContext().setAttribute("bd", bd);
                getServletContext().setAttribute("user", userId);
                getServletContext().setAttribute("pass", motDePasse);
                
                // Afficher le menu de connexion principal de l'application 
                RequestDispatcher dispatcher = request.getRequestDispatcher("/Login");
                dispatcher.forward(request, response);
            }
            catch(Exception e)
            {
                List<String> listeMessageErreur = new LinkedList<String>();
                listeMessageErreur.add("Erreur de connexion au serveur de base de donn�e");
                listeMessageErreur.add(e.getMessage());
                request.setAttribute("listeMessageErreur", listeMessageErreur);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
                dispatcher.forward(request, response);
                // pour d�boggage seulement : afficher tout le contenu de
                // l'exception
                e.printStackTrace();
            }
            
        }
        catch (IFT287Exception e)
        {
            List<String> listeMessageErreur = new LinkedList<String>();
            listeMessageErreur.add(e.getMessage());
            request.setAttribute("listeMessageErreur", listeMessageErreur);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
            dispatcher.forward(request, response);
            // pour d�boggage seulement : afficher tout le contenu de
            // l'exception
            e.printStackTrace();
        }
    }

 // Dans les formulaires, on utilise la méthode POST
    // donc, si le servlet est appelé avec la méthode GET, c'est que 
    // quelqu'un a tapé le nom du servlet dans la barre d'adresse.
    // On redirige vers la bonne page
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        System.out.println("Servlet Login : GET");
        // Si on a d�j� entr� les informations de connexion valide
        if (BiblioHelper.infoBDValide(getServletContext()))
        {
            BiblioHelper.DispatchToLogin(request, response);
        }
        else
        {
            BiblioHelper.DispatchToBDConnect(request, response);
        }
    }

} // class
