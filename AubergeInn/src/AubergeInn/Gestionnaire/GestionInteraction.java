package AubergeInn.Gestionnaire;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import AubergeInn.Connexion;

public class GestionInteraction 
{
	private Connexion cx;
	private PreparedStatement stmAfficherChambre;
	private PreparedStatement stmListerChambresLibres;
	private PreparedStatement stmAfficherClient;

	public GestionInteraction(Connexion cx) throws SQLException
	{
		 this.cx = cx;
		 stmAfficherChambre = cx.getConnection().prepareStatement(
				 "");
	}

	public void afficherChambre(int idChambre)
	{
		// Cette commande affiche les informations sur une chambre, incluant les commodités offertes.
		
	}
	
	public void afficherChambresLibres()
	{
		// Cette commande affiche toutes les chambres qui sont disponibles. L’affichage
		// doit inclure le prix de location de la chambre (prix de base, plus les commodités).
	}
	
	public void afficherClient(int idClient)
	{
		// Cette commande affiche toutes les informations sur un client, incluant
		// les réservations présentes et passées. Les réservations contiennent le
		// prix total de la réservation, sans les taxes.
	}
}
