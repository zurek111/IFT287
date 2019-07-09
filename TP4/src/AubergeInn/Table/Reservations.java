package AubergeInn.Table;

import java.util.LinkedList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import static com.mongodb.client.model.Filters.*;

import AubergeInn.Connexion;
import AubergeInn.Tuple.Reservation;

public class Reservations 
{
    private MongoCollection<Document> reservationsCollection;
    private Connexion cx;
    
    /**
     * Creation d'une instance.
     */
	public Reservations(Connexion cx) 
	{
		this.cx = cx;
		reservationsCollection = cx.getDatabase().getCollection("Reservations");
	}
	
    /**
     * Retourner la connexion associée.
     */
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
	public void ajouter(Reservation reservation)
	{
        reservationsCollection.insertOne(reservation.toDocument());
	}
	
	public List<Reservation> getReservationsClient(int idClient)
	{
		List<Reservation> listeReservations = new LinkedList<Reservation>();
		
		MongoCursor<Document> reservations = reservationsCollection.find(eq("idClient", idClient)).iterator();
        try
        {
            while (reservations.hasNext())
            {
            	listeReservations.add(new Reservation(reservations.next()));
            }
        }
        finally
        {
        	reservations.close();
        }
        
        return listeReservations;
	}
	
	public List<Reservation> getReservationsChambre(int idChambre)
	{
		List<Reservation> listeReservations = new LinkedList<Reservation>();
		
		MongoCursor<Document> reservations = reservationsCollection.find(eq("idChambre", idChambre)).iterator();
        try
        {
            while (reservations.hasNext())
            {
            	listeReservations.add(new Reservation(reservations.next()));
            }
        }
        finally
        {
        	reservations.close();
        }
        
        return listeReservations;
	}
}
