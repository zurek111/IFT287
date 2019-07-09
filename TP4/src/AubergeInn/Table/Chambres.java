package AubergeInn.Table;

import static com.mongodb.client.model.Filters.eq;

import java.util.LinkedList;
import java.util.List;

import org.bson.Document;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import AubergeInn.Connexion;
import AubergeInn.Tuple.Chambre;

public class Chambres 
{
    private MongoCollection<Document> chambresCollection;
    private Connexion cx;
    
    /**
     * Creation d'une instance.
     */
	public Chambres(Connexion cx)
	{
		this.cx = cx;
		chambresCollection = cx.getDatabase().getCollection("Chambres");
	}
	
    /**
     * Retourner la connexion associée.
     */
    public Connexion getConnexion()
    {
        return cx;
    }
    
	/**
	 * Fonction pour savoir si une chambre existe dans la BD.
	 * 
	 * @param idChambre  l'id de la chambre.
	 * 
	 * @return Vrai si la chambre existe, faux sinon.
     */
    public boolean existe(int idChambre)
    {
    	return chambresCollection.find(eq("idChambre", idChambre)).first() != null;
    }

    /**
	 * Fonction pour obtenir toutes les chambres de la BD.
	 * 
	 * @return La liste des chambres.
     */
    public List<Chambre> getAllChambre()
    {
    	List<Chambre> listeChambres = new LinkedList<Chambre>();
    	 MongoCursor<Document> chambres = chambresCollection.find().iterator();
         try
         {
             while (chambres.hasNext())
             {
             	Chambre c = new Chambre(chambres.next());
             	listeChambres.add(c);
             }
         }
         finally
         {
        	 chambres.close();
         }
        
        return listeChambres;
    }
    
    /**
	 * Fonction pour obtenir une chambre de la BD avec son id.
	 * 
	 * @param idChambre  l'id de la chambre.
	 * 
	 * @return L'objet chambre qui contient les données de celle-ci.
     */
    public Chambre getChambre(int idChambre)
    {
    	Document d = chambresCollection.find(eq("idChambre", idChambre)).first();
    	
    	if(d != null)
    		return new Chambre(d);
    	
        return null;
    }
    
    /**
	 * Fonction pour ajouter une chambre dans la BD.
	 * 
	 * @param chambre La chambre à ajouter en BD
     */
    public void ajouter(Chambre chambre)
    {
        chambresCollection.insertOne(chambre.toDocument());
    }
    
    /**
	 * Fonction pour supprimer une chambre dans la BD.
	 * 
	 * @param idChambre Le id de la chambre à supprimer de la BD
	 * 
	 * @return vrai si supprimées dans la BD faux sinon.
     */
    public boolean supprimer(int idChambre)
    {
    	return chambresCollection.deleteOne(eq("idChambre", idChambre)).getDeletedCount() > 0;
    }    
}
