package AubergeInn.Table;

import java.sql.*;
import AubergeInn.Connexion;
import AubergeInn.Tuple.TupleReservation;

public class TableReservations 
{
    private PreparedStatement stmClientExist;
    private Connexion cx;
    
	public TableReservations(Connexion cx) throws SQLException 
	{
		this.cx = cx;
		stmClientExist = cx.getConnection().prepareStatement(
				"select idReservation, idChambre, dateDebut, dateFin "
				+ "from Reservation where idClient = ?");
	}
	
	public Connexion getConnexion()
    {
        return cx;
    }
	
	public TupleReservation getReservationsClient(int idClient) throws SQLException
	{
		stmClientExist.setInt(1, idClient);
		ResultSet rst = stmClientExist.executeQuery();
		if (rst.next())
		{
            TupleReservation tupleReservation = new TupleReservation();
            tupleReservation.setIdReservation(rst.getInt(1));
            tupleReservation.setIdChambre(rst.getInt(2));
            tupleReservation.setIdClient(rst.getInt(3));
            tupleReservation.setDateDebut(rst.getDate(4));
            tupleReservation.setDateFin(rst.getDate(4));

            return tupleReservation;
		}
		else
		{
			return null;
		}
	}
}
