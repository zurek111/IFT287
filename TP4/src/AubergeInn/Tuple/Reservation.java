package AubergeInn.Tuple;

import java.sql.Date;

import javax.persistence.*;

// Classe de données pour une réservation.
@Entity
public class Reservation 
{
	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Chambre chambre;
	@ManyToOne(fetch = FetchType.LAZY)
	private Client client;
	private Date dateDebut;
	private Date dateFin;
	private int prixTotal;
	
	public Reservation()
	{
	}
	
	public Reservation( Client client, Chambre chambre, Date dateDebut, Date dateFin)
	{
		this.setChambre(chambre);
		this.setClient(client);
		this.setDateDebut(dateDebut);
		this.setDateFin(dateFin);
		this.setPrixTotal(chambre.getPrixTotal());
	}

	public Chambre getChambre() 
	{
		return chambre;
	}

	public void setChambre(Chambre chambre) 
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

	public Client getClient() 
	{
		return client;
	}

	public void setClient(Client client) 
	{
		this.client = client;
	}

}
