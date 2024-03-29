package AubergeInn.Gestionnaire;

import java.sql.Date;
import java.time.LocalDate;

import AubergeInn.IFT287Exception;
import AubergeInn.Table.Clients;
import AubergeInn.Table.Reservations;
import AubergeInn.Tuple.Client;
import AubergeInn.Tuple.Reservation;

public class GestionClient 
{
	private Clients clients;
	private Reservations reservations;
	
	public GestionClient(Clients clients, Reservations reservations) throws IFT287Exception
	{
		if (clients.getConnexion() != reservations.getConnexion())
            throw new IFT287Exception("Les collections d'objets n'utilisent pas la même connexion au serveur");

		this.clients = clients;
		this.reservations = reservations;
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
			// Vérifie les informations du client
			if (age < 0 || prenom.isEmpty() || nom.isEmpty())
                throw new IFT287Exception("Le client doit avoir des informations personnelles valides.");
            
			// Vérifie si le membre existe déja
            if (clients.existe(idClient))
                throw new IFT287Exception("Client existe déjà: " + idClient);
            
            Client newClient = new Client(idClient,prenom,nom,age);
            
            // Ajout du client
            clients.ajouter(newClient);
        }
        catch (Exception e)
        {
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
	public Client getClient(int idClient)
			throws IFT287Exception
	{
		try
		{
			Client client = clients.getClient(idClient);
			
			if (client == null)
				throw new IFT287Exception("Le client n'existe pas : " + idClient);

			return client;
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
		    // Verifie si le client est existant
		    if (!clients.existe(idClient))
		        throw new IFT287Exception("Client inexistant: " + idClient);
		    
		    // Verifie si le client a des réservations
		    for (Reservation reservation : reservations.getReservationsClient(idClient))
		    {
		    	LocalDate localDate = LocalDate.now();
				Date date = Date.valueOf(localDate);
		    	if (!reservation.getDateFin().before(date))
		    		throw new IFT287Exception("Client " + idClient + " a des réservations");
		    }
		
		    // Suppression du membre
		    if (!clients.supprimer(idClient))
		        throw new IFT287Exception("Erreur lors de la suppression d'un client.");
		}
		catch (Exception e)
		{
		    throw e;
		}
	}
}
