package AubergeInn.Gestionnaire;

import java.time.LocalDate;
import java.sql.Date;
import java.util.List;

import AubergeInn.Connexion;
import AubergeInn.IFT287Exception;
import AubergeInn.Table.TableChambres;
import AubergeInn.Table.TableCommodites;
import AubergeInn.Tuple.TupleChambre;
import AubergeInn.Tuple.TupleCommodite;
import AubergeInn.Tuple.TupleReservation;

public class GestionChambre 
{
	private Connexion cx;
	private TableChambres chambres;
	private TableCommodites commodites;
	
	// Fonction de connexion pour la chambre
	public GestionChambre(TableChambres chambres, TableCommodites commodites) throws IFT287Exception
	{
		this.cx = chambres.getConnexion();
		
		if (cx != commodites.getConnexion())
            throw new IFT287Exception("Les instances de TableChambres et de TableCommodites n'utilisent pas la même connexion au serveur");
		
		this.chambres = chambres;
		this.commodites = commodites;
	}
	
	/**
	 * Fonction pour ajouter une chambre.
	 * 
	 * @param idChambre  l'id de la chambre.
	 * @param nom  le nom de la chambre.
	 * @param type  le type de la chambre.
	 * @param prix  le prix de la chambre.
	 * 
     */
	public void ajouterChambre(int idChambre, String nom, String type, int prix)
			throws IFT287Exception
	{
		try
        {
			TupleChambre chambre = new TupleChambre(idChambre, nom, type, prix);
			// Vérifie le prix de la chambre
			if (prix < 0)
                throw new IFT287Exception("Le prix de la chambre doit être supérieur ou égal à zéro.");
			
			// Vérifie que le nom est valide
			if (nom.isEmpty() || type.isEmpty())
                throw new IFT287Exception("Le nom de la chambre doit être valide.");
            
			// Vérifie que le type est valide
			if (type.isEmpty())
                throw new IFT287Exception("Le type de la chambre doit être valide.");
			
			// Vérifie si la chambre existe déja
            if (chambres.existe(idChambre))
                throw new IFT287Exception("La chambre existe déjà: " + idChambre);

            // Ajout de la chambre, erreur si la requête retourne 0
            if (chambres.ajouter(chambre) != chambre)
            	throw new IFT287Exception("Erreur lors de l'ajout d'une chambre à la table.");
            
            // Commit
            cx.commit();
        }
        catch (Exception e)
        {
            cx.rollback();
            throw e;
        }
	}
	
	/**
	 * Fonction pour supprimer une chambre.
	 * 
	 * @param idChambre  l'id de la chambre.
	 * 
     */
	public void supprimerChambre(int idChambre)
			throws IFT287Exception
	{
		try
        {    
			cx.demarreTransaction();
			TupleChambre chambre = chambres.getChambre(idChambre);
            // Verifie si la chambre est existant
            if (chambre == null)
                throw new IFT287Exception("Chambre inexistante: " + idChambre);
            
            for (TupleReservation reservation : chambre.getReservations())
            {
            	LocalDate localDate = LocalDate.now();
    			Date date = Date.valueOf(localDate);
            	if (!reservation.getDateFin().before(date))
            		throw new IFT287Exception("Chambre " + idChambre + " a des réservations");
            }

            // Suppression de la chambre
            if (!chambres.supprimer(chambre))
                throw new IFT287Exception("Erreur lors de la suppression d'une chambre.");
            
            // Commit
            cx.commit();
        }
        catch (Exception e)
        {
            cx.rollback();
            throw e;
        }
	}
	
	/**
	 * Fonction pour ajouter une commodité dans une chambre.
	 * 
	 * @param idChambre  l'id de la chambre.
	 * @param idCommodite  l'id de la commodité.
	 * 
     */
	public void inclureCommodite(int idChambre, int idCommodite)
			throws IFT287Exception
	{
		try
		{
			cx.demarreTransaction();
			TupleChambre chambre = chambres.getChambre(idChambre);
			// Vérifie si la chambre existe
			if (chambre == null)
	            throw new IFT287Exception("La chambre n'existe pas : " + idChambre);
	        
			TupleCommodite commodite = commodites.getCommodite(idCommodite);
			// Vérifie si la commodité existe
	        if (commodite == null)
	            throw new IFT287Exception("La commodité n'existe pas : " + idCommodite);
	        
	        // Vérifie si la chambre possède déjà la commodité 
	        if (chambre.getCommodites().contains(commodite))
	        	throw new IFT287Exception("La chambre " + idChambre + " possède déjà la commodité " + idCommodite + ".");      
	        
	        // Inclus la commodité à la chambre, erreur si la requête retourne 0
            chambre.ajouterCommodite(commodite);
            
            // Commit
            cx.commit();
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	/**
	 * Fonction pour enlever une commodité dans une chambre.
	 * 
	 * @param idChambre  l'id de la chambre.
	 * @param idCommodite  l'id de la commodité.
	 * 
     */
	public void enleverCommodite(int idChambre, int idCommodite)
			throws IFT287Exception
	{
		try
		{
			cx.demarreTransaction();
			TupleChambre chambre = chambres.getChambre(idChambre);
			// Vérifie si la chambre existe
			if (chambre == null)
	            throw new IFT287Exception("La chambre n'existe pas : " + idChambre);
			TupleCommodite commodite = commodites.getCommodite(idCommodite);
			// Vérifie si la commodité existe
	        if (commodite == null)
	            throw new IFT287Exception("La commodité n'existe pas : " + idCommodite);
	        
	        // Vérifie si la chambre offre la commodité
	        if (!chambre.getCommodites().contains(commodite))
	            throw new IFT287Exception("La chambre " + idChambre + " ne possède pas la commodité " + idCommodite + ".");
            
	        // Enlève la commodite de la chambre
	        chambre.enleverCommodite(commodite);
            	        
	        // Commit
            cx.commit();
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	/**
	 * Fonction pour aller chercher une chambre dans la BD et calculer son prix total.
	 * 
	 * @param idChambre  l'id de la chambre.
	 * 
	 * @return le tuple de la chambre contenant les données de celle-ci.
     */
	public TupleChambre getChambre(int idChambre)
			throws IFT287Exception
	{
		try
		{
			cx.demarreTransaction();
			TupleChambre tupleChambre = chambres.getChambre(idChambre);
			if (tupleChambre == null)
				throw new IFT287Exception("La chambre n'existe pas : " + idChambre);
			
			cx.commit();
			return tupleChambre;
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	/**
	 * Fonction pour aller chercher toutes les chambres dans la BD.
	 * 
	 * @return la listes des chambres et les données de celles-ci.
     */
	public List<TupleChambre> getAllChambres()
			throws IFT287Exception
	{
		try
		{
			cx.demarreTransaction();
			List<TupleChambre> listeChambres = chambres.getAllChambre();
			
			if (listeChambres.isEmpty())
				throw new IFT287Exception("Aucune chambre");
			cx.commit();
			return listeChambres;
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	/**
	 * Fonction pour trouver les chambres libres en date d'aujourd'hui.
	 * 
	 * @return la listes des chambres libres.
     */
	public List<TupleChambre> getChambresLibres()
			throws IFT287Exception
	{
		try
		{
			cx.demarreTransaction();
			List<TupleChambre> listeChambres = chambres.getAllChambre();

			if (listeChambres.isEmpty())
				throw new IFT287Exception("Aucune chambre n'existe");
			
			LocalDate localDate = LocalDate.now();
			Date date = Date.valueOf(localDate);

			for (int i = 0; i < listeChambres.size(); ++i)
			{
				for (TupleReservation reservation : listeChambres.get(i).getReservations())
				{
					if (!(date.compareTo(reservation.getDateFin()) >= 0))
					{
						listeChambres.remove(i);
						--i;
						break;
					}	
				}
			}
					
			if (listeChambres.isEmpty())
				throw new IFT287Exception("Aucune chambre n'est libre");
			
			for (TupleChambre chambre : listeChambres)
			{

				int prixCommodites = 0;
					
				for (TupleCommodite commodite : chambre.getCommodites())
					prixCommodites += commodite.getPrix();
					
				chambre.setPrix(chambre.getPrix() + prixCommodites);	
			}
			cx.commit();
			return listeChambres;
		}
		catch(Exception e)
		{
			throw e;
		}
	}
}
