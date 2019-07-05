package AubergeInn.Gestionnaire;

import java.util.List;

import AubergeInn.IFT287Exception;
import AubergeInn.Tuple.Chambre;
import AubergeInn.Tuple.Commodite;
import AubergeInn.Tuple.Reservation;
import AubergeInn.Tuple.Client;

public class GestionInteraction 
{

	public GestionInteraction() 
			throws IFT287Exception
	{

	}
	
	/**
	 * Fonction pour afficher une chambre.
	 * 
	 * @param idChambre  l'id de la chambre.
	 * @param gestionAubergeInn  gestionnaire de l'auberge.
	 * 
     */
	public void afficherChambre(int idChambre, GestionAubergeInn gestionAubergeInn)
			throws IFT287Exception
	{
		try
		{		
			Chambre c = gestionAubergeInn.getGestionChambre().getChambre(idChambre);

			System.out.println("idChambre nom typeLit prix");
			System.out.println(c.getIdChambre() + " " + c.getNom() + " " + c.getTypeLit() + " " + c.getPrixTotal());
			
			System.out.println("Commodités offertes : ");
			if (c.getCommodites().isEmpty())
				System.out.println("Aucune");

			for (Commodite commodite : c.getCommodites())
				System.out.println(commodite.getDescription());
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	/**
	 * Fonction pour afficher la liste des chambres libres.
	 * 
	 * @param gestionAubergeInn  gestionnaire de l'auberge.
	 * 
     */
	public void afficherChambresLibres(GestionAubergeInn gestionAubergeInn)
			throws IFT287Exception
	{
		try
		{			
			List<Chambre> listeChambres = gestionAubergeInn.getGestionChambre().getChambresLibres();
			
			System.out.println("idChambre nom typeLit prixTotal");

			for (Chambre c : listeChambres)
				System.out.println(c.getIdChambre() + " " + c.getNom() + " " + c.getTypeLit() + " " + c.getPrixTotal());	
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	/**
	 * Fonction pour afficher un client.
	 * 
	 * @param idClient  l'id du client.
	 * @param gestionAubergeInn  gestionnaire de l'auberge.
	 * 
     */
	public void afficherClient(int idClient, GestionAubergeInn gestionAubergeInn)
			throws IFT287Exception
	{
		try
		{
			Client c = gestionAubergeInn.getGestionClient().getClient(idClient);
			
			System.out.println("idClient prenom nom age");
			System.out.println(c.getIdClient() + " " + c.getPrenom() + " " + c.getNom() + " " + c.getAge());
			
			if (c.getReservations().isEmpty())
				System.out.println("Le client n'a jamais fait de réservation");
			else
			{
				System.out.println("Réservations :");
				
				for (Reservation r : c.getReservations())
				{
					System.out.printf("Chambre #%d - %d$ | %tF au %tF %n", r.getChambre().getIdChambre(), r.getPrixTotal(), r.getDateDebut(), r.getDateFin());
				}
			}
		}
		catch(Exception e)
		{
			throw e;
		}
	}
}
