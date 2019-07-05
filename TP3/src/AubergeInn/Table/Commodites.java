package AubergeInn.Table;

import java.util.List;

import javax.persistence.TypedQuery;

import AubergeInn.Connexion;
import AubergeInn.Tuple.Commodite;

public class Commodites 
{
    private TypedQuery<Commodite> stmExist;

    private Connexion cx;
    
    // Fonction de connexion.
	public Commodites(Connexion cx) 
	{
		this.cx = cx;
		stmExist = cx.getConnection().createQuery(
				"select c from Commodite c where c.idCommodite = :idCommodite", Commodite.class);
		
		/*stmListeCommodites = cx.getConnection().prepareStatement(
				"select c.idCommodite, c.description, c.prix FROM Commodite as c\r\n" + 
				"INNER JOIN CommoditeOfferte as co ON c.idCommodite = co.idCommodite\r\n" + 
				"where idChambre = ?");*/
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
    public boolean existe(int idCommodite)
    {
    	stmExist.setParameter("idCommodite", idCommodite);
   
    	return !stmExist.getResultList().isEmpty();
    }
    
    /**
	 * Fonction pour aller chercher une commodité en BD.
	 * @param idCommodite  l'id de la commodité.
	 * 
	 * @return un tuple de la commodité contenant les données de celle-ci.
     */
    public Commodite getCommodite(int idCommodite)
    {
    	stmExist.setParameter("idCommodite", idCommodite);
    	List<Commodite> commodites = stmExist.getResultList();
        if (!commodites.isEmpty())
        {
            return commodites.get(0);
        }
        else
        {
            return null;
        }
    }
    
    /**
	 * Fonction pour connaitre les commodités incluses dans une chambre.
	 * @param idChambre  l'id de la chambre qu'on veut savoir les commodités.
	 * 
	 * @return une liste des commodités inclues avec la chambre.
     */
    /*public List<TupleCommodite> getCommoditesChambre(int idChambre) throws SQLException
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
    }*/
    
    /**
	 * Fonction pour ajouter une commodité.
	 * @param idCommodite  l'id de la commodité.
	 * @param description  la description de la commodité.
	 * @param prix  le prix de la commodité.
	 * 
	 * @return retourne la commodité.
     */
    public Commodite ajouter(Commodite commodite)
    {
    	cx.getConnection().persist(commodite);
    	return commodite;
    }
}
