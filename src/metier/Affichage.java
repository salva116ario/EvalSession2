package metier;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import model.Activite;
import model.Apprenant;

/**
 * Affichage des résultats sur la console
 * 
 * @author sam s. & salva
 *
 */
public class Affichage {

	ArrayList<Apprenant> apprenants; // La liste d'apprenants à afficher
	boolean nom, prenom, email, region, id; // Les colonnes à afficher ou non (true pour afficher)
	String separateur = "", entete = "";

	/**
	 * Construit un objet Affichage qui permet d'afficher un tableau d'apprenants
	 * avec les colonnes choisies en paramètres
	 * 
	 * @param apprenants : un ArrayList d'objets Apprenant qui correspond à la liste
	 *                   des apprenants à afficher
	 * @param id         : si true, affiche l'identifiant
	 * @param prenom     : si true, affiche le prénom
	 * @param nom        : si true, affiche le nom de famille
	 * @param email      : si true, affiche l'adresse mail
	 * @param region     : si true, affiche le nom de la région
	 */
	public Affichage(ArrayList<Apprenant> apprenants, boolean id, boolean prenom, boolean nom, boolean email,
			boolean region) {
		this.apprenants = apprenants;
		this.id = id;
		this.prenom = prenom;
		this.nom = nom;
		this.email = email;
		this.region = region;
	}

	/**
	 * Affiche un tableau d'apprenants
	 * 
	 * @throws ParseException
	 */
	public void afficher() throws ParseException {
		entete = "";
		if (this.id) {
			separateur += "|----";
			entete += "| id ";
		}
		if (this.prenom) {
			separateur += "|---------------";
			entete +=     "| Prénom        ";
		}
		if (this.nom) {
			separateur += "|---------------";
			entete +=     "| Nom           ";
					
					
					
		}
		if (this.email) {
			separateur += "|-----------------------------------";
			entete +=     "| Adresse mail                      ";
		}
		if (this.region) {
			separateur += "|---------------";
			entete +=     "| Région        ";
		}
		if (separateur.length() > 0) {
			separateur += "|";
			entete +="|";
		}

		 String haut = separateur.replace('|', ' ');
		System.out.println(haut + "\n" + entete + "\n" + separateur);
		for (Apprenant apprenant : apprenants) {
			if (this.id) {
				System.out.printf("|%4d", apprenant.getIdApprenant());
			}
			if (this.prenom) {
				System.out.printf("|%-15s", apprenant.getPrenom());
			}
			if (this.nom) {
				System.out.printf("|%-15s", apprenant.getNom());
			}
			if (this.email) {
				System.out.printf("|%-35s", apprenant.geteMail());
			}
			if (this.region) {
				System.out.printf("|%-15s", apprenant.getRegion().getNomRegion());
			}
			System.out.println("|\n"+separateur);
			
		}

	}

	/**
	 * Affiche une liste d'apprenants sous la forme : Id Prenom Nom
	 * 
	 * @param apprenants: l'ArrayList d'objets Apprenant a afficher
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void afficherDesApprenants(ArrayList<Apprenant> apprenants)
			throws ClassNotFoundException, SQLException {
		for (Apprenant apprenant : apprenants) {
			System.out.println(apprenant.getIdApprenant() + " " + apprenant.getPrenom() + " " + apprenant.getNom());
		}
	}

	/**
	 * Affiche une liste d'activites sous la forme : Id Nom_de_l'activite
	 * 
	 * @param liste_activites: l'ArrayList d'objet Activite a afficher
	 */
	public static void afficherDesActivites(ArrayList<Activite> liste_activites) {
		for (Activite activite : liste_activites) {
			System.out.println(activite.getIdActivite() + " " + activite.getNomActivite());
		}
	}

}
