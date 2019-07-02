package AubergeInn.Tuple;

import java.sql.Date;

import javax.persistence.*;

// Classe de données pour une réservation.
@Entity
public class TupleReservation 
{
	@Id
	@GeneratedValue
	private long id;
	
	private int idReservation;
	@ManyToOne(fetch = FetchType.LAZY)
	private TupleChambre chambre;
	@ManyToOne(fetch = FetchType.LAZY)
	private TupleClient client;
	private Date dateDebut;
	private Date dateFin;
	private int prixTotal;
	
	public TupleReservation()
	{
	}
	
	public TupleReservation(int idReservation, TupleChambre chambre, TupleClient client, Date dateDebut, Date dateFin, int prixTotal)
	{
		this.setIdReservation(idReservation);
		this.setChambre(chambre);
		this.setClient(client);
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

	public TupleChambre getChambre() 
	{
		return chambre;
	}

	public void setChambre(TupleChambre chambre) 
	{
		this.chambre = chambre;
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

	public TupleClient getClient() {
		return client;
	}

	public void setClient(TupleClient client) {
		this.client = client;
	}

}
