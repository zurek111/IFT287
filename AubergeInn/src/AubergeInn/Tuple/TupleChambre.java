package AubergeInn.Tuple;

public class TupleChambre 
{

	private int idChambre;
	private String nom;
	private String type;
	private int prix;
	
	public TupleChambre(int idChambre, String nom, String type, int prix) 
	{
		this.setIdChambre(idChambre);
		this.setNom(nom);
		this.setType(type);
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

	public String getType() 
	{
		return type;
	}

	public void setType(String type) 
	{
		this.type = type;
	}

	public int getPrix() 
	{
		return prix;
	}

	public void setPrix(int prix) 
	{
		this.prix = prix;
	}

}
