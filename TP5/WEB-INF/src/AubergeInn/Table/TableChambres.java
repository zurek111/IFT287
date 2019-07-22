package AubergeInn.Table;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import AubergeInn.Connexion;
import AubergeInn.Tuple.TupleChambre;

public class TableChambres 
{
    private PreparedStatement stmSelect;
    private PreparedStatement stmExist;
    private PreparedStatement stmCommoditeIncluse;
    private PreparedStatement stmRemoveCommodite;
    private PreparedStatement stmInsert;
    private PreparedStatement stmDelete;
    private PreparedStatement stmInclude;

    private Connexion cx;
    
    // fonctions de connexion
	public TableChambres(Connexion cx) throws SQLException 
	{
		this.cx = cx;
		
		stmSelect = cx.getConnection().prepareStatement(
				"select * from Chambre");
		stmExist = cx.getConnection().prepareStatement(
				"select * from Chambre where idChambre = ?");
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
    
	/**
	 * Fonction pour savoir si une chambre existe dans la BD.
	 * 
	 * @param idChambre  l'id de la chambre.
	 * 
	 * @return Vrai si la chambre existe, faux sinon.
     */
    public boolean existe(int idChambre) throws SQLException
    {
    	stmExist.setInt(1, idChambre);
    	ResultSet rst = stmExist.executeQuery();
    	boolean chambreExist = rst.next();
    	rst.close();
    	
    	return chambreExist;
    }
    
    /**
	 * Fonction pour savoir si une commodité est incluse dans une chambre.
	 * 
	 * @param idChambre  l'id de la chambre.
	 * @param idCommodite  l'id de la commodité.
	 * 
	 * @return Vrai si la commodité est incluse dans la chambre, faux sinon.
     */
    public boolean commoditeIncluse(int idChambre, int idCommodite) throws SQLException
    {
    	stmCommoditeIncluse.setInt(1, idChambre);
    	stmCommoditeIncluse.setInt(2, idCommodite);
    	ResultSet rst = stmCommoditeIncluse.executeQuery();
    	boolean commoditeChambreExist = rst.next();
    	rst.close();
    	
    	return commoditeChambreExist;
    }
    /**
	 * Fonction pour obtenir toutes les chambres de la BD.
	 * 
	 * @return La liste des chambres.
     */
    public List<TupleChambre> getAllChambre() throws SQLException
    {
    	ResultSet rset = stmSelect.executeQuery();
    	List<TupleChambre> listeChambres = new LinkedList<TupleChambre>();
        while (rset.next())
        {
        	TupleChambre chambre = new TupleChambre(rset.getInt("idChambre"),
                                                    rset.getString("nom"),
                                                    rset.getString("typeLit"),
                                                    rset.getInt("prix"));
            
        	listeChambres.add(chambre);
        }
        rset.close();
        return listeChambres;
    }
    
    /**
	 * Fonction pour obtenir une chambre de la BD avec son id.
	 * 
	 * @param idChambre  l'id de la chambre.
	 * 
	 * @return Le tuple de la chambre qui contient les données de celle-ci.
     */
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
    
    /**
	 * Fonction pour ajouter une chambre dans la BD.
	 * 
	 * @param idChambre  l'id de la chambre.
	 * @param nom  le nom de la chambre.
	 * @param typeLit  le type de lit de la chambre.
	 * @param prix  le prix de la chambre.
	 * 
	 * @return Le nombre de lignes ajoutées dans la BD.
     */
    public int ajouter(int idChambre, String nom, String typeLit, int prix) throws SQLException
    {
    	stmInsert.setInt(1, idChambre);
    	stmInsert.setString(2, nom);
    	stmInsert.setString(3, typeLit);
    	stmInsert.setInt(4, prix);
    	return stmInsert.executeUpdate();
    }
    
    /**
	 * Fonction pour supprimer une chambre dans la BD.
	 * 
	 * @param idChambre  l'id de la chambre.
	 * 
	 * @return Le nombre de lignes supprimées dans la BD.
     */
    public int supprimer(int idChambre) throws SQLException
    {
    	stmDelete.setInt(1, idChambre);
    	return stmDelete.executeUpdate();
    }
    
    /**
	 * Fonction pour inclure une commodité dans une chambre.
	 * 
	 * @param idChambre  l'id de la chambre.
	 * @param idCommodite  l'id de la commodité.
	 * 
	 * @return Le nombre de lignes ajoutées dans la BD.
     */
    public int inclureCommodite(int idChambre,int idCommodite) throws SQLException
    {
    	stmInclude.setInt(1, idChambre);
    	stmInclude.setInt(2, idCommodite);
    	return stmInclude.executeUpdate();
    }
    
    /**
	 * Fonction pour retirer une commodité d'une chambre.
	 * 
	 * @param idChambre  l'id de la chambre.
	 * @param idCommodite  l'id de la commodité.
	 * 
	 * @return Le nombre de lignes supprimées dans la BD.
     */
    public int enleverCommodite(int idChambre,int idCommodite) throws SQLException
    {
    	stmRemoveCommodite.setInt(1, idChambre);
    	stmRemoveCommodite.setInt(2, idCommodite);
    	return stmRemoveCommodite.executeUpdate();
    }
}
