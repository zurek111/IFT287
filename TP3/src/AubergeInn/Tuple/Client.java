package AubergeInn.Tuple;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.*;

// Classe de données pour un client.
@Entity
public class Client 
{
	@Id
	@GeneratedValue
	private long id;
	
	private int idClient;
	private String prenom;
	private String nom;
	private int age;
	
	@OneToMany(mappedBy = "client")
	@OrderBy("dateDebut")
	private List<Reservation> reservations;
	
	public Client()
	{
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

}
