package AubergeInn.Table;

import java.sql.*;
import AubergeInn.Connexion;
import AubergeInn.Tuple.TupleChambre;

public class TableChambres 
{
    private PreparedStatement stmExist;
    private PreparedStatement stmCommoditeIncluse;
    private PreparedStatement stmRemoveCommodite;
    private PreparedStatement stmInsert;
    private PreparedStatement stmDelete;
    private PreparedStatement stmInclude;

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
		stmInclude = cx.getConnection().prepareStatement(
				"insert into CommoditeOfferte (idChambre, idCommodite) values (?,?)");
		stmCommoditeIncluse = cx.getConnection().prepareStatement(
				"select * from CommoditeOfferte where idChambre = ? and idCommodite = ?");
		stmRemoveCommodite = cx.getConnection().prepareStatement(
				"delete from CommoditeOfferte where idChambre = ? and idCommodite = ?");
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
    
    public boolean commoditeIncluse(int idChambre, int idCommodite) throws SQLException
    {
    	stmCommoditeIncluse.setInt(1, idChambre);
    	stmCommoditeIncluse.setInt(2, idCommodite);
    	ResultSet rst = stmCommoditeIncluse.executeQuery();
    	boolean commoditeChambreExist = rst.next();
    	rst.close();
    	
    	return commoditeChambreExist;
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
    
    public int inclureCommodite(int idChambre,int idCommodite) throws SQLException
    {
    	stmInclude.setInt(1, idChambre);
    	stmInclude.setInt(2, idCommodite);
    	return stmInclude.executeUpdate();
    }
    
    public int enleverCommodite(int idChambre,int idCommodite) throws SQLException
    {
    	stmRemoveCommodite.setInt(1, idChambre);
    	stmRemoveCommodite.setInt(2, idCommodite);
    	return stmRemoveCommodite.executeUpdate();
    }
}
