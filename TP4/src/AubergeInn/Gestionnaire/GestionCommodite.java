package AubergeInn.Gestionnaire;

import java.util.List;

import AubergeInn.IFT287Exception;
import AubergeInn.Table.Commodites;
import AubergeInn.Tuple.Chambre;
import AubergeInn.Tuple.Commodite;

public class GestionCommodite 
{
	private Commodites commodites;
	
	public GestionCommodite(Commodites commodites) throws IFT287Exception
	{
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
            commodites.ajouter(commodite);
        }
        catch (Exception e)
        {
            throw e;
        }
	}
	
	public List<Commodite> getCommodites(Chambre chambre)
	{
		return commodites.getCommodites(chambre);
	}
	
}
