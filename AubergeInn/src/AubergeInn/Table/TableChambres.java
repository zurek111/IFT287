package AubergeInn.Table;

import java.sql.*;
import AubergeInn.Connexion;
import AubergeInn.Tuple.TupleChambre;

public class TableChambres 
{
    private PreparedStatement stmExist;
    private PreparedStatement stmInsert;
    private PreparedStatement stmDelete;

    private Connexion cx;
    
	public TableChambres(Connexion cx) throws SQLException 
	{
		this.cx = cx;
		stmExist = cx.getConnection().prepareStatement(
				"select idChambre, nom, typeLit, prix from Chambre where idChambre = ?");
		stmInsert = cx.getConnection().prepareStatement(
				"insert into Chambre (idChambre, nom, typeLit, prix) values (?,?,?,?)");
		stmDelete = cx.getConnection().prepareStatement(
				"delete from Chambre where idChambre = ?");
	}
	
    public Connexion getConnexion()
    {
        return cx;
    }
    
    public boolean existe(int idChambre) throws SQLException
    {
    	stmExist.setInt(1, idChambre);
    	ResultSet rst = stmExist.executeQuery();
    	boolean chambreExist = rst.next();
    	rst.close();
    	
    	return chambreExist;
    }
    
    public TupleChambre getChambre(int idChambre) throws SQLException
    {
    	stmExist.setInt(1, idChambre);
        ResultSet rst = stmExist.executeQuery();
        if (rst.next())
        {
            TupleChambre tupleChambre = new TupleChambre();
            tupleChambre.setIdChambre(idChambre);
            tupleChambre.setNom(rst.getString(2));
            tupleChambre.setTypeLit(rst.getString(3));
            tupleChambre.setPrix(rst.getInt(4));
            rst.close();
            
            return tupleChambre;
        }
        else
        {
            return null;
        }
    }
    
    public int ajouter(int idChambre, String nom, String typeLit, int prix) throws SQLException
    {
    	stmInsert.setInt(1, idChambre);
    	stmInsert.setString(2, nom);
    	stmInsert.setString(3, typeLit);
    	stmInsert.setInt(4, prix);
    	return stmInsert.executeUpdate();
    }
    
    public int supprimer(int idChambre) throws SQLException
    {
    	stmDelete.setInt(1, idChambre);
    	return stmDelete.executeUpdate();
    }
}
