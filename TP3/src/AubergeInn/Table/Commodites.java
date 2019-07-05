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
	}
	
    public Connexion getConnexion()
    {
        return cx;
    }
    
    /**
	 * Fonction pour savoir si une commodité existe.
	 * @param idCommodite  l'id de la commodité.
	 * 
	 * @return Vrai si elle fait existe en BD, faux sinon..
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
	 * @return un object commodité contenant les données de celle-ci.
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
	 * Fonction pour ajouter une commodité.
	 * @param commodite l'objet commodité à ajouter.
	 * 
	 * @return retourne l'objet commodité ajouté.
     */
    public Commodite ajouter(Commodite commodite)
    {
    	cx.getConnection().persist(commodite);
    	return commodite;
    }
}
