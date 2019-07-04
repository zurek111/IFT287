package AubergeInn.Table;

import java.sql.*;
import java.util.List;

import javax.persistence.TypedQuery;

import AubergeInn.Connexion;
import AubergeInn.Tuple.TupleChambre;

public class TableChambres 
{
    private TypedQuery<TupleChambre> stmSelect;
    private TypedQuery<TupleChambre> stmExist;


    private Connexion cx;
    
    // fonctions de connexion
	public TableChambres(Connexion cx)
	{
		this.cx = cx;
		
		stmSelect = cx.getConnection().createQuery(
				"select c from Chambre c",TupleChambre.class);
		stmExist = cx.getConnection().createQuery(
				"select c from Chambre c where c.idChambre = :idChambre",TupleChambre.class);
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
    public boolean existe(int idChambre)
    {
    	stmExist.setParameter("idChambre", idChambre);
    	
    	return !stmExist.getResultList().isEmpty();
    }
    
    /**
	 * Fonction pour savoir si une commodité est incluse dans une chambre.
	 * 
	 * @param idChambre  l'id de la chambre.
	 * @param idCommodite  l'id de la commodité.
	 * 
	 * @return Vrai si la commodité est incluse dans la chambre, faux sinon.
     */
    /*public boolean commoditeIncluse(int idChambre, int idCommodite) throws SQLException
    {
    	stmCommoditeIncluse.setInt(1, idChambre);
    	stmCommoditeIncluse.setInt(2, idCommodite);
    	ResultSet rst = stmCommoditeIncluse.executeQuery();
    	boolean commoditeChambreExist = rst.next();
    	rst.close();
    	
    	return commoditeChambreExist;
    }*/
    /**
	 * Fonction pour obtenir toutes les chambres de la BD.
	 * 
	 * @return La liste des chambres.
     */
    public List<TupleChambre> getAllChambre()
    {
    	
    	List<TupleChambre> listeChambres = stmSelect.getResultList();
        
        return listeChambres;
    }
    
    /**
	 * Fonction pour obtenir une chambre de la BD avec son id.
	 * 
	 * @param idChambre  l'id de la chambre.
	 * 
	 * @return Le tuple de la chambre qui contient les données de celle-ci.
     */
    public TupleChambre getChambre(int idChambre)
    {
    	stmExist.setParameter("idChambre", idChambre);
        List<TupleChambre> chambres = stmExist.getResultList();
        if (!chambres.isEmpty())
        {
            return chambres.get(0);
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
	 * @return La chambre ajoutée dans la BD.
     */
    public TupleChambre ajouter(TupleChambre chambre)
    {
    	cx.getConnection().persist(chambre);
    	return chambre;
    }
    
    /**
	 * Fonction pour supprimer une chambre dans la BD.
	 * 
	 * @param idChambre  l'id de la chambre.
	 * 
	 * @return vrai si supprimées dans la BD faux sinon.
     */
    public boolean supprimer(TupleChambre chambre) throws SQLException
    {
    	if (chambre != null)
    	{
    		cx.getConnection().remove(chambre);
    		return true;
    	}
    	return false;
    }
    
    /**
	 * Fonction pour inclure une commodité dans une chambre.
	 * 
	 * @param idChambre  l'id de la chambre.
	 * @param idCommodite  l'id de la commodité.
	 * 
	 * @return Le nombre de lignes ajoutées dans la BD.
     */
    /*public int inclureCommodite(int idChambre,int idCommodite) throws SQLException
    {
    	stmInclude.setInt(1, idChambre);
    	stmInclude.setInt(2, idCommodite);
    	return stmInclude.executeUpdate();
    }*/
    
    /**
	 * Fonction pour retirer une commodité d'une chambre.
	 * 
	 * @param idChambre  l'id de la chambre.
	 * @param idCommodite  l'id de la commodité.
	 * 
	 * @return Le nombre de lignes supprimées dans la BD.
     */
    /*public int enleverCommodite(int idChambre,int idCommodite) throws SQLException
    {
    	stmRemoveCommodite.setInt(1, idChambre);
    	stmRemoveCommodite.setInt(2, idCommodite);
    	return stmRemoveCommodite.executeUpdate();
    }*/
}
