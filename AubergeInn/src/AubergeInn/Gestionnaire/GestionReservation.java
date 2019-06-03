package AubergeInn.Gestionnaire;

import java.util.Date;

import AubergeInn.Connexion;
import AubergeInn.IFT287Exception;
import AubergeInn.Table.TableChambres;
import AubergeInn.Table.TableClients;
import AubergeInn.Table.TableReservations;

public class GestionReservation 
{
	private Connexion cx;
	private TableChambres chambres;
	private TableReservations reservations;
	private TableClients clients;
	
	public GestionReservation(TableChambres chambres, TableReservations reservations, TableClients clients) throws IFT287Exception
	{
		this.cx = reservations.getConnexion();
		
		if (cx != chambres.getConnexion())
            throw new IFT287Exception("Les instances de TableReservations et de TableChambres n'utilisent pas la même connexion au serveur");
		
		if (cx != clients.getConnexion())
            throw new IFT287Exception("Les instances de TableReservations et de TableClients n'utilisent pas la même connexion au serveur");
        
		this.chambres = chambres;
        this.reservations = reservations;
        this.clients = clients;
	}
	
	public void reserver(int idClient, int idChambre, Date dateDebut, Date dateFin)
	{
		// Cette commande réserve une chambre pour un client.
	}
}
