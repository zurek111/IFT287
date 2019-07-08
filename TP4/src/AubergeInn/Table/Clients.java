package AubergeInn.Table;

import AubergeInn.Connexion;
import AubergeInn.Tuple.Client;

import static com.mongodb.client.model.Filters.eq;
import org.bson.Document;
import com.mongodb.client.MongoCollection;

public class Clients 
{
	private MongoCollection<Document> clientsCollection;
    private Connexion cx;
    
	public Clients(Connexion cx)
	{
		this.cx = cx;
		clientsCollection = cx.getDatabase().getCollection("Clients");
	}
	
    public Connexion getConnexion()
    {
        return cx;
    }
    
    /**
	 * Fonction pour savoir si un client fait parti de la BD objet.
	 * 
	 * @param idClient  l'id du client.
	 * 
	 * @return Vrai si le client existe, faux sinon.
     */
    public boolean existe(int idClient)
    {
    	return clientsCollection.find(eq("idClient", idClient)).first() != null;
    }
    
    /**
	 * Fonction pour obtenir un client de la BD objet.
	 * 
	 * @param idClient  l'id du client.
	 * 
	 * @return L'objet client contenant ses données.
     */
    public Client getClient(int idClient)
    {
    	Document d = clientsCollection.find(eq("idClient", idClient)).first();
    	if(d != null)
    	{
    		return new Client(d);
    	}
        return null;
    }
    
    /**
	 * Fonction pour ajouter un client dans la BD.
	 * 
	 * @param client L'objet client à ajouter dans la BD.
	 * 
	 * @return L'objet client ajouté dans la BD.
     */
    public void ajouter(Client client)
    {
    	clientsCollection.insertOne(client.toDocument());
    }
    
    /**
	 * Fonction pour supprimer un client de la BD.
	 * 
	 * @param client L'objet client à ajouter dans la BD.
	 * 
	 * @return retourne vrai si supprimer dans la BD faux sinon.
     */
    public boolean supprimer(int idClient)
    {
    	return clientsCollection.deleteOne(eq("idClient", idClient)).getDeletedCount() > 0;
    }
}
