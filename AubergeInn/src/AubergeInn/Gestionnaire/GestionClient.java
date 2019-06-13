package AubergeInn.Gestionnaire;

import java.sql.SQLException;

import AubergeInn.Connexion;
import AubergeInn.IFT287Exception;
import AubergeInn.Table.TableClients;
import AubergeInn.Table.TableReservations;
import AubergeInn.Tuple.TupleClient;

public class GestionClient 
{
	private Connexion cx;
	private TableClients clients;
	private TableReservations reservations;
	
	public GestionClient(TableClients clients, TableReservations reservations) throws IFT287Exception
	{
		this.cx = clients.getConnexion();
		
		if (cx != reservations.getConnexion())
            throw new IFT287Exception("Les instances de TableClients et de TableReservations n'utilisent pas la même connexion au serveur");
        
		this.clients = clients;
        this.reservations = reservations;
	}
	
	public void ajouterClient(int idClient, String prenom, String nom, int age)
			throws SQLException, IFT287Exception
	{
		try
        {
			// Vérifie les informations du client
			if (age < 0 || prenom.isEmpty() || nom.isEmpty())
                throw new IFT287Exception("Le client doit avoir des informations personnelles valides.");
            
			// Vérifie si le membre existe déja
            if (clients.existe(idClient))
                throw new IFT287Exception("Client existe déjà: " + idClient);

            // Ajout du client
            if (clients.ajouter(idClient, prenom, nom, age) == 0)
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
	
	public TupleClient getClient(int idClient)
			throws SQLException, IFT287Exception
	{
		try
		{
			TupleClient tupleClient = clients.getClient(idClient);
			
			if (tupleClient == null)
				throw new IFT287Exception("Le client n'existe pas : " + idClient);

			tupleClient.setReservations(reservations.getReservationsClient(idClient));
			
			return tupleClient;
		}
		catch(Exception e)
		{
			throw e;
		}
	}	
	
	public void supprimerClient(int idClient)
			throws SQLException, IFT287Exception, Exception
	{
		 try
	        {
	            TupleClient tupleClient = clients.getClient(idClient);
	            
	            // Verifie si le client est existant
	            if (tupleClient == null)
	                throw new IFT287Exception("Client inexistant: " + idClient);
	            
	            // Verifie si le client a des réservations
	            if (!reservations.getReservationsClient(idClient).isEmpty())
	                throw new IFT287Exception("Client " + idClient + " a des réservations");

	            // Suppression du membre
	            if (clients.supprimer(idClient) == 0)
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
