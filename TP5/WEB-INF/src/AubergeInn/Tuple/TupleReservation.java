package AubergeInn.Tuple;

import java.sql.Date;

// Classe de données pour une réservation.
public class TupleReservation 
{
	private int idReservation;
	private int idChambre;
	private int idClient;
	private Date dateDebut;
	private Date dateFin;
	private int prixTotal;
	
	public TupleReservation()
	{
	}
	
	public TupleReservation(int idReservation, int idChambre, int idClient, Date dateDebut, Date dateFin, int prixTotal)
	{
		this.setIdReservation(idReservation);
		this.setIdChambre(idChambre);
		this.setIdClient(idClient);
		this.setDateDebut(dateDebut);
		this.setDateFin(dateFin);
		this.setPrixTotal(prixTotal);
	}

	public int getIdReservation() 
	{
		return idReservation;
	}

	public void setIdReservation(int idReservation) 
	{
		this.idReservation = idReservation;
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

}
