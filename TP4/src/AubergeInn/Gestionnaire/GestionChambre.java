package AubergeInn.Gestionnaire;

import java.time.LocalDate;
import java.sql.Date;
import java.util.List;

import AubergeInn.IFT287Exception;
import AubergeInn.Table.Chambres;
import AubergeInn.Table.Commodites;
import AubergeInn.Table.Reservations;
import AubergeInn.Tuple.Chambre;
import AubergeInn.Tuple.Reservation;

public class GestionChambre 
{
	private Chambres chambres;
	private Commodites commodites;
	private Reservations reservations; // TODO - est-ce qu'on veut cette variable pour savoir les reservations d'une chambre ou il y aurait une autre facon de faire ??
	
	// Fonction de connexion pour la chambre
	public GestionChambre(Chambres chambres, Commodites commodites, Reservations reservations) throws IFT287Exception
	{
		if (chambres.getConnexion() != commodites.getConnexion() || commodites.getConnexion() != reservations.getConnexion())
            throw new IFT287Exception("Les instances de Chambres et de Commodites n'utilisent pas la même connexion au serveur");
		
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
			throws IFT287Exception
	{
		try
        {
			Chambre chambre = new Chambre(idChambre, nom, type, prix);
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

            chambres.ajouter(chambre);
        }
        catch (Exception e)
        {
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
            // Verifie si la chambre est existante
            if (!chambres.existe(idChambre))
                throw new IFT287Exception("Chambre inexistante: " + idChambre);
            
            for (Reservation reservation : reservations.getReservationsChambre(idChambre))
            {
            	LocalDate localDate = LocalDate.now();
    			Date date = Date.valueOf(localDate);
            	if (!reservation.getDateFin().before(date))
            		throw new IFT287Exception("Chambre " + idChambre + " a des réservations");
            }

            // Suppression de la chambre
            if (!chambres.supprimer(idChambre))
                throw new IFT287Exception("Erreur lors de la suppression d'une chambre.");  
        }
        catch (Exception e)
        {
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
			Chambre chambre = chambres.getChambre(idChambre);
			
			// Vérifie si la chambre existe
			if (chambre == null)
	            throw new IFT287Exception("La chambre n'existe pas : " + idChambre);
	        
			// Vérifie si la commodité existe
	        if (!commodites.existe(idCommodite))
	            throw new IFT287Exception("La commodité n'existe pas : " + idCommodite);
	        
	        // Vérifie si la chambre possède déjà la commodité 
	        if (chambre.getIdCommodites().contains(idCommodite))
	        	throw new IFT287Exception("La chambre " + idChambre + " possède déjà la commodité " + idCommodite + ".");      
	        
	        // Inclus la commodité à la chambre
            chambre.ajouterCommodite(idCommodite);
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
			Chambre chambre = chambres.getChambre(idChambre);
			// Vérifie si la chambre existe
			if (chambre == null)
	            throw new IFT287Exception("La chambre n'existe pas : " + idChambre);

			// Vérifie si la commodité existe
	        if (!commodites.existe(idCommodite))
	            throw new IFT287Exception("La commodité n'existe pas : " + idCommodite);

	        // Vérifie si la chambre offre la commodité
	        if (!chambre.getIdCommodites().contains(idCommodite))
	            throw new IFT287Exception("La chambre " + idChambre + " ne possède pas la commodité " + idCommodite + ".");
            
	        // Enlève la commodite de la chambre
	        chambre.enleverCommodite(idCommodite);
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
	 * @return un objet chambre contenant les données de celle-ci.
     */
	public Chambre getChambre(int idChambre)
			throws IFT287Exception
	{
		try
		{
			Chambre chambre = chambres.getChambre(idChambre);
			if (chambre == null)
				throw new IFT287Exception("La chambre n'existe pas : " + idChambre);
			
			return chambre;
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
	public List<Chambre> getAllChambres()
			throws IFT287Exception
	{
		try
		{			
			List<Chambre> listeChambres = chambres.getAllChambre();
			
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
	public List<Chambre> getChambresLibres()
			throws IFT287Exception
	{
		try
		{			
			List<Chambre> listeChambres = chambres.getAllChambre();

			if (listeChambres.isEmpty())
				throw new IFT287Exception("Aucune chambre n'existe");
			
			LocalDate localDate = LocalDate.now();
			Date date = Date.valueOf(localDate);

			for (int i = 0; i < listeChambres.size(); ++i)
			{
				for (Reservation reservation : reservations.getReservationsChambre(listeChambres.get(i).getIdChambre()))
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
			
			return listeChambres;
		}
		catch(Exception e)
		{
			throw e;
		}
	}
}
