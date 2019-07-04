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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + idCommodite;
		result = prime * result + prix;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TupleCommodite other = (TupleCommodite) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (idCommodite != other.idCommodite)
			return false;
		if (prix != other.prix)
			return false;
		return true;
	}

}
