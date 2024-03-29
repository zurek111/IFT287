package AubergeInn.Table;

import AubergeInn.Connexion;
import AubergeInn.Tuple.TupleReservation;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class TableReservations 
{
    private PreparedStatement stmReservationClient;
    private PreparedStatement stmReservationChambre;
    private PreparedStatement stmInsert;


    private Connexion cx;
    
    // Fonction de connexion
	public TableReservations(Connexion cx) throws SQLException 
	{
		this.cx = cx;
		stmReservationClient = cx.getConnection().prepareStatement(
				"select * from Reservation where idClient = ? order by dateDebut, dateFin");
		stmReservationChambre = cx.getConnection().prepareStatement(
				"select * from Reservation where idChambre = ?");
		stmInsert = cx.getConnection().prepareStatement(
				"insert into Reservation "
				+ "(idClient, idChambre, dateDebut, dateFin, prixTotal) values (?,?,?,?,?)");
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
	public List<TupleReservation> getReservationsClient(int idClient) throws SQLException
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
	}
	
	/**
	 * Fonction pour obtenir la liste des réservations d'une chambre.
	 * @param idChambre  l'id de la chambre.
	 * 
	 * @return La liste des réservations.
     */
	public List<TupleReservation> getReservationsChambre(int idChambre) throws SQLException
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
	}
	/**
	 * Fonction pour ajouter une réservation.
	 * @param idClient  l'id du client.
	 * @param idChambre  l'id de la chambre.
	 * @param dateDebut  date du début de la réservation.
	 * @param dateFin  date de fin de la réservation.
	 * @param prixTotal  Prix de la réservation.
	 * 
	 * @return Le nombre de lignes ajoutées.
     */
	public int ajouter(int idClient, int idChambre, Date dateDebut, Date dateFin, int prixTotal) throws SQLException
	{
    	stmInsert.setInt(1, idClient);
    	stmInsert.setInt(2, idChambre);
    	stmInsert.setDate(3, dateDebut);
    	stmInsert.setDate(4, dateFin);
    	stmInsert.setInt(5, prixTotal);
    	
    	return stmInsert.executeUpdate();
	}
}
