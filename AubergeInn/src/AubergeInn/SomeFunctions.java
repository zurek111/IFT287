package AubergeInn;

import java.text.DecimalFormat;
import java.util.Date;

public class SomeFunctions {

	public SomeFunctions() {
	}
	
	////////////////////////////////////////////////
	// CLIENT
	////////////////////////////////////////////////	
	public void ajouterClient(int idClient, String prenom, String nom, int age)
	{
		// Cette commande ajoute un nouveau client au système.
	}
	
	public void supprimerClient(int idClient)
	{
		// Cette commande supprime un client s’il n’a pas de réservation en cours.
	}

	////////////////////////////////////////////////
	// CHAMBRE
	////////////////////////////////////////////////
	public void ajouterChambre(int idChambre, String nom, String type, int prix)
	{
		// Cette commande ajoute une nouvelle chambre au système.
	}
	
	public void supprimerChambre(int idChambre)
	{
		// Cette commande supprime une chambre si elle n’est pas réservée et
		// n’a pas de réservation future.
	}
	
	////////////////////////////////////////////////
	// COMMODITE
	////////////////////////////////////////////////
	public void ajouterCommodite(int idCommodite, String description, int prix)
	{
		// Cette commande ajoute un nouveau service offert par l’entreprise.
	}
	
	public void inclureCommodite(int idChambre, int idCommodite)
	{
		// Cette commande ajoute une commodité à une chambre.
	}
	
	public void enleverCommodite(int idChambre, int idCommodite)
	{
		// Cette commande enlève une commodité d’une chambre.
	}
	
	////////////////////////////////////////////////
	// RESERVATIONS
	////////////////////////////////////////////////
	public void reserver(int idClient, int idChambre, Date dateDebut, Date dateFin)
	{
		// Cette commande réserve une chambre pour un client.
	}
	
	////////////////////////////////////////////////
	// AFFICHAGE
	////////////////////////////////////////////////
	public void afficherClient(int idClient)
	{
		// Cette commande affiche toutes les informations sur un client, incluant
		// les réservations présentes et passées. Les réservations contiennent le
		// prix total de la réservation, sans les taxes.
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
	
	////////////////////////////////////////////////
	// QUITTER
	////////////////////////////////////////////////
	public void quitter()
	{
		// Cette commande quitte l’application.
	}
}
