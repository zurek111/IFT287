package AubergeInn.Tuple;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.*;

//Classe de données pour une chambre.
@Entity
public class Chambre 
{
	@Id
	@GeneratedValue
	private long id;

	private int idChambre;
	private String nom;
	private String typeLit;
	private int prix;
	
	private List<Commodite> commodites;

	@OneToMany(mappedBy = "chambre")
	@OrderBy("dateDebut")
	private List<Reservation> reservations;
	
	public Chambre()
	{
	}
	
	public Chambre(int idChambre, String nom, String typeLit, int prix) 
	{
		this.setIdChambre(idChambre);
		this.setNom(nom);
		this.setTypeLit(typeLit);
		this.setPrix(prix);
		this.setCommodites(new LinkedList<Commodite>());
		this.setReservations(new LinkedList<Reservation>());
	}

	public int getIdChambre() 
	{
		return idChambre;
	}

	public void setIdChambre(int idChambre) 
	{
		this.idChambre = idChambre;
	}

	public String getNom() 
	{
		return nom;
	}

	public void setNom(String nom) 
	{
		this.nom = nom;
	}

	public String getTypeLit() 
	{
		return typeLit;
	}

	public void setTypeLit(String typeLit) 
	{
		this.typeLit = typeLit;
	}

	public int getPrix() 
	{
		return prix;
	}

	public void setPrix(int prix) 
	{
		this.prix = prix;
	}

	public List<Commodite> getCommodites() 
	{
		return commodites;
	}

	public void setCommodites(List<Commodite> commodites) 
	{
		this.commodites = commodites;
	}
	
	public void ajouterCommodite(Commodite commodite)
	{
		this.commodites.add(commodite);
	}
	
	public void enleverCommodite(Commodite commodite)
	{
		this.commodites.remove(commodite);
	}
	
	public List<Reservation> getReservations() 
	{
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) 
	{
		this.reservations = reservations;
	}

	public int getPrixTotal() 
	{
		int prixTotal = this.prix;
		for (Commodite commodite : this.commodites)
			prixTotal += commodite.getPrix();
		return prixTotal;
	}

	public void ajouterReservation(Reservation reservation) 
	{
		this.reservations.add(reservation); 
		
	}
	
	public void enleverReservation(Reservation reservation) 
	{
		this.reservations.remove(reservation); 
		
	}

}
