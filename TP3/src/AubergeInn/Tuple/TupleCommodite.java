package AubergeInn.Tuple;

import javax.persistence.*;

// Classe de données pour une commodité.
@Entity
public class TupleCommodite 
{
	@Id
	@GeneratedValue
	private long id;

	private int idCommodite;
	private String description;
	private int prix;
	
	public TupleCommodite()
	{
	}
	
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
