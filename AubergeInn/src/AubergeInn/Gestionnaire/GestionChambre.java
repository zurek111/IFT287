package AubergeInn.Gestionnaire;

import AubergeInn.Connexion;
import AubergeInn.IFT287Exception;
import AubergeInn.Table.TableChambres;
import AubergeInn.Table.TableCommoditesOffertes;
import AubergeInn.Table.TableCommodites;
import AubergeInn.Table.TableReservations;

public class GestionChambre 
{
	private Connexion cx;
	private TableChambres chambres;
	private TableCommoditesOffertes commoditesOffertes;
	private TableCommodites commodites;
	private TableReservations reservations;
	
	public GestionChambre(TableChambres chambres, TableCommodites commodites, TableCommoditesOffertes commoditesOffertes, TableReservations reservations) throws IFT287Exception
	{
		this.cx = chambres.getConnexion();
		
		if (cx != commodites.getConnexion())
            throw new IFT287Exception("Les instances de TableChambres et de TableCommodites n'utilisent pas la même connexion au serveur");
		
		if (cx != commoditesOffertes.getConnexion())
            throw new IFT287Exception("Les instances de TableChambres et de TableCommoditesOffertes n'utilisent pas la même connexion au serveur");
        
		if (cx != reservations.getConnexion())
            throw new IFT287Exception("Les instances de TableChambres et de TableReservations n'utilisent pas la même connexion au serveur");

		this.chambres = chambres;
		this.commoditesOffertes = commoditesOffertes;
		this.commodites = commodites;
        this.reservations = reservations;
	}
	
	public void ajouterChambre(int idChambre, String nom, String type, int prix)
	{
		// Cette commande ajoute une nouvelle chambre au système.
	}
	
	public void supprimerChambre(int idChambre)
	{
		// Cette commande supprime une chambre si elle n’est pas réservée et
		// n’a pas de réservation future.
	}
	
	public void inclureCommodite(int idChambre, int idCommodite)
	{
		// Cette commande ajoute une commodité à une chambre.
	}
	
	public void enleverCommodite(int idChambre, int idCommodite)
	{
		// Cette commande enlève une commodité d’une chambre.
	}
}
