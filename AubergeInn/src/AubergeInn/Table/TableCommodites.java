package AubergeInn.Table;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import AubergeInn.Connexion;
import AubergeInn.Tuple.TupleCommodite;

public class TableCommodites 
{
    private PreparedStatement stmExist;
    private PreparedStatement stmInsert;
    private PreparedStatement stmListeCommodites;

    private Connexion cx;
    
	public TableCommodites(Connexion cx) throws SQLException 
	{
		this.cx = cx;
		stmExist = cx.getConnection().prepareStatement(
				"select idCommodite, description, prix from Commodite where idCommodite = ?");
		
		stmInsert = cx.getConnection().prepareStatement(
				"insert into Commodite (idCommodite, description, prix) values (?,?,?)");
		
		stmListeCommodites = cx.getConnection().prepareStatement(
				"select c.idCommodite, c.description, c.prix FROM Commodite as c\r\n" + 
				"INNER JOIN CommoditeOfferte as co ON c.idCommodite = co.idCommodite\r\n" + 
				"where idChambre = ?");
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
    
    public List<TupleCommodite> getCommoditesChambre(int idChambre) throws SQLException
    {
    	stmListeCommodites.setInt(1, idChambre);
    	ResultSet rset = stmListeCommodites.executeQuery();
        List<TupleCommodite> listeCommodites = new LinkedList<TupleCommodite>();
        
        while (rset.next())
        {
        	TupleCommodite tupleCommodite = new TupleCommodite(rset.getInt("idCommodite"),
        													   rset.getString("description"),
        													   rset.getInt("prix"));
        	
        	listeCommodites.add(tupleCommodite);
        }
        rset.close();
        return listeCommodites;
    }
    
    public int ajouter(int idCommodite, String description, int age) throws SQLException
    {
    	stmInsert.setInt(1, idCommodite);
    	stmInsert.setString(2, description);
    	stmInsert.setInt(3, age);
    	return stmInsert.executeUpdate();
    }
}
