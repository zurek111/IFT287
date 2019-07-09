package AubergeInn.Tuple;

import org.bson.Document;

// Classe de données pour une commodité.
public class Commodite 
{
	private int idCommodite;
	private String description;
	private int prix;
	
	public Commodite()
	{
	}
	
    public Commodite(Document d)
    {
    	setIdCommodite(d.getInteger("idCommodite"));
    	setDescription(d.getString("description"));
    	setPrix(d.getInteger("prix"));
    }
	
	public Commodite(int idCommodite, String description, int prix) 
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
	
    public Document toDocument()
    {
    	return new Document().append("idCommodite", idCommodite)
    			             .append("description", description)
    			             .append("prix", prix);
    }
}
