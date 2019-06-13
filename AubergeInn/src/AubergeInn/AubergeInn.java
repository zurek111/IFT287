// Travail fait par :
//   Simon Cesare-Zurek - 18 068 546
//   NomEquipier2 - Matricule

package AubergeInn;

import java.io.*;
import java.util.StringTokenizer;
import java.sql.*;

import AubergeInn.Gestionnaire.GestionAubergeInn;

/**
 * Fichier de base pour le TP2 du cours IFT287
 *
 * <pre>
 * 
 * Vincent Ducharme
 * Universite de Sherbrooke
 * Version 1.0 - 7 juillet 2016
 * IFT287 - Exploitation de BD relationnelles et OO
 * 
 * Ce programme permet d'appeler des transactions d'un systeme
 * de gestion utilisant une base de donnees.
 *
 * Paramètres du programme
 * 0- site du serveur SQL ("local" ou "dinf")
 * 1- nom de la BD
 * 2- user id pour etablir une connexion avec le serveur SQL
 * 3- mot de passe pour le user id
 * 4- fichier de transaction [optionnel]
 *           si non spécifié, les transactions sont lues au
 *           clavier (System.in)
 *
 * Pré-condition
 *   - La base de donnees doit exister
 *
 * Post-condition
 *   - Le programme effectue les mises à jour associees à chaque
 *     transaction
 * </pre>
 */
public class AubergeInn
{
    static private GestionAubergeInn gestionAubergeInn;

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception
    {
        if (args.length < 4)
        {
            System.out.println("Usage: java AubergeInn.AubergeInn <serveur> <bd> <user> <password> [<fichier-transactions>]");
            return;
        }
        
        AubergeInn aubergeInnInstance = null;
        
        try
        {
            // Il est possible que vous ayez à déplacer la connexion ailleurs.
            // N'hésitez pas à le faire!
        	aubergeInnInstance = new AubergeInn(args[0], args[1], args[2], args[3]);
            BufferedReader reader = ouvrirFichier(args);
            String transaction = lireTransaction(reader);
            while (!finTransaction(transaction))
            {
                executerTransaction(transaction);
                transaction = lireTransaction(reader);
            }
        }
        finally
        {
            if(aubergeInnInstance != null)
            	aubergeInnInstance.fermer();     
        }
    }
    
    public AubergeInn(String serveur, String bd, String user, String pass) throws Exception
    {
    	gestionAubergeInn = new GestionAubergeInn(serveur, bd, user, pass);
    }
    
    public void fermer() throws Exception
    {
    	gestionAubergeInn.fermer();
    }

    /**
     * Decodage et traitement d'une transaction
     */
    static void executerTransaction(String transaction) throws Exception, IFT287Exception
    {
        try
        {
            System.out.print(transaction);
            // Decoupage de la transaction en mots
            StringTokenizer tokenizer = new StringTokenizer(transaction, " ");
            if (tokenizer.hasMoreTokens())
            {
                String command = tokenizer.nextToken();
                // Vous devez remplacer la chaine "commande1" et "commande2" par
                // les commandes de votre programme. Vous pouvez ajouter autant
                // de else if que necessaire. Vous n'avez pas a traiter la
                // commande "quitter".
                if (command.equals("ajouterClient"))
                {
                    int idClient = readInt(tokenizer);
                    String prenom = readString(tokenizer);
                    String nom = readString(tokenizer);
                    int age = readInt(tokenizer);
                    gestionAubergeInn.getGestionClient().ajouterClient(idClient, prenom, nom, age);
                }
                else if (command.equals("supprimerClient"))
                {
                	int idClient = readInt(tokenizer);
                    gestionAubergeInn.getGestionClient().supprimerClient(idClient);
                }
                else if (command.equals("ajouterChambre"))
                {
                	int idChambre = readInt(tokenizer);
                    String nom = readString(tokenizer);
                    String type = readString(tokenizer);
                    int prix = readInt(tokenizer);
                    gestionAubergeInn.getGestionChambre().ajouterChambre(idChambre, nom, type, prix);
                }
                else if (command.equals("supprimerChambre"))
                {
                	int idChambre = readInt(tokenizer);
                    gestionAubergeInn.getGestionChambre().supprimerChambre(idChambre);
                }
                else if (command.equals("ajouterCommodite"))
                {
                	int idCommodite = readInt(tokenizer);
                	String description = readString(tokenizer);
                	int prix = readInt(tokenizer);
                	gestionAubergeInn.getGestionCommodite().ajouterCommodite(idCommodite, description, prix);
                }
                else if (command.equals("inclureCommodite"))
                {
                	int idChambre = readInt(tokenizer);
                	int idCommodite = readInt(tokenizer);
                	gestionAubergeInn.getGestionChambre().inclureCommodite(idChambre, idCommodite);
                }
                else if (command.equals("enleverCommodite"))
                {
                	int idChambre = readInt(tokenizer);
                	int idCommodite = readInt(tokenizer);
                	gestionAubergeInn.getGestionChambre().enleverCommodite(idChambre, idCommodite);
                }
                else if (command.equals("reserver"))
                {
                	int idClient = readInt(tokenizer);
                	int idChambre = readInt(tokenizer);
                	Date dateDebut = readDate(tokenizer);
                	Date dateFin = readDate(tokenizer);
                	gestionAubergeInn.getGestionReservation().reserver(idClient, idChambre, dateDebut, dateFin);
                }
                else if (command.equals("afficherChambre"))
                {
                	int idChambre = readInt(tokenizer);
                	gestionAubergeInn.getGestionInteraction().afficherChambre(idChambre, gestionAubergeInn);
                }
                else if (command.equals("afficherChambresLibree"))
                {
                	gestionAubergeInn.getGestionInteraction().afficherChambresLibres(gestionAubergeInn);
                }
                else if (command.equals("afficherClient"))
                {
                	int idClient = readInt(tokenizer);
                	gestionAubergeInn.getGestionInteraction().afficherClient(idClient, gestionAubergeInn);
                }
                else
                {
                    System.out.println(" : Transaction non reconnue");
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(" " + e.toString());
            // Ce rollback est ici seulement pour vous aider et éviter des problèmes lors de la correction
            // automatique. En théorie, il ne sert à rien et ne devrait pas apparaître ici dans un programme
            // fini et fonctionnel sans bogues.
            if (gestionAubergeInn.getConnexion() != null)
            	gestionAubergeInn.getConnexion().rollback();
        }
    }

    
    // ****************************************************************
    // *   Les methodes suivantes n'ont pas besoin d'etre modifiees   *
    // ****************************************************************

    /**
     * Ouvre le fichier de transaction, ou lit à partir de System.in
     */
    public static BufferedReader ouvrirFichier(String[] args) throws FileNotFoundException
    {
        if (args.length < 5)
            // Lecture au clavier
            return new BufferedReader(new InputStreamReader(System.in));
        else
            // Lecture dans le fichier passe en parametre
            return new BufferedReader(new InputStreamReader(new FileInputStream(args[4])));
    }

    /**
     * Lecture d'une transaction
     */
    static String lireTransaction(BufferedReader reader) throws IOException
    {
        return reader.readLine();
    }

    /**
     * Verifie si la fin du traitement des transactions est atteinte.
     */
    static boolean finTransaction(String transaction)
    {
        // fin de fichier atteinte
        return (transaction == null || transaction.equals("quitter"));
    }

    /** Lecture d'une chaine de caracteres de la transaction entree a l'ecran */
    static String readString(StringTokenizer tokenizer) throws Exception
    {
        if (tokenizer.hasMoreElements())
            return tokenizer.nextToken();
        else
            throw new Exception("Autre parametre attendu");
    }

    /**
     * Lecture d'un int java de la transaction entree a l'ecran
     */
    static int readInt(StringTokenizer tokenizer) throws Exception
    {
        if (tokenizer.hasMoreElements())
        {
            String token = tokenizer.nextToken();
            try
            {
                return Integer.valueOf(token).intValue();
            }
            catch (NumberFormatException e)
            {
                throw new Exception("Nombre attendu a la place de \"" + token + "\"");
            }
        }
        else
            throw new Exception("Autre parametre attendu");
    }

    static Date readDate(StringTokenizer tokenizer) throws Exception
    {
        if (tokenizer.hasMoreElements())
        {
            String token = tokenizer.nextToken();
            try
            {
                return Date.valueOf(token);
            }
            catch (IllegalArgumentException e)
            {
                throw new Exception("Date dans un format invalide - \"" + token + "\"");
            }
        }
        else
            throw new Exception("Autre parametre attendu");
    }

}
