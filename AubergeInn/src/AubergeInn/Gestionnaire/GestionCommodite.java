package AubergeInn.Gestionnaire;

import AubergeInn.Connexion;
import AubergeInn.IFT287Exception;
import AubergeInn.Table.TableCommodites;
import AubergeInn.Table.TableCommoditesOffertes;

public class GestionCommodite 
{
	private Connexion cx;
	private TableCommodites commodites;
	
	public GestionCommodite(TableCommodites commodites) throws IFT287Exception
	{
		this.commodites = commodites;
	}
	
	public void ajouterCommodite(int idCommodite, String description, int prix)
	{
		// Cette commande ajoute un nouveau service offert par l’entreprise.
	}
}
