package AubergeInn.Gestionnaire;

import java.sql.SQLException;

import AubergeInn.Connexion;
import AubergeInn.IFT287Exception;
import AubergeInn.Table.TableChambres;
import AubergeInn.Table.TableCommodites;
import AubergeInn.Table.TableReservations;
import AubergeInn.Tuple.TupleChambre;

public class GestionChambre 
{
	private Connexion cx;
	private TableChambres chambres;
	private TableCommodites commodites;
	private TableReservations reservations;
	
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
	
	public void ajouterChambre(int idChambre, String nom, String type, int prix)
			throws SQLException, IFT287Exception
	{
		// Cette commande ajoute une nouvelle chambre au système.
		try
        {
			// Vérifie les informations de la chambre
			if (prix < 0)
                throw new IFT287Exception("Le prix de la chambre doit être supérieur ou égal à zéro.");
			
			if (nom.isEmpty() || type.isEmpty())
                throw new IFT287Exception("La chambre doit avoir des informations valides.");
            
			// Vérifie si la chambre existe déja
            if (chambres.existe(idChambre))
                throw new IFT287Exception("La chambre existe déjà: " + idChambre);

            // Ajout de la chambre
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
	
	public void supprimerChambre(int idChambre)
			throws SQLException, IFT287Exception
	{
		try
        {
            TupleChambre tupleChambre = chambres.getChambre(idChambre);
            
            // Verifie si la chambre est existant
            if (tupleChambre == null)
                throw new IFT287Exception("Chambre inexistante: " + idChambre);
            
            // Verifie si la chambre a des reservations
            if (reservations.getReservationsChambre(idChambre) != null)
                throw new IFT287Exception("Chambre " + idChambre + " a des réservations");

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
	
	public void inclureCommodite(int idChambre, int idCommodite)
			throws SQLException, IFT287Exception
	{
		try
		{
			if (!chambres.existe(idChambre))
	            throw new IFT287Exception("La chambre n'existe pas : " + idChambre);
	        
	        if (!commodites.existe(idCommodite))
	            throw new IFT287Exception("La commodité n'existe pas : " + idCommodite);
	        
	        if (chambres.commoditeExiste(idChambre, idCommodite))
	            throw new IFT287Exception("La chambre " + idChambre + " possède déjà la commodité " + idCommodite + ".");
	        
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
	
	public void enleverCommodite(int idChambre, int idCommodite)
			throws SQLException, IFT287Exception
	{
		try
		{
			if (!chambres.existe(idChambre))
	            throw new IFT287Exception("La chambre n'existe pas : " + idChambre);
	        
	        if (!commodites.existe(idCommodite))
	            throw new IFT287Exception("La commodité n'existe pas : " + idCommodite);
	        
	        if (!chambres.commoditeExiste(idChambre, idCommodite))
	            throw new IFT287Exception("La chambre " + idChambre + " ne possède pas la commodité " + idCommodite + ".");
            
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
}
