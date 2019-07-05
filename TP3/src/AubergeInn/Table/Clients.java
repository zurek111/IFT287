package AubergeInn.Table;

import java.util.List;

import javax.persistence.TypedQuery;

import AubergeInn.Connexion;
import AubergeInn.Tuple.Client;

public class Clients 
{
    private TypedQuery<Client> stmClient;
    private Connexion cx;
    
	public Clients(Connexion cx)
	{
		this.cx = cx;
		stmClient = cx.getConnection().createQuery(
				"select c from Client c where c.idClient = :idClient",Client.class);
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
    	stmClient.setParameter("idClient", idClient);
    	return !stmClient.getResultList().isEmpty();
    }
    
    /**
	 * Fonction pour obtenir un client de la BD objet.
	 * 
	 * @param idClient  l'id du client.
	 * 
	 * @return Le tuple du client contenant ses données.
     */
    public Client getClient(int idClient)
    {
    	stmClient.setParameter("idClient", idClient);
    	List<Client> clients = stmClient.getResultList();
        if(!clients.isEmpty())
        {
            return clients.get(0);
        }
        else
        {
            return null;
        }
    }
    
    /**
	 * Fonction pour ajouter un client dans la BD.
	 * 
	 * @param Client L'objet client à ajouter dans la BD.
	 * 
	 * @return L'objet Client ajouté dans la BD.
     */
    public Client ajouter(Client client)
    {
    	cx.getConnection().persist(client);
    	return client;
    }
    
    /**
	 * Fonction pour supprimer un client de la BD.
	 * 
	 * @param Client L'objet client à ajouter dans la BD.
	 * 
	 * @return retourne vrai si supprimer dans la BD faux sinon.
     */
    public boolean supprimer(Client client)
    {
    	if(client != null)
        {
            cx.getConnection().remove(client);
            return true;
        }
        return false;
    }
}
