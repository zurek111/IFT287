package AubergeInn.Table;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import AubergeInn.Connexion;
import AubergeInn.Tuple.TupleChambre;
import AubergeInn.Tuple.TupleClient;

public class TableClients 
{
	private PreparedStatement stmSelect;
    private PreparedStatement stmExist;
    private PreparedStatement stmInsert;
    private PreparedStatement stmDelete;

    private Connexion cx;
    
	public TableClients(Connexion cx) throws SQLException 
	{
		this.cx = cx;
		stmSelect = cx.getConnection().prepareStatement(
				"select * from Client");
		stmExist = cx.getConnection().prepareStatement(
				"select idClient, prenom, nom, age from Client where idClient = ?");
		stmInsert = cx.getConnection().prepareStatement(
				"insert into Client (idClient, prenom, nom, age) values (?,?,?,?)");
		stmDelete = cx.getConnection().prepareStatement(
				"delete from Client where idClient = ?");
	}
	
    public Connexion getConnexion()
    {
        return cx;
    }
    
    /**
	 * Fonction pour savoir si un client fait parti de la BD.
	 * 
	 * @param idClient  l'id du client.
	 * 
	 * @return Vrai si le client existe, faux sinon.
     */
    public boolean existe(int idClient) throws SQLException
    {
    	stmExist.setInt(1, idClient);
    	ResultSet rst = stmExist.executeQuery(); 	
    	boolean clientExist = rst.next();
    	rst.close();
   
    	return clientExist;
    }
    /**
	 * Fonction pour obtenir toutes les clients de la BD.
	 * 
	 * @return La liste des clients.
     */
    public List<TupleClient> getAllClients() throws SQLException
    {
    	ResultSet rset = stmSelect.executeQuery();
    	List<TupleClient> listeClients = new LinkedList<TupleClient>();
        while (rset.next())
        {
        	TupleClient tupleClient = new TupleClient();
            tupleClient.setIdClient(rset.getInt(1));
            tupleClient.setPrenom(rset.getString(2));
            tupleClient.setNom(rset.getString(3));
            tupleClient.setAge(rset.getInt(4));
            
            listeClients.add(tupleClient);
        }
        rset.close();
        return listeClients;
    }
    
    /**
	 * Fonction pour obtenir un client de la BD.
	 * 
	 * @param idClient  l'id du client.
	 * 
	 * @return Le tuple du client contenant ses données.
     */
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
    
    /**
	 * Fonction pour ajouter un client dans la BD.
	 * 
	 * @param idClient  l'id du client.
	 * @param prenom  le nom du client.
	 * @param nom  le prenom du client.
	 * @param age  l'age du client.
	 * 
	 * @return Le nombre de lignes ajoutées dans la BD.
     */
    public int ajouter(int idClient, String prenom, String nom, int age) throws SQLException
    {
    	stmInsert.setInt(1, idClient);
    	stmInsert.setString(2, prenom);
    	stmInsert.setString(3, nom);
    	stmInsert.setInt(4, age);
    	return stmInsert.executeUpdate();
    }
    
    /**
	 * Fonction pour supprimer un client de la BD.
	 * 
	 * @param idClient  l'id du client.
	 * @param prenom  le nom du client.
	 * @param nom  le prenom du client.
	 * @param age  l'age du client.
	 * 
	 * @return Le nombre de lignes supprimées dans la BD.
     */
    public int supprimer(int idClient) throws SQLException
    {
    	stmDelete.setInt(1, idClient);
    	return stmDelete.executeUpdate();
    }
}
