package AubergeInn.Gestionnaire;

import java.sql.SQLException;
import java.util.List;

import AubergeInn.Connexion;
import AubergeInn.IFT287Exception;
import AubergeInn.Table.TableChambres;
import AubergeInn.Table.TableCommodites;
import AubergeInn.Tuple.TupleChambre;
import AubergeInn.Tuple.TupleCommodite;

public class GestionInteraction 
{
	private Connexion cx;
	private TableChambres chambres;
	private TableCommodites commodites;

	public GestionInteraction(Connexion cx, TableChambres chambres, TableCommodites commodites) 
			throws IFT287Exception
	{
		 this.cx = cx;
		 
		 if (cx != chambres.getConnexion())
	            throw new IFT287Exception("Le gestionnaire d'interaction n'utilise pas la même connexion au serveur que l'instance TableChambres");

		 if (cx != commodites.getConnexion())
	            throw new IFT287Exception("Le gestionnaire d'interaction n'utilise pas la même connexion au serveur que l'instance TableCommodites");
		 
		 this.chambres = chambres;
		 this.commodites = commodites;
	}

	public void afficherChambre(int idChambre)
			throws SQLException, IFT287Exception
	{
		try
		{	
			TupleChambre c = chambres.getChambre(idChambre);
			if (c == null)
				throw new IFT287Exception("La chambre n'existe pas : " + idChambre);
			
			List<TupleCommodite> listeCommodites = commodites.getCommoditesChambre(idChambre);
			if (listeCommodites != null)
			{
				int prixCommodites = 0;
				
				for (TupleCommodite commodite : listeCommodites)
					prixCommodites += commodite.getPrix();
				
				c.setPrix(c.getPrix() + prixCommodites);
			}

			System.out.println("");
			System.out.println("idChambre nom typeLit prix");
			System.out.println(c.getIdChambre() + " " + c.getNom() + " " + c.getTypeLit() + " " + c.getPrix());
			
			System.out.println("Commodités offertes : ");
			for (TupleCommodite commodite : listeCommodites)
				System.out.println(commodite.getDescription());
			
	        // Commit
            cx.commit();
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	public void afficherChambresLibres()
	{
		// Cette commande affiche toutes les chambres qui sont disponibles. L’affichage
		// doit inclure le prix de location de la chambre (prix de base, plus les commodités).
	}
	
	public void afficherClient(int idClient)
	{
		// Cette commande affiche toutes les informations sur un client, incluant
		// les réservations présentes et passées. Les réservations contiennent le
		// prix total de la réservation, sans les taxes.
	}
}
