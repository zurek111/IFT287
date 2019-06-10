package AubergeInn.Gestionnaire;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import AubergeInn.Connexion;
import AubergeInn.IFT287Exception;
import AubergeInn.Table.TableChambres;
import AubergeInn.Table.TableClients;
import AubergeInn.Table.TableReservations;
import AubergeInn.Tuple.TupleChambre;
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
	
	public void reserver(int idClient, int idChambre, Date dateDebut, Date dateFin)
			throws SQLException, IFT287Exception
	{
		try
		{
			TupleChambre tupleChambre = chambres.getChambre(idChambre);
			if (tupleChambre == null)
	            throw new IFT287Exception("La chambre n'existe pas : " + idChambre);
 
			if (!clients.existe(idClient))
	            throw new IFT287Exception("Le client n'existe pas : " + idClient);

			
			List<TupleReservation> tupleReservation = reservations.getReservationsChambre(idChambre);
			if (tupleReservation != null)
			{
				for (TupleReservation reservation : tupleReservation)
				{
					if (dateDebut.before(reservation.getDateDebut()) || dateFin.after(reservation.getDateFin()))
							throw new IFT287Exception("Chambre déjà réservé pendnt ces dates.");
				}
			}
			
			// TODO - Calculer prixTotal
			if (reservations.ajouter(idClient, idChambre, dateDebut, dateFin, tupleChambre.getPrix()) == 0)
				throw new IFT287Exception("Erreur lors de l'ajout de la réservation.");
	       
			// Commit
            cx.commit();
		}
		catch(Exception e)
		{
			throw e;
		}
	}
}
