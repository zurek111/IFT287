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
 * Servlet qui gère la page des chambres
 */

public class Chambres extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        System.out.println("Servlet Chambres : POST");
        try
        {
        	if (AubergeInnHelper.peutProceder(getServletContext(), request, response))
            {
        		// Ajouter chambre
	            if (request.getParameter("ajouterChambre") != null)
	            {
	                System.out.println("Servlet Chambres : POST - ajouterChambre");

	                // Params : idChambre, typeLit, nom et prix
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
                	
                	// Paramètre vide ?
                	if (typeLit.length() == 0)
                		throw new IFT287Exception("La chambre doit avoir un type de lit.");
                	if (nom.length() == 0)
                		throw new IFT287Exception("La chambre doit avoir un nom.");
                	
                	// On ajoute la chambre à la BD
                	GestionAubergeInn aubergeInnUpdate = (GestionAubergeInn) request.getSession().getAttribute("aubergeInnUpdate");
                	synchronized (aubergeInnUpdate) {
                		aubergeInnUpdate.getGestionChambre().ajouterChambre(idChambre, nom, typeLit, prix);
                	}
	            } 
	            // Supprimer chambre
	            else if (request.getParameter("supprimerChambre") != null)
	            {
	            	System.out.println("Servlet Clients : POST - supprimerChambre");
	            	int idChambre = Integer.parseInt(request.getParameter("supprimerChambre"));

	            	// On supprime la chambre de la BD
	            	GestionAubergeInn aubergeInnUpdate = (GestionAubergeInn) request.getSession().getAttribute("aubergeInnUpdate");
                	synchronized (aubergeInnUpdate) {
                		aubergeInnUpdate.getGestionChambre().supprimerChambre(idChambre);
                	}
	            }
	            // Ajouter commodite
	            else if (request.getParameter("ajouterCommodite") != null)
	            {
	                System.out.println("Servlet Chambres : POST - ajouterCommodite");

	                // Params : idCommodite, description et prix
                	String idCommoditeParam = request.getParameter("idCommodite");
                	String description = request.getParameter("description");
                	String prixParam = request.getParameter("prix");
                	
                	int idCommodite, prix;
                	try 
                	{
                		idCommodite = Integer.parseInt(idCommoditeParam);
                		prix = Integer.parseInt(prixParam);
                	}
                	catch (NumberFormatException e)
                	{
                		throw new IFT287Exception("L'id et l'âge doivent être un nombre.");
                	}
                	
                	// Description vide ?
                	if (description.length() == 0)
                		throw new IFT287Exception("La chambre doit avoir un type de lit.");
                	
                	// On ajoute la commodité à la BD
                	GestionAubergeInn aubergeInnUpdate = (GestionAubergeInn) request.getSession().getAttribute("aubergeInnUpdate");
                	synchronized (aubergeInnUpdate) {
                		aubergeInnUpdate.getGestionCommodite().ajouterCommodite(idCommodite, description, prix);
                	}
	            } 
	            // Enlever commodite
	            else if (request.getParameter("enleverCommodite") != null)
	            {
	                System.out.println("Servlet Chambres : POST - enleverCommodite");
	                
	                // Params : idChambre et idCommodite
	                String param = request.getParameter("enleverCommodite");
	                String[] id = param.split("/");
	                String idChambreParam = id[0];
                	String idCommoditeParam = id[1];
                	
                	
                	int idCommodite, idChambre;
                	try 
                	{
                		idCommodite = Integer.parseInt(idCommoditeParam);
                		idChambre = Integer.parseInt(idChambreParam);
                	}
                	catch (NumberFormatException e)
                	{
                		throw new IFT287Exception("Les ids doivent être un nombre.");
                	}
                	
                	// On enleve la commodité de la chambre
                	GestionAubergeInn aubergeInnUpdate = (GestionAubergeInn) request.getSession().getAttribute("aubergeInnUpdate");
                	synchronized (aubergeInnUpdate) {
                		aubergeInnUpdate.getGestionChambre().enleverCommodite(idChambre, idCommodite);
                	}
	            } 
	            // Inclure commodite
	            else if (request.getParameter("inclureCommodite") != null)
	            {
	                System.out.println("Servlet Chambres : POST - inclureCommodite");

	                // Params : idChambre et idCommodite
                	String idChambreParam = request.getParameter("inclureCommodite");
                	String idCommoditeParam = request.getParameter("idCommodite" + idChambreParam);
                	
                	int idCommodite, idChambre;
                	try 
                	{
                		idCommodite = Integer.parseInt(idCommoditeParam);
                		idChambre = Integer.parseInt(idChambreParam);
                	}
                	catch (NumberFormatException e)
                	{
                		throw new IFT287Exception("Les ids doivent être un nombre.");
                	}
                	
                	// On inclut la commodité dans la chambre
                	GestionAubergeInn aubergeInnUpdate = (GestionAubergeInn) request.getSession().getAttribute("aubergeInnUpdate");
                	synchronized (aubergeInnUpdate) {
                		aubergeInnUpdate.getGestionChambre().inclureCommodite(idChambre, idCommodite);
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

    // get de l'adresse /tp5/Chambres
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
