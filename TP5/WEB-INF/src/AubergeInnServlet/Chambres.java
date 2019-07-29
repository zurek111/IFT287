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

public class Chambres extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    	// ajouter chambres
        System.out.println("Servlet Chambres : POST");
        try
        {
        	if (AubergeInnHelper.peutProceder(getServletContext(), request, response))
            {
	            if (request.getParameter("ajouterChambre") != null)
	            {
	                System.out.println("Servlet Chambres : POST - ajouter");

                	String idChambreParam = request.getParameter("idChambre");
                	String typeLit = request.getParameter("typeLit");
                	String nom = request.getParameter("nom");
                	String prixParam = request.getParameter("prix");
                	
                	int idChambre, prix;
                	try 
                	{
                		idChambre = Integer.parseInt(idChambreParam);
                		prix = Integer.parseInt(prixParam);
                	}
                	catch (NumberFormatException e)
                	{
                		throw new IFT287Exception("L'id et l'âge doivent être un nombre.");
                	}
                	
                	if (typeLit.length() == 0)
                		throw new IFT287Exception("La chambre doit avoir un type de lit.");
                	if (nom.length() == 0)
                		throw new IFT287Exception("La chambre doit avoir un nom.");
                	
                	GestionAubergeInn aubergeInnUpdate = (GestionAubergeInn) request.getSession().getAttribute("aubergeInnUpdate");
                	synchronized (aubergeInnUpdate) {
                		aubergeInnUpdate.getGestionChambre().ajouterChambre(idChambre, nom, typeLit, prix);
                	}
	            } 
	            else if (request.getParameter("supprimerChambre") != null)
	            {
	            	System.out.println("Servlet Clients : POST - supprimer");
	            	int idChambre = Integer.parseInt(request.getParameter("supprimerChambre"));

	            	GestionAubergeInn aubergeInnUpdate = (GestionAubergeInn) request.getSession().getAttribute("aubergeInnUpdate");
                	synchronized (aubergeInnUpdate) {
                		aubergeInnUpdate.getGestionChambre().supprimerChambre(idChambre);
                	}
	            }
	            AubergeInnHelper.DispatchToChambres(request, response);
            }
        }
        catch (Exception e)
        {
            List<String> listeMessageErreur = new LinkedList<String>();
            listeMessageErreur.add(e.getMessage());
            request.setAttribute("listeMessageErreur", listeMessageErreur);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/chambres.jsp");
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
            AubergeInnHelper.DispatchToChambres(request, response);
        	
        }
    }

} // class
