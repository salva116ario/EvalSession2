package model;

/**
 * Modèle pour l'objet Activite
 * 
 * @author sam s. & salva
 *
 */
public class Activite {

	private int idActivite;
	private String nomActivite;

	public Activite() {
	}

	public Activite(int idActivite, String nomActivite) {
		this.idActivite = idActivite;
		this.nomActivite = nomActivite;
	}

	public int getIdActivite() {
		return idActivite;
	}

	public void setIdActivite(int idActivite) {
		this.idActivite = idActivite;
	}

	public String getNomActivite() {
		return nomActivite;
	}

	public void setNomActivite(String nomActivite) {
		this.nomActivite = nomActivite;
	}

	@Override
	public String toString() {
		return "activite [idActivite=" + idActivite + ", nomActivite=" + nomActivite + "]";
	}

}
