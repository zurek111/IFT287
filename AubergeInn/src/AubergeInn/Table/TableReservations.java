package AubergeInn.Table;

import java.sql.*;
import AubergeInn.Connexion;
import AubergeInn.Tuple.TupleReservation;

public class TableReservations 
{
    private PreparedStatement stmClientExiste;
    private PreparedStatement stmChambreExiste;

    private Connexion cx;
    
	public TableReservations(Connexion cx) throws SQLException 
	{
		this.cx = cx;
		stmClientExiste = cx.getConnection().prepareStatement(
				"select * from Reservation where idClient = ?");
		stmChambreExiste = cx.getConnection().prepareStatement(
				"select * from Reservation where idChambre = ?");
	}
	
	public Connexion getConnexion()
    {
        return cx;
    }
	
	public TupleReservation getReservationsClient(int idClient) throws SQLException
	{
		stmClientExiste.setInt(1, idClient);
		ResultSet rst = stmClientExiste.executeQuery();
		if (rst.next())
		{
            TupleReservation tupleReservation = new TupleReservation();
            tupleReservation.setIdReservation(rst.getInt(1));
            tupleReservation.setIdChambre(rst.getInt(2));
            tupleReservation.setIdClient(rst.getInt(3));
            tupleReservation.setDateDebut(rst.getDate(4));
            tupleReservation.setDateFin(rst.getDate(5));
            tupleReservation.setPrixTotal(rst.getInt(6));

            return tupleReservation;
		}
		else
		{
			return null;
		}
	}
	
	public TupleReservation getReservationsChambre(int idChambre) throws SQLException
	{
		stmChambreExiste.setInt(1, idChambre);
		ResultSet rst = stmChambreExiste.executeQuery();
		if (rst.next())
		{
            TupleReservation tupleReservation = new TupleReservation();
            tupleReservation.setIdReservation(rst.getInt(1));
            tupleReservation.setIdChambre(rst.getInt(2));
            tupleReservation.setIdClient(rst.getInt(3));
            tupleReservation.setDateDebut(rst.getDate(4));
            tupleReservation.setDateFin(rst.getDate(5));
            tupleReservation.setPrixTotal(rst.getInt(6));

            return tupleReservation;
		}
		else
		{
			return null;
		}
	}
}
