package AubergeInn.Gestionnaire;

import java.sql.Date;
import java.util.List;

import AubergeInn.IFT287Exception;
import AubergeInn.Table.Chambres;
import AubergeInn.Table.Clients;
import AubergeInn.Table.Commodites;
import AubergeInn.Table.Reservations;
import AubergeInn.Tuple.Chambre;
import AubergeInn.Tuple.Commodite;
import AubergeInn.Tuple.Reservation;

public class GestionReservation 
{
	private Chambres chambres;
	private Reservations reservations;
	private Clients clients;
	private Commodites commodites;
	
	public GestionReservation(Chambres chambres, Reservations reservations, Clients clients, Commodites commodites) throws IFT287Exception
	{
		if (chambres.getConnexion() != reservations.getConnexion() 
			|| reservations.getConnexion() != clients.getConnexion()
			|| clients.getConnexion() != commodites.getConnexion())
		{
            throw new IFT287Exception("Les collections d'objets n'utilisent pas la même connexion au serveur");
		}

		this.chambres = chambres;
        this.reservations = reservations;
        this.clients = clients;
        this.commodites = commodites;
	}
	
	/**
	 * Fonction pour réserver une chambre à un client.
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
			// Vérifie que les date sont des dates valides
			if (dateDebut.compareTo(dateFin) >= 0)
	            throw new IFT287Exception("La date de début doit être au moins un jour avant la date de fin.");
			
			Chambre chambre = chambres.getChambre(idChambre);
			
			// Vérifie si la chambre existe
			if (chambre == null)
	            throw new IFT287Exception("La chambre n'existe pas : " + idChambre);
			
			// Vérifie si le client existe
			if (!clients.existe(idClient))
	            throw new IFT287Exception("Le client n'existe pas : " + idClient);

			// Vérifie si la chambre est déjà réservée
			for (Reservation reservation : reservations.getReservationsChambre(idChambre))
			{
				if (!(dateFin.compareTo(reservation.getDateDebut()) <= 0 || dateDebut.compareTo(reservation.getDateFin()) >= 0))
					throw new IFT287Exception("Chambre déjà réservé pendant ces dates.");		
			}
			
			// Calcul du prix total de la chambre (prix de base + commodités)
			int prixTotal = chambre.getPrix();
			for (Commodite commodite : commodites.getCommodites(chambre))
				prixTotal += commodite.getPrix();
			
			Reservation reservation = new Reservation(idClient,idChambre,dateDebut,dateFin, prixTotal);
			
			// Ajout d'une réservation
			reservations.ajouter(reservation);
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	public List<Reservation> getReservationsClient(int idClient)
	{
		return reservations.getReservationsClient(idClient);
	}
}
