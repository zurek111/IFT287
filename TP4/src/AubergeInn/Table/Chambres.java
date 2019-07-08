package AubergeInn.Table;

import java.util.List;

import javax.persistence.TypedQuery;

import AubergeInn.Connexion;
import AubergeInn.Tuple.Chambre;

public class Chambres 
{
    private TypedQuery<Chambre> stmSelect;
    private TypedQuery<Chambre> stmExist;


    private Connexion cx;
    
    // fonctions de connexion
	public Chambres(Connexion cx)
	{
		this.cx = cx;
		
		stmSelect = cx.getConnection().createQuery(
				"select c from Chambre c", Chambre.class);
		stmExist = cx.getConnection().createQuery(
				"select c from Chambre c where c.idChambre = :idChambre", Chambre.class);
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
	 * Fonction pour obtenir toutes les chambres de la BD.
	 * 
	 * @return La liste des chambres.
     */
    public List<Chambre> getAllChambre()
    {
    	
    	List<Chambre> listeChambres = stmSelect.getResultList();
        
        return listeChambres;
    }
    
    /**
	 * Fonction pour obtenir une chambre de la BD avec son id.
	 * 
	 * @param idChambre  l'id de la chambre.
	 * 
	 * @return L'objet chambre qui contient les données de celle-ci.
     */
    public Chambre getChambre(int idChambre)
    {
    	stmExist.setParameter("idChambre", idChambre);
        List<Chambre> chambres = stmExist.getResultList();
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
	 * @param chambre La chambre à ajouter en BD
	 * 
	 * @return La chambre ajoutée dans la BD.
     */
    public Chambre ajouter(Chambre chambre)
    {
    	cx.getConnection().persist(chambre);
    	return chambre;
    }
    
    /**
	 * Fonction pour supprimer une chambre dans la BD.
	 * 
	 * @param chambre La chambre à supprimer de la BD
	 * 
	 * @return vrai si supprimées dans la BD faux sinon.
     */
    public boolean supprimer(Chambre chambre)
    {
    	if (chambre != null)
    	{
    		cx.getConnection().remove(chambre);
    		return true;
    	}
    	return false;
    }
    
}
