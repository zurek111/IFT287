package AubergeInn.Table;

import java.sql.*;
import AubergeInn.Connexion;
import AubergeInn.Tuple.TupleClient;

public class TableClients 
{
    private PreparedStatement stmExist;
    private PreparedStatement stmInsert;
    private PreparedStatement stmDelete;

    private Connexion cx;
    
	public TableClients(Connexion cx) throws SQLException 
	{
		this.cx = cx;
		stmExist = cx.getConnection().prepareStatement(
				"select idClient, Prenom, Nom, Age from Client where idClient = ?");
		stmInsert = cx.getConnection().prepareStatement(
				"insert into Client (idClient, Prenom, Nom, Age) values (?,?,?,?)");
		stmDelete = cx.getConnection().prepareStatement(
				"delete from Client where idClient = ?");
	}
	
    public Connexion getConnexion()
    {
        return cx;
    }
    
    public boolean existe(int idClient) throws SQLException
    {
    	stmExist.setInt(1, idClient);
    	ResultSet rst = stmExist.executeQuery(); 	
    	boolean clientExist = rst.next();
    	rst.close();
   
    	return clientExist;
    }
    
    public TupleClient getClient(int idClient) throws SQLException
    {
    	stmExist.setInt(1, idClient);
        ResultSet rst = stmExist.executeQuery();
        if (rst.next())
        {
            TupleClient tupleClient = new TupleClient();
            tupleClient.setIdClient(idClient);
            tupleClient.setPrenom(rst.getString(2));
            tupleClient.setNom(rst.getString(3));
            tupleClient.setAge(rst.getInt(4));
            rst.close();
            
            return tupleClient;
        }
        else
        {
            return null;
        }
    }
    
    public int ajouter(int idClient, String prenom, String nom, int age) throws SQLException
    {
    	stmInsert.setInt(1, idClient);
    	stmInsert.setString(2, prenom);
    	stmInsert.setString(3, nom);
    	stmInsert.setInt(4, age);
    	return stmInsert.executeUpdate();
    }
    
    public int supprimer(int idClient) throws SQLException
    {
    	stmDelete.setInt(1, idClient);
    	return stmDelete.executeUpdate();
    }
}
