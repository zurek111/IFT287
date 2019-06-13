package AubergeInn.Tuple;

import java.util.List;

public class TupleChambre 
{

	private int idChambre;
	private String nom;
	private String typeLit;
	private int prix;
	private List<TupleCommodite> commodites;
	
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

}
