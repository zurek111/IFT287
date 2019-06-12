package AubergeInn.Gestionnaire;

import java.sql.SQLException;
import java.util.List;

import AubergeInn.Connexion;
import AubergeInn.IFT287Exception;
import AubergeInn.Table.TableCommodites;
import AubergeInn.Tuple.TupleCommodite;

public class GestionCommodite 
{
	private Connexion cx;
	private TableCommodites commodites;
	
	public GestionCommodite(TableCommodites commodites) throws IFT287Exception
	{
		this.cx = commodites.getConnexion();
		this.commodites = commodites;
	}
	
	public void ajouterCommodite(int idCommodite, String description, int prix)
			throws SQLException, IFT287Exception
	{
		try
        {
			// Vérifie les informations de la commodité
			if (prix < 0)
                throw new IFT287Exception("La commodité doit avoir un prix supérieur ou égal à zéro.");
			
			if (description.isEmpty())
                throw new IFT287Exception("La commodité doit avoir une description.");
            
			// Vérifie si la commodité existe déja
            if (commodites.existe(idCommodite))
                throw new IFT287Exception("La commodite existe déjà: " + idCommodite);

            // Ajout de la commodité
            if (commodites.ajouter(idCommodite, description, prix) == 0)
            	throw new IFT287Exception("Erreur lors de l'ajout d'une commodité à la table.");
            
            // Commit
            cx.commit();
        }
        catch (Exception e)
        {
            cx.rollback();
            throw e;
        }
	}
	
	public List<TupleCommodite> getCommoditesOffertes(int idChambre)
			throws SQLException
	{
		try
		{	
			return commodites.getCommoditesChambre(idChambre);
		}
		catch(Exception e)
		{
			throw e;
		}
		// Cette commande affiche les informations sur une chambre, incluant les commodités offertes.
	}
}
