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

public class Reservations extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    	// ajouter reservation
        System.out.println("Servlet Reservations : POST");
        try
        {
        	if (AubergeInnHelper.peutProceder(getServletContext(), request, response))
            {
	            if (request.getParameter("reserver") != null)
	            {
	                System.out.println("Servlet Reservations : POST - reservations");

                	String idClientParam = request.getParameter("idClient");
                	String idChambreParam = request.getParameter("idChambre");
                	String dateDebutParam = request.getParameter("dateDebut");
                	String dateFinParam = request.getParameter("dateFin");
                	
                	int idClient, idChambre;
                	Date dateDebut, dateFin;
                	try 
                	{
                		idClient = Integer.parseInt(idClientParam);
                		idChambre = Integer.parseInt(idChambreParam);
                	}
                	catch (NumberFormatException e)
                	{
                		throw new IFT287Exception("Format du no de client ou du no chambre incorrecte.");
                	}
                	try
                	{
                		dateDebut = Date.valueOf(dateDebutParam);
	                	dateFin = Date.valueOf(dateFinParam);
                	}
                	catch (Exception e)
                	{
                		throw new IFT287Exception("Les dates ne peuvent être vide et elle doivent être de la forme dd/MM/yyyy exemple: 25/12/2020");
                	}
                	
                	GestionAubergeInn aubergeInnUpdate = (GestionAubergeInn) request.getSession().getAttribute("aubergeInnUpdate");
                	synchronized (aubergeInnUpdate) {
                		aubergeInnUpdate.getGestionReservation().reserver(idClient, idChambre, dateDebut, dateFin);
                	}
                	
                	AubergeInnHelper.DispatchToReservations(request, response);

	            }
            }
        }
        catch (Exception e)
        {
            List<String> listeMessageErreur = new LinkedList<String>();
            listeMessageErreur.add(e.getMessage());
            request.setAttribute("listeMessageErreur", listeMessageErreur);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/reservations.jsp");
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
        System.out.println("Servlet Reservations : GET");
        
        // Si on a déjà entré les informations de connexion valide
        if (AubergeInnHelper.peutProceder(getServletContext(), request, response))
        {
            AubergeInnHelper.DispatchToReservations(request, response);
        	
        }
    }

} // class
