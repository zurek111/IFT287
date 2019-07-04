package AubergeInn.Gestionnaire;

import java.sql.Date;
import java.time.LocalDate;

import AubergeInn.Connexion;
import AubergeInn.IFT287Exception;
import AubergeInn.Table.TableClients;
import AubergeInn.Tuple.TupleClient;
import AubergeInn.Tuple.TupleReservation;

public class GestionClient 
{
	private Connexion cx;
	private TableClients clients;
	
	public GestionClient(TableClients clients) throws IFT287Exception
	{
		this.cx = clients.getConnexion();
        
		this.clients = clients;
	}
	
	/**
	 * Fonction pour ajouter un client.
	 * 
	 * @param idClient  l'id du client.
	 * @param prenom  le nom du client.
	 * @param nom  le prenom du client.
	 * @param age  l'age du client.
	 * 
     */
	public void ajouterClient(int idClient, String prenom, String nom, int age)
			throws IFT287Exception
	{
		try
        {
			cx.demarreTransaction();
			// Vérifie les informations du client
			if (age < 0 || prenom.isEmpty() || nom.isEmpty())
                throw new IFT287Exception("Le client doit avoir des informations personnelles valides.");
            
			// Vérifie si le membre existe déja
            if (clients.existe(idClient))
                throw new IFT287Exception("Client existe déjà: " + idClient);
            
            TupleClient newClient = new TupleClient(idClient,prenom,nom,age);
            // Ajout du client
            if (clients.ajouter(newClient) != newClient)
            	throw new IFT287Exception("Erreur lors de l'ajout d'un client à la table.");
            
            // Commit
            cx.commit();
        }
        catch (Exception e)
        {
            cx.rollback();
            throw e;
        }
	}
	
	/**
	 * Fonction pour obtenir un client de la BD.
	 * 
	 * @param idClient  l'id du client.
	 * 
	 * @return Le tuple du client contenant ses données.
     */
	public TupleClient getClient(int idClient)
			throws IFT287Exception
	{
		try
		{
			cx.demarreTransaction();
			TupleClient tupleClient = clients.getClient(idClient);
			
			if (tupleClient == null)
				throw new IFT287Exception("Le client n'existe pas : " + idClient);

			cx.commit();
			return tupleClient;
		}
		catch(Exception e)
		{
			throw e;
		}
	}	
	
	/**
	 * Fonction pour supprimer un client de la BD.
	 * 
	 * @param idClient  l'id du client.
	 * 
     */
	public void supprimerClient(int idClient)
			throws IFT287Exception
	{
		 try
	        {
			 	cx.demarreTransaction();
	            TupleClient tupleClient = clients.getClient(idClient);
	            
	            // Verifie si le client est existant
	            if (tupleClient == null)
	                throw new IFT287Exception("Client inexistant: " + idClient);
	            
	            // Verifie si le client a des réservations
	            for (TupleReservation reservation : tupleClient.getReservations())
	            {
	            	LocalDate localDate = LocalDate.now();
	    			Date date = Date.valueOf(localDate);
	            	if (!reservation.getDateFin().before(date))
	            		throw new IFT287Exception("Client " + idClient + " a des réservations");
	            }

	            // Suppression du membre
	            if (!clients.supprimer(tupleClient))
	                throw new IFT287Exception("Erreur lors de la suppression d'un client.");
	            
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
