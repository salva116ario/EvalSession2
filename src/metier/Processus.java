package metier;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import model.Activite;
import model.Apprenant;
import model.Region;

/**
 * Les méthodes de cette classe regroupent les séries d'actions a effectuer pour
 * chaque entrée du menu principal
 * 
 * @author sam s. & salva
 *
 */

public class Processus {

	/**
	 * Suite des actions à réaliser pour : Supprimer un apprenant
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ParseException 
	 */
	public static void processusSupprimerApprenant() throws ClassNotFoundException, SQLException, ParseException {
		Processus.processusAfficherTousLesApprenants();
		System.out.println(">>>Entrez l'id de l'apprenant à supprimer de la BDD");
		int choix = Traitements.userChoisitUnApprenantId();
		if (!InterrogeBDD.checkIfApprenantIdExiste(choix)) {
			System.out.println("!!! Cet apprenant ne figure pas dans la liste");
		} else {
			Apprenant apprenant = InterrogeBDD.getApprenantById(choix);
			ModifieBDD.supprimerApprenant(apprenant);
		}
	}

	/**
	 * Suite des actions à réaliser pour : afficher les activités d'un apprenant
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ParseException 
	 */
	public static void processusAfficherActivitesPourUnApprenant() throws ClassNotFoundException, SQLException, ParseException {
		Processus.processusAfficherTousLesApprenants();
		System.out.println(">>> Indiquez le NOM de l'apprenant dont vous souhaitez voir les activités :");
		String nom = Traitements.userChoisitUnApprenantByNom();
		if (nom == null) {
			System.out.println("!!! Ce nom ne figure pas dans liste des apprenants !");
		} else {
			Apprenant apprenant = InterrogeBDD.getApprenantByNom(nom);
			ArrayList<Activite> activites = InterrogeBDD.getActivitesByApprenant(apprenant.getIdApprenant());
			System.out.print("### " + apprenant.getPrenom() + " " + apprenant.getNom());
			if (activites.size() > 0) {
				System.out.println(" PRATIQUE LES ACTIVITES SUIVANTES ###");
				Affichage.afficherDesActivites(activites);
			} else {
				System.out.println(" NE PRATIQUE AUCUNE ACTIVITE ###");
			}
		}
	}

	/**
	 * Suite des actions à réaliser pour : modifier le nom d'un apprenant
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ParseException 
	 */
	public static void processusUpdateNomApprenant() throws ClassNotFoundException, SQLException, ParseException {
		Processus.processusAfficherTousLesApprenants();
		System.out.println(">>> De quel apprenant faut-il modifier le nom ?");
		int choix = Traitements.userChoisitUnApprenantId();
		if (InterrogeBDD.checkIfApprenantIdExiste(choix)) {
			ModifieBDD.updateNomApprenant(choix);
		} else {
			System.out.println("Aucun apprenant n'a cet identifiant !");
		}
	}

	/**
	 * Suite des actions à réaliser pour : ajouter un nouvel apprenant
	 * 
	 * @throws SQLException
	 * @throws ParseException
	 */
	public static void processusAjouterNouvelApprenant() throws SQLException, ParseException {
		Apprenant apprenant = Traitements.creerApprenant();
		ModifieBDD.ajouterApprenant(apprenant);
	}

	/**
	 * Affiche un menu qui permet de choisir l'activité dont on souhaite voir par
	 * qui elle est pratiquée
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void processusAfficherApprenantsPratiquantUneActivite() throws ClassNotFoundException, SQLException {
		Processus.processusAfficherToutesLesActivites();
		System.out.println(">>> De quelle activité voulez-vous voir les pratiquants ?");
		int choix = Traitements.userChoisitActiviteById();
		if (choix == 0) {
			System.out.println("!!! Cette activité n'existe pas dans la base de données.");
		} else {
			String activite = InterrogeBDD.getActiviteById(choix).getNomActivite();
			ArrayList<Apprenant> apprenants = InterrogeBDD.getApprenantsByActivite(choix);
			System.out.print("### L'ACTIVITE '" + activite + "'");
			if (apprenants.size() > 0) {
				System.out.println(" EST PRATIQUEE PAR : ###");
				Affichage.afficherDesApprenants(apprenants);
			} else {
				System.out.println(" N'EST PRATIQUEE PAR PERSONNE ! ###");
			}
		}
	}

	/**
	 * Suite des actions à réaliser pour : afficher toutes les activités
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void processusAfficherToutesLesActivites() throws ClassNotFoundException, SQLException {
		ArrayList<Activite> activites = InterrogeBDD.getAllActivites();
		System.out.println("### TOUTES LES ACTIVITES ###");
		Affichage.afficherDesActivites(activites);
	}

	/**
	 * Suite des actions à réaliser pour : ajouter une activité à un apprenant
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ParseException 
	 */
	public static void processusAjouterActiviteAUnApprenant() throws ClassNotFoundException, SQLException, ParseException {

		Processus.processusAfficherTousLesApprenants();
		System.out.println(">>> A quel apprenant souhaitez-vous ajouter une activité ?");
		int choixApprenant = Traitements.userChoisitUnApprenantId();
		if (!InterrogeBDD.checkIfApprenantIdExiste(choixApprenant)) {
			System.out.println("!!! Cet apprenant n'est pas dans la base de données.");
		} else {
			Apprenant apprenant = new Apprenant();
			apprenant = InterrogeBDD.getApprenantById(choixApprenant);
			Affichage.afficherDesActivites(InterrogeBDD.getAllActivites());
			System.out.println(">>> Quelle activité souhaitez-vous ajouter ?");
			int choixActivite = Traitements.userChoisitActiviteById();
			if (!InterrogeBDD.checkIfActiviteExiste(choixActivite)) {
				System.out.println("!!! Cette activité n'existe pas dans la base de données.");
			} else {
				ModifieBDD.ajouterActiviteAUnApprenant(choixApprenant, choixActivite);
				System.out.println("### L'apprenant " + apprenant.getPrenom() + " " + apprenant.getNom()
						+ " pratique désormais les activités suivantes : ###");
				Affichage.afficherDesActivites(InterrogeBDD.getActivitesByApprenant(choixApprenant));
			}
		}
	}

	/**
	 * Suite des actions à réaliser pour : afficher tous les apprenants
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ParseException 
	 */
	public static void processusAfficherTousLesApprenants() throws ClassNotFoundException, SQLException, ParseException {
		ArrayList<Apprenant> apprenants = InterrogeBDD.getAllApprenants();
		System.out.println("### TOUS LES APPRENANTS ###");
		//Affichage.afficherDesApprenants(apprenants);
		Affichage maListe = new Affichage(apprenants, true, true, true, true, true);
		maListe.afficher();
	}

	/**
	 * Suite des actions à réaliser pour : afficher les apprenants par région
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ParseException 
	 */
	public static void processusAfficherApprenantsParRegion() throws ClassNotFoundException, SQLException, ParseException {
		ArrayList<Region> regions = InterrogeBDD.getAllRegions();
		for (Region region : regions) {
			System.out.println("### REGION " + region.getNomRegion() + "###");
			ArrayList<Apprenant> apprenants = InterrogeBDD.getApprenantsByRegion(region.getIdRegion());
			if (apprenants.size() > 0) {
				Affichage maListe = new Affichage(apprenants,false,true,true,false,false);
				maListe.afficher();
			} else {
				System.out.println("!!! Aucun apprenant dans cette région.");
			}
			System.out.println();
		}
	}

	/**
	 * Suite des actions à réaliser pour : afficher les activités que personne ne
	 * pratique
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void processusAfficherActivitesOrphelines() throws ClassNotFoundException, SQLException {
		ArrayList<Activite> activites = InterrogeBDD.getActivitesOrphelines();
		if (activites.size() > 0) {
			System.out.println("### ACTIVITES QUE PERSONNE NE PRATIQUE ###");
			Affichage.afficherDesActivites(activites);
		} else {
			System.out.println("Aucune activité orpheline.");
		}
	}
}
