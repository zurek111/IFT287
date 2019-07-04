package AubergeInn.Gestionnaire;

import java.util.List;

import AubergeInn.Connexion;
import AubergeInn.IFT287Exception;
import AubergeInn.Tuple.TupleChambre;
import AubergeInn.Tuple.TupleCommodite;
import AubergeInn.Tuple.TupleReservation;
import AubergeInn.Tuple.TupleClient;

public class GestionInteraction 
{
	private Connexion cx;

	public GestionInteraction(Connexion cx) 
			throws IFT287Exception
	{
		 this.cx = cx;
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
			TupleChambre c = gestionAubergeInn.getGestionChambre().getChambre(idChambre);

			System.out.println("idChambre nom typeLit prix");
			System.out.println(c.getIdChambre() + " " + c.getNom() + " " + c.getTypeLit() + " " + c.getPrixTotal());
			
			System.out.println("Commodités offertes : ");
			for (TupleCommodite commodite : c.getCommodites())
				System.out.println(commodite.getDescription());
			
	        // Commit
            cx.commit();
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
			List<TupleChambre> listeChambres = gestionAubergeInn.getGestionChambre().getChambresLibres();
			
			System.out.println("idChambre nom typeLit prixTotal");

			for (TupleChambre c : listeChambres)
				System.out.println(c.getIdChambre() + " " + c.getNom() + " " + c.getTypeLit() + " " + c.getPrix());

	        // Commit
            cx.commit();		
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
			TupleClient c = gestionAubergeInn.getGestionClient().getClient(idClient);
			
			System.out.println("idClient prenom nom age");
			System.out.println(c.getIdClient() + " " + c.getPrenom() + " " + c.getNom() + " " + c.getAge());
			
			if (c.getReservations().isEmpty())
				System.out.println("Le client n'a jamais fait de réservation");
			else
			{
				System.out.println("Réservations :");
				
				for (TupleReservation r : c.getReservations())
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
