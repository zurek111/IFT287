package AubergeInn.Table;

import AubergeInn.Connexion;
import AubergeInn.Tuple.Reservation;

public class Reservations 
{
    private Connexion cx;
    
    // Fonction de connexion
	public Reservations(Connexion cx) 
	{
		this.cx = cx;
		
	}
	
	public Connexion getConnexion()
    {
        return cx;
    }
	
	/**
	 * Fonction pour ajouter une réservation.
	 * 
	 * @param reservation l'objet contenant les informations de la réservation à ajouter
	 * 
	 * @return L'objet réservation ajouté.
     */
	public Reservation ajouter(Reservation reservation)
	{
    	cx.getConnection().persist(reservation);
    	
    	return reservation;
	}
}
