package AubergeInn.Table;

import java.sql.*;
import AubergeInn.Connexion;
import AubergeInn.Tuple.TupleCommodite;

public class TableCommodites 
{
    private PreparedStatement stmExist;
    private PreparedStatement stmInsert;

    private Connexion cx;
    
	public TableCommodites(Connexion cx) throws SQLException 
	{
		this.cx = cx;
		stmExist = cx.getConnection().prepareStatement(
				"select idCommodite, description, prix from Commodite where idCommodite = ?");
		
		stmInsert = cx.getConnection().prepareStatement(
				"insert into Commodite (idCommodite, description, prix) values (?,?,?)");
	}
	
    public Connexion getConnexion()
    {
        return cx;
    }
    
    public boolean existe(int idCommodite) throws SQLException
    {
    	stmExist.setInt(1, idCommodite);
    	ResultSet rst = stmExist.executeQuery(); 	
    	boolean commoditeExist = rst.next();
    	rst.close();
   
    	return commoditeExist;
    }
    
    public TupleCommodite getCommodite(int idCommodite) throws SQLException
    {
    	stmExist.setInt(1, idCommodite);
        ResultSet rst = stmExist.executeQuery();
        if (rst.next())
        {
        	TupleCommodite tupleCommodite = new TupleCommodite();
        	tupleCommodite.setIdCommodite(idCommodite);
        	tupleCommodite.setDescription(rst.getString(2));
        	tupleCommodite.setPrix(rst.getInt(3));
            rst.close();
            
            return tupleCommodite;
        }
        else
        {
            return null;
        }
    }
    
    public int ajouter(int idCommodite, String description, int age) throws SQLException
    {
    	stmInsert.setInt(1, idCommodite);
    	stmInsert.setString(2, description);
    	stmInsert.setInt(3, age);
    	return stmInsert.executeUpdate();
    }
}
