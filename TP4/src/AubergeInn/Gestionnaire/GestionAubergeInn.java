package AubergeInn.Gestionnaire;

import AubergeInn.Connexion;
import AubergeInn.IFT287Exception;
import AubergeInn.Table.*;

public class GestionAubergeInn 
{
    private Connexion cx;
    
    private GestionInteraction gestionInteraction;
    private GestionChambre gestionChambre;
    private GestionClient gestionClient;
    private GestionReservation gestionReservation;
    private GestionCommodite gestionCommodite;
    
    private Chambres chambres;
    private Clients clients;
    private Reservations reservations;
    private Commodites commodites;
    
    // Fonction de connection qui initialise les tables.
    public GestionAubergeInn(String serveur, String bd, String user, String password)
            throws IFT287Exception
    {
        cx = new Connexion(serveur, bd, user, password);
        // initialisation des tables
        chambres = new Chambres(cx);
        clients = new Clients(cx);
        reservations = new Reservations(cx);
        commodites = new Commodites(cx);
        
        // passe les tables aux gestionnaires.
        setGestionInteraction(new GestionInteraction());
        setGestionChambre(new GestionChambre(chambres, commodites, reservations));
        setGestionClient(new GestionClient(clients, reservations));
        setGestionReservation(new GestionReservation(chambres, reservations, clients, commodites));
        setGestionCommodite(new GestionCommodite(commodites));
    }
    
    public void fermer()
    {
        // Fermeture de la connexion
        cx.fermer();
    }
    
    public Connexion getConnexion()
    {
    	return cx;
    }

	public GestionInteraction getGestionInteraction() 
	{
		return gestionInteraction;
	}

	public void setGestionInteraction(GestionInteraction gestionInteraction) 
	{
		this.gestionInteraction = gestionInteraction;
	}

	public GestionChambre getGestionChambre() 
	{
		return gestionChambre;
	}

	public void setGestionChambre(GestionChambre gestionChambre) 
	{
		this.gestionChambre = gestionChambre;
	}

	public GestionClient getGestionClient() 
	{
		return gestionClient;
	}

	public void setGestionClient(GestionClient gestionClient) 
	{
		this.gestionClient = gestionClient;
	}

	public GestionReservation getGestionReservation() 
	{
		return gestionReservation;
	}

	public void setGestionReservation(GestionReservation gestionReservation) 
	{
		this.gestionReservation = gestionReservation;
	}

	public GestionCommodite getGestionCommodite() 
	{
		return gestionCommodite;
	}

	public void setGestionCommodite(GestionCommodite gestionCommodite) 
	{
		this.gestionCommodite = gestionCommodite;
	}

}
