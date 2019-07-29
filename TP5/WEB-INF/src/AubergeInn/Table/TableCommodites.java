package AubergeInn.Table;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import AubergeInn.Connexion;
import AubergeInn.Tuple.TupleClient;
import AubergeInn.Tuple.TupleCommodite;

public class TableCommodites 
{
	private PreparedStatement stmSelect;
    private PreparedStatement stmExist;
    private PreparedStatement stmInsert;
    private PreparedStatement stmListeCommodites;

    private Connexion cx;
    
    // Fonction de connexion.
	public TableCommodites(Connexion cx) throws SQLException 
	{
		this.cx = cx;
		stmSelect = cx.getConnection().prepareStatement(
				"select * from Commodite");
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
    
    /**
	 * Fonction pour savoir si une chambre possède une commodité.
	 * @param idCommodite  l'id de la commodité.
	 * 
	 * @return Vrai si elle fait partie de la chambre, faux sinon..
     */
    public boolean existe(int idCommodite) throws SQLException
    {
    	stmExist.setInt(1, idCommodite);
    	ResultSet rst = stmExist.executeQuery(); 	
    	boolean commoditeExist = rst.next();
    	rst.close();
   
    	return commoditeExist;
    }
    
    /**
	 * Fonction pour aller chercher une commodité en BD.
	 * @param idCommodite  l'id de la commodité.
	 * 
	 * @return un tuple de la commodité contenant les données de celle-ci.
     */
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
    /**
	 * Fonction pour obtenir toutes les commodités de la BD.
	 * 
	 * @return La liste des commodités.
     */
    public List<TupleCommodite> getAllCommodites() throws SQLException
    {
    	ResultSet rset = stmSelect.executeQuery();
    	List<TupleCommodite> listeCommodite = new LinkedList<TupleCommodite>();
        while (rset.next())
        {
        	TupleCommodite tupleCommodite = new TupleCommodite();
        	tupleCommodite.setIdCommodite(rset.getInt(1));
        	tupleCommodite.setDescription(rset.getString(2));
        	tupleCommodite.setPrix(rset.getInt(3));
            
        	listeCommodite.add(tupleCommodite);
        }
        rset.close();
        return listeCommodite;
    }
    /**
	 * Fonction pour connaitre les commodités incluses dans une chambre.
	 * @param idChambre  l'id de la chambre qu'on veut savoir les commodités.
	 * 
	 * @return une liste des commodités inclues avec la chambre.
     */
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
    
    /**
	 * Fonction pour ajouter une commodité.
	 * @param idCommodite  l'id de la commodité.
	 * @param description  la description de la commodité.
	 * @param prix  le prix de la commodité.
	 * 
	 * @return une liste des commodités inclues avec la chambre.
     */
    public int ajouter(int idCommodite, String description, int prix) throws SQLException
    {
    	stmInsert.setInt(1, idCommodite);
    	stmInsert.setString(2, description);
    	stmInsert.setInt(3, prix);
    	return stmInsert.executeUpdate();
    }
}
