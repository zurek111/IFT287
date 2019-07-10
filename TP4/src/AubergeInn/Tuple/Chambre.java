package AubergeInn.Tuple;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;


//Classe de données pour une chambre.
public class Chambre 
{
	private int idChambre;
	private String nom;
	private String typeLit;
	private int prix;
	
	private List<Integer> idCommodites;
	
	public Chambre()
	{
	}
	
	public Chambre(Document d)
    {
		setIdChambre(d.getInteger("idChambre"));
		setNom(d.getString("nom"));
		setTypeLit(d.getString("typeLit"));
		setPrix(d.getInteger("prix"));
		this.setIdCommodites((List<Integer>) d.get("idCommodites"));

    }
	
	public Chambre(int idChambre, String nom, String typeLit, int prix) 
	{
		this.setIdChambre(idChambre);
		this.setNom(nom);
		this.setTypeLit(typeLit);
		this.setPrix(prix);
		this.setIdCommodites(new ArrayList<Integer>());
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
	
	public List<Integer> getIdCommodites() 
	{
		return idCommodites;
	}

	public void setIdCommodites(List<Integer> idCommodites) 
	{
		this.idCommodites = idCommodites;
	}
	
	public void enleverCommodite(int idCommodite)
	{
		this.getIdCommodites().remove(new Integer(idCommodite));
	}
	
	public void ajouterCommodite(int idCommodite)
	{
		this.getIdCommodites().add(idCommodite);
	}
	
	/**
	 * Fonction pour créer un document à partir d'une chambre.
	 * 
	 * @return un document qui contient les données de la chambre.
     */
    public Document toDocument()
    {
    	return new Document().append("idChambre", getIdChambre())
    			             .append("nom", getNom())
    			             .append("typeLit", getTypeLit())
    			             .append("prix", getPrix())
    			             .append("idCommodites", getIdCommodites());
    	
    }
}
