package AubergeInn.Table;

import AubergeInn.Connexion;
import AubergeInn.Tuple.Reservation;

public class Reservations 
{
    /*private TypedQuery<TupleReservation> stmReservationClient;
    private PreparedStatement stmReservationChambre;
    private PreparedStatement stmInsert;*/


    private Connexion cx;
    
    // Fonction de connexion
	public Reservations(Connexion cx) 
	{
		this.cx = cx;
		
	}
	
	public Connexion getConnexion()
    {
        return cx;
    }
	
	/**
	 * Fonction pour obtenir la liste des réservations d'un client.
	 * @param idClient  l'id du client.
	 * 
	 * @return La liste des réservations.
     */
	/*public List<TupleReservation> getReservationsClient(int idClient) throws SQLException
	{
		stmReservationClient.setInt(1, idClient);
		ResultSet rst = stmReservationClient.executeQuery();
        List<TupleReservation> listeReservations = new LinkedList<TupleReservation>();

		while (rst.next())
		{
            TupleReservation tupleReservation = new TupleReservation();
            tupleReservation.setIdReservation(rst.getInt(1));
            tupleReservation.setIdClient(rst.getInt(2));
            tupleReservation.setIdChambre(rst.getInt(3));
            tupleReservation.setDateDebut(rst.getDate(4));
            tupleReservation.setDateFin(rst.getDate(5));
            tupleReservation.setPrixTotal(rst.getInt(6));
            
            listeReservations.add(tupleReservation);
		}
		
		return listeReservations;
	}*/
	
	/**
	 * Fonction pour obtenir la liste des réservations d'une chambre.
	 * @param idChambre  l'id de la chambre.
	 * 
	 * @return La liste des réservations.
     */
	/*public List<TupleReservation> getReservationsChambre(int idChambre) throws SQLException
	{
		stmReservationChambre.setInt(1, idChambre);
		ResultSet rst = stmReservationChambre.executeQuery();
        List<TupleReservation> listeReservations = new LinkedList<TupleReservation>();

		while (rst.next())
		{
            TupleReservation tupleReservation = new TupleReservation();
            tupleReservation.setIdReservation(rst.getInt(1));
            tupleReservation.setIdClient(rst.getInt(2));
            tupleReservation.setIdChambre(rst.getInt(3));
            tupleReservation.setDateDebut(rst.getDate(4));
            tupleReservation.setDateFin(rst.getDate(5));
            tupleReservation.setPrixTotal(rst.getInt(6));
            
            listeReservations.add(tupleReservation);
		}	
		
		return listeReservations;
	}*/
	/**
	 * Fonction pour ajouter une réservation.
	 * @param idClient  l'id du client.
	 * @param idChambre  l'id de la chambre.
	 * @param dateDebut  date du début de la réservation.
	 * @param dateFin  date de fin de la réservation.
	 * @param prixTotal  Prix de la réservation.
	 * 
	 * @return La reservation ajoutée.
     */
	public Reservation ajouter(Reservation reservation)
	{
    	cx.getConnection().persist(reservation);
    	
    	return reservation;
	}
}
