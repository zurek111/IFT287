package AubergeInn.Tuple;

import java.util.LinkedList;
import java.util.List;

import org.bson.Document;

// Classe de données pour un client.
public class Client 
{
	
	private int idClient;
	private String prenom;
	private String nom;
	private int age;

	
	public Client()
	{
	}
	
	public Client(Document d)
    {
		setIdClient(d.getInteger("idClient"));
		setPrenom(d.getString("prenom"));
		setNom(d.getString("nom"));
		setAge(d.getInteger("age"));
    }
	
	public Client(int idClient, String prenom, String nom, int age) 
	{
		this.setIdClient(idClient);
		this.setPrenom(prenom);
		this.setNom(nom);
		this.setAge(age);
		this.setReservations(new LinkedList<Reservation>());
	}


	public int getIdClient() 
	{
		return idClient;
	}


	public void setIdClient(int idClient) 
	{
		this.idClient = idClient;
	}


	public String getPrenom() 
	{
		return prenom;
	}


	public void setPrenom(String prenom) 
	{
		this.prenom = prenom;
	}


	public String getNom() 
	{
		return nom;
	}


	public void setNom(String nom) 
	{
		this.nom = nom;
	}


	public int getAge() 
	{
		return age;
	}


	public void setAge(int age) 
	{
		this.age = age;
	}
	
	public void ajouterReservation (Reservation reservation)
	{
		reservations.add(reservation);
	}
	
	public void enleverReservation (Reservation reservation)
	{
		reservations.remove(reservation);
	}
	
	public List<Reservation> getReservations() 
	{
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) 
	{
		this.reservations = reservations;
	}
	
	public Document toDocument()
    {
    	return new Document().append("idClient", getIdClient())
    			             .append("prenom", getPrenom())
    			             .append("nom", getNom())
    			             .append("age", getAge());
    }
}
