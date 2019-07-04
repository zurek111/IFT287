package AubergeInn.Gestionnaire;

import java.sql.Date;

import AubergeInn.Connexion;
import AubergeInn.IFT287Exception;
import AubergeInn.Table.TableChambres;
import AubergeInn.Table.TableClients;
import AubergeInn.Table.TableReservations;
import AubergeInn.Tuple.TupleChambre;
import AubergeInn.Tuple.TupleClient;
import AubergeInn.Tuple.TupleReservation;

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
	
	/**
	 * Fonction pour reserver une chambre à un client.
	 * 
	 * @param idClient  l'id du client.
	 * @param idChambre  l'id de la chambre.
	 * @param dateDebut  La date de début de la réservation.
	 * @param dateFin  La date de fin de la réservation.
	 * 
     */
	public void reserver(int idClient, int idChambre, Date dateDebut, Date dateFin)
			throws IFT287Exception
	{
		try
		{
			cx.demarreTransaction();
			// Vérifie que les date sont des dates valides
			if (dateDebut.compareTo(dateFin) >= 0)
	            throw new IFT287Exception("La date de début doit être au moins un jour avant la date de fin.");
			
			TupleChambre tupleChambre = chambres.getChambre(idChambre);
			
			// Vérifie si la chambre existe
			if (tupleChambre == null)
	            throw new IFT287Exception("La chambre n'existe pas : " + idChambre);
			TupleClient tupleClient = clients.getClient(idClient);
			// Vérifie si le client existe
			if (tupleClient == null)
	            throw new IFT287Exception("Le client n'existe pas : " + idClient);

			for (TupleReservation reservation : tupleChambre.getReservations())
			{
				if (!(dateFin.compareTo(reservation.getDateDebut()) <= 0 || dateDebut.compareTo(reservation.getDateFin()) >= 0))
					throw new IFT287Exception("Chambre déjà réservé pendant ces dates.");		
			}
			TupleReservation reservation = new TupleReservation(tupleClient,tupleChambre,dateDebut,dateFin);
			// Ajout d'une réservation, erreur si la requête retourne 0
			if (reservations.ajouter(reservation) != reservation)
				throw new IFT287Exception("Erreur lors de l'ajout de la réservation.");
			
			tupleClient.ajouterReservation(reservation);
			tupleChambre.ajouterReservation(reservation);
			// Commit
            cx.commit();
		}
		catch(Exception e)
		{
			throw e;
		}
	}
}
