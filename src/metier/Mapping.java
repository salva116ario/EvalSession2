package metier;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.Activite;
import model.Apprenant;
import model.Region;

/**
 * Ensemble des méthodes de mapping
 * 
 * @author sam s. & salva
 *
 */

public class Mapping {

	/**
	 * Méthode pour instancier un objet Apprenant
	 * 
	 * @param resultat
	 * @return Un objet Apprenant
	 * @throws SQLException
	 */
	public static Apprenant mapperApprenant(ResultSet resultat) throws SQLException {

		Apprenant apprenant = new Apprenant();
		apprenant.setIdApprenant(resultat.getInt("idApprenant"));
		apprenant.setPrenom(resultat.getString("prenom"));
		apprenant.setNom(resultat.getString("nom"));
		apprenant.setDateNaissance(resultat.getDate("dateNaissance"));
		apprenant.seteMail(resultat.getString("eMail"));
		apprenant.setPhoto(resultat.getString("photo"));
		apprenant.setRegion(InterrogeBDD.getRegionById(resultat.getInt("idRegion")));

		return apprenant;

	}

	/**
	 * Méthode pour instancier objet Activite
	 * 
	 * @param resultat
	 * @return un objet Activite
	 * @throws SQLException
	 */
	public static Activite mapperActivite(ResultSet resultat) throws SQLException {

		Activite activite = new Activite(0, null);
		activite.setIdActivite(resultat.getInt("idActivite"));
		activite.setNomActivite(resultat.getString("nomActivite"));

		return activite;

	}

	/**
	 * Méthode pour instancier objet Region
	 * 
	 * @param resultat
	 * @return un objet Region
	 * @throws SQLException
	 */
	public static Region mapperRegion(ResultSet resultat) throws SQLException {

		Region region = new Region(0, null);
		region.setIdRegion(resultat.getInt("idRegion"));
		region.setNomRegion(resultat.getString("nomRegion"));

		return region;

	}

}
