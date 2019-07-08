package AubergeInn.Gestionnaire;

import AubergeInn.Connexion;
import AubergeInn.IFT287Exception;
import AubergeInn.Table.Commodites;
import AubergeInn.Tuple.Commodite;

public class GestionCommodite 
{
	private Connexion cx;
	private Commodites commodites;
	
	public GestionCommodite(Commodites commodites) throws IFT287Exception
	{
		this.cx = commodites.getConnexion();
		this.commodites = commodites;
	}
	
	/**
	 * Fonction pour ajouter une commodité dans la BD.
	 * 
	 * @param idCommodite  l'id de la commodité.
	 * @param description  description de la commodité.
	 * @param prix		   prix de la commodité.
	 * 
     */
	public void ajouterCommodite(int idCommodite, String description, int prix)
			throws IFT287Exception
	{
		try
        {
            cx.demarreTransaction();

			// Vérifie les informations de la commodité
			if (prix < 0)
                throw new IFT287Exception("La commodité doit avoir un prix supérieur ou égal à zéro.");
			
			if (description.isEmpty())
                throw new IFT287Exception("La commodité doit avoir une description.");
            
			// Vérifie si la commodité existe déja
            if (commodites.existe(idCommodite))
                throw new IFT287Exception("La commodite existe déjà: " + idCommodite);
            Commodite commodite = new Commodite(idCommodite,description,prix);
            // Ajout de la commodité
            if (commodites.ajouter(commodite) != commodite)
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
	
}
