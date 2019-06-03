package AubergeInn.Tuple;

public class TupleCommodite 
{

	private int idCommodite;
	private String description;
	private int prix;
	
	public TupleCommodite(int idCommodite, String description, int prix) 
	{
		this.setIdCommodite(idCommodite);
		this.setDescription(description);
		this.setPrix(prix);
	}

	public int getIdCommodite() 
	{
		return idCommodite;
	}

	public void setIdCommodite(int idCommodite) 
	{
		this.idCommodite = idCommodite;
	}

	public String getDescription() 
	{
		return description;
	}

	public void setDescription(String description) 
	{
		this.description = description;
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
