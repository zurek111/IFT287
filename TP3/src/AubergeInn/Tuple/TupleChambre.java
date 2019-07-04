package AubergeInn.Tuple;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

//Classe de données pour une chambre.
public class TupleChambre 
{
	@Id
	@GeneratedValue
	private long id;

	private int idChambre;
	private String nom;
	private String typeLit;
	private int prix;
	private List<TupleCommodite> commodites;

	@OneToMany(mappedBy = "chambre")
	@OrderBy("dateDebut")
	private List<TupleReservation> reservations;
	
	public TupleChambre()
	{
	}
	
	public TupleChambre(int idChambre, String nom, String typeLit, int prix) 
	{
		this.setIdChambre(idChambre);
		this.setNom(nom);
		this.setTypeLit(typeLit);
		this.setPrix(prix);
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

	public List<TupleCommodite> getCommodites() 
	{
		return commodites;
	}

	public void setCommodites(List<TupleCommodite> commodites) 
	{
		this.commodites = commodites;
	}
	
	public void ajouterCommodite(TupleCommodite commodite)
	{
		this.commodites.add(commodite);
	}
	
	public void enleverCommodite(TupleCommodite commodite)
	{
		this.commodites.remove(commodite);
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
