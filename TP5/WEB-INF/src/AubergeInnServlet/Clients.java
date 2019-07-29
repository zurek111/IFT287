package AubergeInnServlet;

import java.sql.Date;
import java.io.*;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

import AubergeInn.IFT287Exception;
import AubergeInn.Gestionnaire.GestionAubergeInn;


/**
 * Servlet qui gère la connexion d'un utilisateur au système de gestion de
 * bibliothèque
 * 
 * <pre>
 * Vincent Ducharme
 * Université de Sherbrooke
 * Version 1.0 - 11 novembre 2018
 * IFT287 - Exploitation de BD relationnelles et OO
 * </pre>
 */

public class Clients extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    	// ajouter client
        System.out.println("Servlet Clients : POST");
        try
        {
        	if (AubergeInnHelper.peutProceder(getServletContext(), request, response))
            {
	            if (request.getParameter("ajouter") != null)
	            {
	                System.out.println("Servlet Clients : POST - ajouter");

                	String idClientParam = request.getParameter("idClient");
                	String prenom = request.getParameter("prenom");
                	String nom = request.getParameter("nom");
                	String ageParam = request.getParameter("age");
                	
                	int idClient, age;
                	try 
                	{
                		idClient = Integer.parseInt(idClientParam);
                		age = Integer.parseInt(ageParam);
                	}
                	catch (NumberFormatException e)
                	{
                		throw new IFT287Exception("L'id et l'âge doivent être un nombre.");
                	}
                	
                	if (prenom.length() == 0)
                		throw new IFT287Exception("Le client doit avoir un prenom.");
                	if (nom.length() == 0)
                		throw new IFT287Exception("Le client doit avoir un nom.");
                	
                	GestionAubergeInn aubergeInnUpdate = (GestionAubergeInn) request.getSession().getAttribute("aubergeInnUpdate");
                	synchronized (aubergeInnUpdate) {
                		aubergeInnUpdate.getGestionClient().ajouterClient(idClient, prenom, nom, age);
                	}
                	
                	AubergeInnHelper.DispatchToClients(request, response);

	            } 
	            else if (request.getParameter("supprimer") != null)
	            {
	            	System.out.println("Servlet Clients : POST - supprimer");
	            	int idClient = Integer.parseInt(request.getParameter("supprimer"));

	            	GestionAubergeInn aubergeInnUpdate = (GestionAubergeInn) request.getSession().getAttribute("aubergeInnUpdate");
                	synchronized (aubergeInnUpdate) {
                		aubergeInnUpdate.getGestionClient().supprimerClient(idClient);
                	}
                	AubergeInnHelper.DispatchToClients(request, response);
	            }
            }
        }
        catch (Exception e)
        {
            List<String> listeMessageErreur = new LinkedList<String>();
            listeMessageErreur.add(e.getMessage());
            request.setAttribute("listeMessageErreur", listeMessageErreur);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/clients.jsp");
            dispatcher.forward(request, response);
            // pour déboggage seulement : afficher tout le contenu de l'exception
            e.printStackTrace();
        }
    }

    // Dans les formulaires, on utilise la méthode POST
    // donc, si le servlet est appelé avec la méthode GET
    // c'est que l'adresse a été demandé par l'utilisateur.
    // On procède si la connexion est actives seulement, sinon
    // on retourne au login
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        System.out.println("Servlet Clients : GET");
        
        // Si on a déjà entré les informations de connexion valide
        if (AubergeInnHelper.peutProceder(getServletContext(), request, response))
        {
            AubergeInnHelper.DispatchToClients(request, response);
        	
        }
    }

} // class
