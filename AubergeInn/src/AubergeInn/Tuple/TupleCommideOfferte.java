package AubergeInn.Tuple;

public class TupleCommideOfferte 
{

	private int idChambre;
	private int idCommodite;
	
	public TupleCommideOfferte(int idChambre, int idCommodite) 
	{
		this.setIdChambre(idChambre);
		this.setIdCommodite(idCommodite);
	}

	public int getIdChambre() 
	{
		return idChambre;
	}

	public void setIdChambre(int idChambre) 
	{
		this.idChambre = idChambre;
	}

	public int getIdCommodite() 
	{
		return idCommodite;
	}

	public void setIdCommodite(int idCommodite) 
	{
		this.idCommodite = idCommodite;
	}

}
