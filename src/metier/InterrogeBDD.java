package metier;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connexion.AccesBD;
import metier.Mapping;
import model.Activite;
import model.Apprenant;
import model.Region;

/**
 * Ensemble des methodes qui effectuent une requete a la BDD
 * 
 * @author sam s. & salva
 *
 */
public class InterrogeBDD {

	/**
	 * Retourne la region correspondant a idRegion
	 * 
	 * @param idRegion
	 * @return une ArrayList d'objets Region (via la méthode Mapping.mapperRegion())
	 * @throws SQLException
	 */
	public static Region getRegionById(int idRegion) throws SQLException {

		PreparedStatement preparedStatement = AccesBD.getConnection()
				.prepareStatement("SELECT * FROM region WHERE idRegion = ?");
		preparedStatement.setInt(1, idRegion);
		ResultSet resultat = preparedStatement.executeQuery();
		resultat.next();

		return Mapping.mapperRegion(resultat);
	}

	/**
	 * Retourne la liste de toutes les activites
	 * 
	 * @return une ArrayList de tous les objets Activite (via la methode
	 *         Mapping.mapperRegion())
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static ArrayList<Activite> getAllActivites() throws ClassNotFoundException, SQLException {
		ArrayList<Activite> activites = new ArrayList<Activite>();
		String requete = "SELECT * FROM activite ORDER BY idActivite";
		ResultSet resultat = AccesBD.executerQuery(requete);

		while (resultat.next()) {
			Activite activite = Mapping.mapperActivite(resultat);
			activites.add(activite);
		}

		return activites;
	}

	/**
	 * Retourne la liste de tous les apprenants
	 * 
	 * @return une ArrayList de tous les objets Apprenant (via la methode
	 *         Mapping.mapperApprenant())
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static ArrayList<Apprenant> getAllApprenants() throws ClassNotFoundException, SQLException {
		ArrayList<Apprenant> apprenants = new ArrayList<Apprenant>();
		String requete = "SELECT * FROM apprenant ORDER BY idApprenant";
		ResultSet resultat = AccesBD.executerQuery(requete);

		while (resultat.next()) {
			Apprenant apprenant = Mapping.mapperApprenant(resultat);
			apprenants.add(apprenant);
		}

		return apprenants;
	}

	/**
	 * Retourne les infos d'un apprenant recherché par son nom
	 * 
	 * @param nom de l'apprenant
	 * @return l'objet Apprenant recherché (via Mapping.mapperApprenant())
	 * @throws SQLException
	 */

	public static Apprenant getApprenantByNom(String nom) throws SQLException {
		PreparedStatement preparedStatement = AccesBD.getConnection()
				.prepareStatement("SELECT * FROM apprenant WHERE nom = ?");
		preparedStatement.setString(1, nom);
		ResultSet resultat = preparedStatement.executeQuery();
		resultat.next();
		return Mapping.mapperApprenant(resultat);
	}

	/**
	 * Retourne l'activité correspondant à l'id passé en argument
	 * 
	 * @param id
	 * @return l'objet Activite correspondant à id (via Mapping.mapperActivite())
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Activite getActiviteById(int id) throws ClassNotFoundException, SQLException {
		PreparedStatement preparedStatement = AccesBD.getConnection()
				.prepareStatement("SELECT * FROM activite WHERE idActivite = ?");
		preparedStatement.setInt(1, id);
		ResultSet resultat = preparedStatement.executeQuery();
		resultat.next();
		return Mapping.mapperActivite(resultat);
	}

	/**
	 * Retourne la liste des activités pratiquées par un apprenant dont l'id a été
	 * passé en argument
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public static ArrayList<Activite> getActivitesByApprenant(int id) throws SQLException {
		ArrayList<Activite> activites = new ArrayList<>();
		PreparedStatement preparedStatement = AccesBD.getConnection().prepareStatement(
				"SELECT activite.*  FROM apprenant, liste_activites, activite WHERE (liste_activites.idApprenant = ? && liste_activites.idActivite = activite.idActivite && apprenant.idApprenant = ?)");
		preparedStatement.setInt(1, id);
		preparedStatement.setInt(2, id);
		ResultSet resultat = preparedStatement.executeQuery();
		while (resultat.next()) {
			Activite activite = Mapping.mapperActivite(resultat);
			activites.add(activite);
		}
		return activites;
	}

	/**
	 * Retourne la liste des apprenants qui pratiquent l'activité id
	 * 
	 * @param id
	 * @return une ArrayList d'objets Apprenant (via MappMapping.mapperApprenant())
	 * @throws SQLException
	 */

	public static ArrayList<Apprenant> getApprenantsByActivite(int id) throws SQLException {
		ArrayList<Apprenant> apprenants = new ArrayList<>();
		PreparedStatement preparedStatement = AccesBD.getConnection().prepareStatement(
				"SELECT apprenant.*  FROM apprenant, liste_activites, activite WHERE (liste_activites.idActivite = ? && activite.idActivite = ? && liste_activites.idApprenant = apprenant.idApprenant)");
		preparedStatement.setInt(1, id);
		preparedStatement.setInt(2, id);
		ResultSet resultat = preparedStatement.executeQuery();
		while (resultat.next()) {
			Apprenant apprenant = Mapping.mapperApprenant(resultat);
			apprenants.add(apprenant);
		}
		return apprenants;
	}

	/**
	 * Vérfie si un nom figure dans la table 'apprenant'
	 * 
	 * @param nom
	 * @return boolean
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static boolean checkIfNomApprenantExiste(String nom) throws SQLException, ClassNotFoundException {
		String requete = "SELECT * FROM apprenant WHERE nom = \"" + nom + "\"";
		ResultSet resultat = AccesBD.executerQuery(requete);
		return resultat.next();
	}

	/**
	 * Vérifie si un id existe dans la table 'apprenant'
	 * 
	 * @param id
	 * @return boolean
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static boolean checkIfApprenantIdExiste(int id) throws SQLException, ClassNotFoundException {
		String requete = "SELECT * FROM apprenant WHERE idApprenant = \"" + id + "\"";
		ResultSet resultat = AccesBD.executerQuery(requete);
		return resultat.next();
	}

	/**
	 * Retourne la liste des activités qui ne sont pratiquées par aucun apprenant
	 * 
	 * @return Une ArrayList d'objets Ativite (via Mapping.mapperActivite())
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static ArrayList<Activite> getActivitesOrphelines() throws ClassNotFoundException, SQLException {
		ArrayList<Activite> activites = new ArrayList<>();
		String requete = ("SELECT *  FROM activite WHERE NOT EXISTS (select null from liste_activites where activite.idActivite = liste_activites.idActivite) ");
		ResultSet resultat = AccesBD.executerQuery(requete);
		while (resultat.next()) {
			Activite activite = Mapping.mapperActivite(resultat);
			activites.add(activite);
		}
		return activites;
	}

	/**
	 * Retourne un Apprenant selon son id
	 * 
	 * @param id
	 * @return Un objet Apprenant ou null
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Apprenant getApprenantById(int id) throws ClassNotFoundException, SQLException {
		Apprenant apprenant = new Apprenant();
		String requete = "SELECT * FROM apprenant WHERE idApprenant = " + id;
		ResultSet resultat = AccesBD.executerQuery(requete);

		if (resultat.next()) {
			apprenant = Mapping.mapperApprenant(resultat);
			return apprenant;
		}
		return null;

	}

	/**
	 * Verifie si une activite identifiée par un id existe
	 * 
	 * @param choix : id de l'activite
	 * @return boolean
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static boolean checkIfActiviteExiste(int choix) throws ClassNotFoundException, SQLException {
		String requete = "SELECT * FROM activite WHERE idActivite = \"" + choix + "\"";
		ResultSet resultat = AccesBD.executerQuery(requete);
		return resultat.next();

	}

	/**
	 * Retourne la liste des apprenants d'une région idRegion *
	 * 
	 * @param idRegion
	 * @return Une ArrayList d'objets Apprenant (via
	 *         Mapping.mapperApprenant(resultat))
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static ArrayList<Apprenant> getApprenantsByRegion(int idRegion) throws ClassNotFoundException, SQLException {
		ArrayList<Apprenant> apprenants = new ArrayList<>();
		String requete = "SELECT * FROM apprenant WHERE idRegion = " + idRegion;
		ResultSet resultat = AccesBD.executerQuery(requete);
		while (resultat.next()) {
			Apprenant apprenant = Mapping.mapperApprenant(resultat);
			apprenants.add(apprenant);
		}

		return apprenants;
	}

	/**
	 * Retourne la liste de toutes les régions
	 * 
	 * @return Une ArrayList d'objets Region (via Mapping.mapperRegion())
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static ArrayList<Region> getAllRegions() throws ClassNotFoundException, SQLException {
		ArrayList<Region> regions = new ArrayList<>();
		String requete = "SELECT * FROM region";
		ResultSet resultat = AccesBD.executerQuery(requete);
		while (resultat.next()) {
			Region region = Mapping.mapperRegion(resultat);
			regions.add(region);
		}
		return regions;
	}

}
