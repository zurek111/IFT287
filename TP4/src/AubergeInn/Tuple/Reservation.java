package AubergeInn.Tuple;

import java.util.Date;

import org.bson.Document;

// Classe de données pour une réservation.
public class Reservation 
{	
	private int idChambre;
	private int idClient;
	private Date dateDebut;
	private Date dateFin;
	private int prixTotal;
	
	public Reservation()
	{
	}
	
	public Reservation(Document d)
    {
		setIdChambre(d.getInteger("idChambre"));
		setIdClient(d.getInteger("idClient"));
		setDateDebut(d.getDate("dateDebut"));
		setDateFin(d.getDate("dateFin"));
		setPrixTotal(d.getInteger("prixTotal"));
    }
	
	public Reservation(int idClient, int idChambre, Date dateDebut, Date dateFin, int prixTotal)
	{
		this.setIdChambre(idChambre);
		this.setIdClient(idClient);
		this.setDateDebut(dateDebut);
		this.setDateFin(dateFin);
		this.setPrixTotal(prixTotal);
	}

	public int getIdChambre() 
	{
		return idChambre;
	}

	public void setIdChambre(int idChambre) 
	{
		this.idChambre = idChambre;
	}
	
	public int getIdClient() 
	{
		return idClient;
	}

	public void setIdClient(int idClient) 
	{
		this.idClient = idClient;
	}

	public Date getDateDebut() 
	{
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) 
	{
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() 
	{
		return dateFin;
	}

	public void setDateFin(Date dateFin) 
	{
		this.dateFin = dateFin;
	}

	public int getPrixTotal() 
	{
		return prixTotal;
	}

	public void setPrixTotal(int prixTotal) 
	{
		this.prixTotal = prixTotal;
	}

    public Document toDocument()
    {
    	return new Document().append("idChambre", idChambre)
    			             .append("idClient", idClient)
    			             .append("dateDebut", dateDebut)
    			             .append("dateFin", dateFin)
    			             .append("prixTotal", prixTotal);
    }
}
