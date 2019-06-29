package AubergeInn.Tuple;

import java.util.List;

import javax.persistence.*;

// Classe de données pour un client.
@Entity
public class TupleClient 
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
	private List<TupleReservation> reservations;
	
	public TupleClient()
	{
	}
	
	public TupleClient(int idClient, String prenom, String nom, int age) 
	{
		this.setIdClient(idClient);
		this.setPrenom(prenom);
		this.setNom(nom);
		this.setAge(age);
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
	
	public void ajouterReservation (TupleReservation reservation)
	{
		reservations.add(reservation);
	}
	
	public void enleverReservation (TupleReservation reservation)
	{
		reservations.remove(reservation);
	}
	
	public List<TupleReservation> getReservations() 
	{
		return reservations;
	}

	public void setReservations(List<TupleReservation> reservations) 
	{
		this.reservations = reservations;
	}

}
