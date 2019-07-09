package AubergeInn.Table;

import AubergeInn.Connexion;
import AubergeInn.Tuple.Chambre;
import AubergeInn.Tuple.Commodite;

import static com.mongodb.client.model.Filters.eq;

import java.util.LinkedList;
import java.util.List;

import org.bson.Document;
import com.mongodb.client.MongoCollection;

public class Commodites 
{
	private MongoCollection<Document> commoditesCollection;

    private Connexion cx;
    
    // Fonction de connexion.
	public Commodites(Connexion cx) 
	{
		this.cx = cx;
		commoditesCollection = cx.getDatabase().getCollection("Commodites");
	}
	
    public Connexion getConnexion()
    {
        return cx;
    }
    
    /**
	 * Fonction pour savoir si une commodité existe.
	 * @param idCommodite  l'id de la commodité.
	 * 
	 * @return Vrai si elle fait existe en BD, faux sinon..
     */
    public boolean existe(int idCommodite)
    {
    	return commoditesCollection.find(eq("idCommodite", idCommodite)).first() != null;
    }
    
    /**
	 * Fonction pour aller chercher une commodité en BD.
	 * @param idCommodite  l'id de la commodité.
	 * 
	 * @return un object commodité contenant les données de celle-ci.
     */
    public Commodite getCommodite(int idCommodite)
    {
    	Document d = commoditesCollection.find(eq("idCommodite", idCommodite)).first();
    	
    	if(d != null)
    		return new Commodite(d);

        return null;
    }
    
    /**
	 * Fonction pour ajouter une commodité.
	 * @param commodite l'objet commodité à ajouter.
	 * 
	 * @return retourne l'objet commodité ajouté.
     */
    public void ajouter(Commodite commodite)
    {
    	commoditesCollection.insertOne(commodite.toDocument());
    }
    
    public List<Commodite> getCommodites(Chambre chambre)
    {
    	List<Commodite> listeCommodites = new LinkedList<Commodite>();
		for (int idCommodite : chambre.getIdCommodites())
		{
			listeCommodites.add(getCommodite(idCommodite));
		}
		
		return listeCommodites;
    }
}
