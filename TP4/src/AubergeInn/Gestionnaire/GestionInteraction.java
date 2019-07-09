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
			
			int prixTotal = c.getPrix();
			String descriptionCommodites = "";
			for (Commodite commodite : gestionAubergeInn.getGestionCommodite().getCommodites(c))
			{
				prixTotal += commodite.getPrix();
				descriptionCommodites += commodite.getDescription() + "\n";
			}

			System.out.println("idChambre nom typeLit prix");
			System.out.println(c.getIdChambre() + " " + c.getNom() + " " + c.getTypeLit() + " " + prixTotal);
			
			System.out.println("Commodités offertes : ");
			if (descriptionCommodites.isEmpty())
				System.out.println("Aucune");
			else
				System.out.println(descriptionCommodites);
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
			{
				int prixTotal = c.getPrix();
				for (Commodite commodite : gestionAubergeInn.getGestionCommodite().getCommodites(c))
					prixTotal += commodite.getPrix();
				
				System.out.println(c.getIdChambre() + " " + c.getNom() + " " + c.getTypeLit() + " " + prixTotal);	
			}			
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
			
			// Liste de réservation du client
			List<Reservation> listeReservation = gestionAubergeInn.getGestionReservation().getReservationsClient(idClient);	
			
			if (listeReservation.isEmpty())
				System.out.println("Le client n'a jamais fait de réservation");
			else
			{
				System.out.println("Réservations :");
				
				for (Reservation r : listeReservation)
				{
					System.out.printf("Chambre #%d - %d$ | %tF au %tF %n", r.getIdChambre(), r.getPrixTotal(), r.getDateDebut(), r.getDateFin());
				}
			}
		}
		catch(Exception e)
		{
			throw e;
		}
	}
}
