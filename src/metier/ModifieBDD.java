package metier;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import connexion.AccesBD;
import model.Apprenant;

/** 
 * Ensemble des méthodes qui modifient la BDD
 * (Create / Update / Delete)
 * 
 * @author sam s. & salva
 *
 */
public class ModifieBDD {


	/**
	 * Modifie la nom d'un apprenant
	 * 
	 * @param choix
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void updateNomApprenant(int choix) throws ClassNotFoundException, SQLException {
		if (choix != 0) {
			Scanner sc = new Scanner(System.in);
			try {
				System.out.println("Entrez la modification à apporter au nom de l'apprenant : ");
				String nouveauNom = sc.nextLine();
				PreparedStatement prepareStatement = AccesBD.getConnection()
						.prepareStatement("UPDATE apprenant SET nom = ? WHERE idApprenant = ?");
				prepareStatement.setString(1, nouveauNom);
				prepareStatement.setInt(2, choix);
				prepareStatement.executeUpdate();
				System.out.println("Modification effectuée pour l'apprenant n° : "
						+ InterrogeBDD.getApprenantById(choix).getIdApprenant());
			} catch (SQLException e) {
				System.out.println("Erreur lors de la modification !");
			}
		}
	}
	

	/**
	 * Ajoute un apprenant à la BDD
	 * 
	 * @param apprenant
	 * @throws SQLException
	 */
	public static void ajouterApprenant(Apprenant apprenant) throws SQLException {
		PreparedStatement prepareStatement = AccesBD.getConnection()
				.prepareStatement("INSERT INTO apprenant VALUES( null , ? , ? , ? , ? , ? , ? )");
		prepareStatement.setString(1, apprenant.getPrenom());
		prepareStatement.setString(2, apprenant.getNom());
		java.sql.Date sqlDate = new java.sql.Date(apprenant.getDateNaissance().getTime());
		prepareStatement.setDate(3, sqlDate);
		prepareStatement.setString(4, apprenant.geteMail());
		prepareStatement.setString(5, apprenant.getPhoto());
		prepareStatement.setInt(6, apprenant.getRegion().getIdRegion());
		prepareStatement.executeUpdate();
		System.out.println("L'apprenant " + apprenant.getPrenom() + " " + apprenant.getNom()
				+ " a été ajouté à la base de données");
	}
	

	/**
	 * Ajoute à la BDD une activité liée à un apprenant
	 * 
	 * @param idApprenant
	 * @param idActivite
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void ajouterActiviteAUnApprenant(int idApprenant, int idActivite)
			throws ClassNotFoundException, SQLException {
		try {
			PreparedStatement prepareStatement = AccesBD.getConnection()
					.prepareStatement("INSERT INTO liste_activites (idActivite, idApprenant) VALUES (?,?)");
			prepareStatement.setInt(1, idActivite);
			prepareStatement.setInt(2, idApprenant);
			prepareStatement.executeUpdate();
			System.out.println("Ajout d'activite effectué.");
		} catch (SQLException e) {
			System.out.println("L'ajout de l'activité a échoué !");
		}
	}
	

	/**
	 * Supprime un apprenant de la BDD
	 * 
	 * @param apprenant
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static void supprimerApprenant(Apprenant apprenant) throws SQLException, ClassNotFoundException {

		try {
			PreparedStatement prepareStatement = AccesBD.getConnection()
					.prepareStatement("DELETE FROM apprenant WHERE idApprenant = ?");
			prepareStatement.setInt(1, apprenant.getIdApprenant());
			prepareStatement.executeUpdate();
			System.out.println("Suppression de l'apprenant " + apprenant.getNom() + " effectuee");
		} catch (SQLException e) {
			System.out.println("Erreur lors de la suppression... !");
		}

	}

}