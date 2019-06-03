package AubergeInn.Gestionnaire;

import java.sql.SQLException;

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
    
    private TableChambres chambres;
    private TableClients clients;
    private TableReservations reservations;
    private TableCommodites commodites;
    private TableCommoditesOffertes commoditesOffertes;
    
    public GestionAubergeInn(String serveur, String bd, String user, String password)
            throws IFT287Exception, SQLException
    {
        cx = new Connexion(serveur, bd, user, password);
        chambres = new TableChambres(cx);
        clients = new TableClients(cx);
        reservations = new TableReservations(cx);
        commodites = new TableCommodites(cx);
        commoditesOffertes = new TableCommoditesOffertes(cx);

        setGestionInteraction(new GestionInteraction(cx));
        setGestionChambre(new GestionChambre(chambres, commodites, commoditesOffertes, reservations));
        setGestionClient(new GestionClient(clients, reservations));
        setGestionReservation(new GestionReservation(chambres, reservations, clients));
        setGestionCommodite(new GestionCommodite(commodites));
    }
    
    public void fermer() throws SQLException
    {
        // Fermeture de la connexion
        cx.fermer();
    }
    
    public Connexion getConnexion()
    {
    	return cx;
    }

	public GestionInteraction getGestionInteraction() {
		return gestionInteraction;
	}

	public void setGestionInteraction(GestionInteraction gestionInteraction) {
		this.gestionInteraction = gestionInteraction;
	}

	public GestionChambre getGestionChambre() {
		return gestionChambre;
	}

	public void setGestionChambre(GestionChambre gestionChambre) {
		this.gestionChambre = gestionChambre;
	}

	public GestionClient getGestionClient() {
		return gestionClient;
	}

	public void setGestionClient(GestionClient gestionClient) {
		this.gestionClient = gestionClient;
	}

	public GestionReservation getGestionReservation() {
		return gestionReservation;
	}

	public void setGestionReservation(GestionReservation gestionReservation) {
		this.gestionReservation = gestionReservation;
	}

	public GestionCommodite getGestionCommodite() {
		return gestionCommodite;
	}

	public void setGestionCommodite(GestionCommodite gestionCommodite) {
		this.gestionCommodite = gestionCommodite;
	}

}
