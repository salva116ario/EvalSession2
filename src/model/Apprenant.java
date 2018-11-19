package model;

import java.sql.Date;

/**
 * Modèle pour l'objet Apprenant
 * 
 * @author sam s. & salva
 *
 */

public class Apprenant {

	private int idApprenant;
	private String prenom, nom, eMail, photo;
	private java.sql.Date dateNaissance;
	private Region region;

	public Apprenant() {
	}

	/**
	 * @param idApprenant
	 * @param prenom
	 * @param nom
	 * @param eMail
	 * @param photo
	 * @param dateNaissance
	 * @param region
	 */
	public Apprenant(int idApprenant, String prenom, String nom, String eMail, String photo, Date dateNaissance,
			Region region) {
		this.idApprenant = idApprenant;
		this.prenom = prenom;
		this.nom = nom;
		this.eMail = eMail;
		this.photo = photo;
		this.dateNaissance = dateNaissance;
		this.region = region;
	}

	public int getIdApprenant() {
		return idApprenant;
	}

	public void setIdApprenant(int idApprenant) {
		this.idApprenant = idApprenant;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	@Override
	public String toString() {
		return "|%3d" + idApprenant + "|%20s"+prenom;
	}

	
}
