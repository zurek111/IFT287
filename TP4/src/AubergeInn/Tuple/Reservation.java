package AubergeInn.Tuple;

import java.sql.Date;

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
	
	public Reservation( Client client, int idChambre, Date dateDebut, Date dateFin, int prixTotal)
	{
		this.setIdChambre(idChambre);
		this.setClient(client);
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

	public Client getClient() 
	{
		return client;
	}

	public void setClient(Client client) 
	{
		this.client = client;
	}

}
