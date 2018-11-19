package main;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

import metier.Processus;
import metier.Traitements;

/**
 * Lance le menu principal de l'application
 * 
 * @author sam s. & salva
 *
 */

public class MenuPrincipal {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, ParseException {

		boolean continuer = true;

		do {

			affichageDuMenuPrincipal();

			int choix = Traitements.userChoixMenuPrincipal();

			switch (choix) {

			case 1:
				Processus.processusAfficherTousLesApprenants();
				break;

			case 2:
				Processus.processusAfficherApprenantsParRegion();
				break;

			case 3:
				Processus.processusAfficherActivitesPourUnApprenant();
				break;

			case 4:
				Processus.processusAfficherApprenantsPratiquantUneActivite();
				break;

			case 5:
				Processus.processusAjouterNouvelApprenant();
				break;

			case 6:
				Processus.processusAjouterActiviteAUnApprenant();
				break;

			case 7:
				Processus.processusAfficherActivitesOrphelines();
				break;

			case 8:
				Processus.processusUpdateNomApprenant();
				break;

			case 9:
				Processus.processusSupprimerApprenant();
				break;

			case 10:
				continuer = false;
				break;

			}

		} while (continuer);

	}

	/**
	 * Affiche le menu principal
	 */

	public static void affichageDuMenuPrincipal() {
		System.out.println("\n\nMENU PRINCIPAL");
		System.out.println("==============");
		System.out.println("Choix 1 : Affichez tous les apprenant(e)s.");
		System.out.println("Choix 2 : Affichez la liste des apprenants pour chaque région.");
		System.out.println(
				"Choix 3 : Recherchez un apprenant(e) (par son nom) et afficher la liste des activités qu’il ou qu’elle pratique.");
		System.out.println(
				"Choix 4 : Recherchez les apprenants qui pratiquent une activité passée en paramètre d’une méthode.");
		System.out.println("Choix 5 : Ajoutez un(e) nouvel(le) apprenant(e) à la base de données.");
		System.out.println("Choix 6 : Affectez une activité à un(e) apprenant(e)");
		System.out.println("Choix 7 : Affichez la liste des activités que personne ne fait.");
		System.out.println("Choix 8 : Modifiez le nom d’un(e) apprenant(e).");
		System.out.println("Choix 9 : Supprimez un(e) apprenant(e).");
		System.out.println("Choix 10 : Quitter");
		System.out.println(
				"================================================================================================================");
	}

	
}