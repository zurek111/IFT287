package AubergeInn.Gestionnaire;

import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import AubergeInn.Connexion;
import AubergeInn.IFT287Exception;
import AubergeInn.Table.TableChambres;
import AubergeInn.Table.TableCommodites;
import AubergeInn.Table.TableReservations;
import AubergeInn.Tuple.TupleChambre;
import AubergeInn.Tuple.TupleCommodite;
import AubergeInn.Tuple.TupleReservation;

public class GestionChambre 
{
	private Connexion cx;
	private TableChambres chambres;
	private TableCommodites commodites;
	private TableReservations reservations;
	
	// Fonction de connexion pour la chambre
	public GestionChambre(TableChambres chambres, TableCommodites commodites, TableReservations reservations) throws IFT287Exception
	{
		this.cx = chambres.getConnexion();
		
		if (cx != commodites.getConnexion())
            throw new IFT287Exception("Les instances de TableChambres et de TableCommodites n'utilisent pas la même connexion au serveur");
		
		if (cx != reservations.getConnexion())
            throw new IFT287Exception("Les instances de TableChambres et de TableReservations n'utilisent pas la même connexion au serveur");

		this.chambres = chambres;
		this.commodites = commodites;
        this.reservations = reservations;
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
			throws SQLException, IFT287Exception
	{
		try
        {
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
            if (chambres.ajouter(idChambre, nom, type, prix) == 0)
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
			throws SQLException, IFT287Exception
	{
		try
        {    
            // Verifie si la chambre est existant
            if (!chambres.existe(idChambre))
                throw new IFT287Exception("Chambre inexistante: " + idChambre);
            
            // Verifie si la chambre a des réservations
            List<TupleReservation> listeReservations = reservations.getReservationsChambre(idChambre);
            
            for (TupleReservation reservation : listeReservations)
            {
            	LocalDate localDate = LocalDate.now();
    			Date date = Date.valueOf(localDate);
            	if (!reservation.getDateFin().before(date))
            		throw new IFT287Exception("Chambre " + idChambre + " a des réservations");
            }

            // Suppression de la chambre
            if (chambres.supprimer(idChambre) == 0)
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
			throws SQLException, IFT287Exception
	{
		try
		{
			// Vérifie si la chambre existe
			if (!chambres.existe(idChambre))
	            throw new IFT287Exception("La chambre n'existe pas : " + idChambre);
	        
			// Vérifie si la commodité existe
	        if (!commodites.existe(idCommodite))
	            throw new IFT287Exception("La commodité n'existe pas : " + idCommodite);
	        
	        // Vérifie si la chambre 
	        if (chambres.commoditeIncluse(idChambre, idCommodite))
	            throw new IFT287Exception("La chambre " + idChambre + " possède déjà la commodité " + idCommodite + ".");
	        
	        // Inclus la commodité à la chambre, erreur si la requête retourne 0
            if (chambres.inclureCommodite(idChambre, idCommodite) == 0)
                throw new IFT287Exception("Erreur lors de l'ajout d'une commodité à la chambre.");
            
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
			throws SQLException, IFT287Exception
	{
		try
		{
			// Vérifie si la chambre existe
			if (!chambres.existe(idChambre))
	            throw new IFT287Exception("La chambre n'existe pas : " + idChambre);
	        
			// Vérifie si la commodité existe
	        if (!commodites.existe(idCommodite))
	            throw new IFT287Exception("La commodité n'existe pas : " + idCommodite);
	        
	        // Vérifie si la chambre offre la commodité
	        if (!chambres.commoditeIncluse(idChambre, idCommodite))
	            throw new IFT287Exception("La chambre " + idChambre + " ne possède pas la commodité " + idCommodite + ".");
            
	        // Enlève la commodite de la chambre
	        if (chambres.enleverCommodite(idChambre, idCommodite) == 0)
	        	throw new IFT287Exception("Erreur lors du retrait d'une commodité à une chambre.");
            	        
	        // Commit
            cx.commit();
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	/**
	 * Fonction pour aller chercher une chambre dans la BD.
	 * 
	 * @param idChambre  l'id de la chambre.
	 * 
	 * @return le tuple de la chambre contenant les données de celle-ci.
     */
	public TupleChambre getChambre(int idChambre)
			throws SQLException, IFT287Exception
	{
		try
		{
			TupleChambre tupleChambre = chambres.getChambre(idChambre);
			if (tupleChambre == null)
				throw new IFT287Exception("La chambre n'existe pas : " + idChambre);
			
			tupleChambre.setCommodites(commodites.getCommoditesChambre(idChambre));
			
			int prixCommodites = 0;
			
			for (TupleCommodite commodite : tupleChambre.getCommodites())
				prixCommodites += commodite.getPrix();
			
			tupleChambre.setPrix(tupleChambre.getPrix() + prixCommodites);
			
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
			throws SQLException, IFT287Exception
	{
		try
		{
			List<TupleChambre> listeChambres = chambres.getAllChambre();
			
			if (listeChambres.isEmpty())
				throw new IFT287Exception("Aucune chambre");
			
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
			throws SQLException, IFT287Exception
	{
		try
		{
			List<TupleChambre> listeChambres = chambres.getAllChambre();

			if (listeChambres.isEmpty())
				throw new IFT287Exception("Aucune chambre n'existe");
			
			LocalDate localDate = LocalDate.now();
			Date date = Date.valueOf(localDate);
			List<TupleReservation> listeReservation = new LinkedList<TupleReservation>();

			for (int i = 0; i < listeChambres.size(); ++i)
			{
				listeReservation = reservations.getReservationsChambre(listeChambres.get(i).getIdChambre());
				
				for (TupleReservation reservation : listeReservation)
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
				chambre.setCommodites(commodites.getCommoditesChambre(chambre.getIdChambre()));

				int prixCommodites = 0;
					
				for (TupleCommodite commodite : chambre.getCommodites())
					prixCommodites += commodite.getPrix();
					
				chambre.setPrix(chambre.getPrix() + prixCommodites);	
			}

			return listeChambres;
		}
		catch(Exception e)
		{
			throw e;
		}
	}
}
