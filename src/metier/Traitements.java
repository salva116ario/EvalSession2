package metier;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

import model.Apprenant;

/**
 * Méthodes qui gèrent les interactions avec l'utilisateur
 * 
 * @author sam s. & salva
 *
 */
public class Traitements {

	/**
	 * Saisit le nom d'apprenant demandé par l'utilisateur puis vérifie sa présence
	 * dans la BDD via checkIfNomApprenantExiste()
	 * 
	 * @return String (null si le nom ne figure pas dans la BDD)
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static String userChoisitUnApprenantByNom() throws ClassNotFoundException, SQLException {
		Scanner sc = new Scanner(System.in);
		String choix = sc.nextLine();
		if (InterrogeBDD.checkIfNomApprenantExiste(choix)) {
			return choix;
		}
		return null;
	}

	/**
	 * Saisit l'id d'apprenant demandé par l'utilisateur puis vérifie sa présence
	 * dans la BDD via checkIfApprenantIdExiste()
	 * 
	 * @return int (0 si l'id ne figure pas dans la BDD)
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static int userChoisitUnApprenantId() throws ClassNotFoundException, SQLException {
		Scanner sc = new Scanner(System.in);
		String rep = sc.nextLine();
		int choix = Integer.parseInt(rep);

		if (InterrogeBDD.checkIfApprenantIdExiste(choix)) {
			return choix;
		}
		return 0;
	}

	/**
	 * Saisit l'id d'activité demandé par l'utilisateur puis vérifie sa présence
	 * dans la BDD via checkIfActiviteExiste()
	 * 
	 * @return int (0 si l'id ne figure pas dans la BDD)
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static int userChoisitActiviteById() throws ClassNotFoundException, SQLException {
		Scanner sc = new Scanner(System.in);
		int choix = Integer.parseInt(sc.nextLine());
		if (InterrogeBDD.checkIfActiviteExiste(choix)) {
			return choix;
		}
		return 0;
	}

	/**
	 * Enregistre les informations de l'utilisateur pour créer un nouvel apprenant
	 * 
	 * @return un objet Apprenant
	 * @throws SQLException
	 * @throws ParseException
	 */
	public static Apprenant creerApprenant() throws SQLException, ParseException {

		Apprenant nouvelApprenant = new Apprenant();
		Scanner sc = new Scanner(System.in);
		System.out.println("Entrez le prénom de l'apprenant : ");
		nouvelApprenant.setPrenom(sc.nextLine());
		System.out.println("Entrez le nom de l'apprenant : ");
		nouvelApprenant.setNom(sc.nextLine());
		System.out.println("Entrez la date de naissance au format AAAA-MM-JJ :");
		java.sql.Date dateNaissance = Date.valueOf(sc.nextLine());
		nouvelApprenant.setDateNaissance(dateNaissance);
		System.out.println("Entrez l'adresse mail :");
		nouvelApprenant.seteMail(sc.nextLine());
		nouvelApprenant.setPhoto("");
		System.out.println("Entrez l'identifiant de région [1,2 ou3] :");
		int idRegion = Integer.parseInt(sc.nextLine());
		nouvelApprenant.setRegion(InterrogeBDD.getRegionById(idRegion));

		return nouvelApprenant;
	}

	/**
	 * Demande à l'utilisateur son choix dans le menu principal
	 * 
	 * @return
	 */
	public static int userChoixMenuPrincipal() {
		Scanner saisie = new Scanner(System.in);
		System.out.println("Entrez votre choix : ");
		String entree = saisie.nextLine();
		boolean isNumeric = entree.chars().allMatch(x -> Character.isDigit(x));
		if (!isNumeric) {
			System.out.println("Veuillez entrer un choix valide.");
			return 0;
		}
		int choix = Integer.parseInt(entree);
		if (choix < 1 || choix > 10) {
			System.out.println("Veuillez entrer un choix valide.");
			return 0;
		}
		return choix;

	}
}
