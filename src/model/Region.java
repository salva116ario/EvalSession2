package model;

/**
 * Modèle pour l'objet Region
 * 
 * @author sam s. & salva
 *
 */
public class Region {

	private int idRegion;
	private String nomRegion;

	public Region() {
	}

	/**
	 * 
	 * @param idRegion
	 * @param nomRegion
	 */

	public Region(int idRegion, String nomRegion) {
		this.idRegion = idRegion;
		this.nomRegion = nomRegion;
	}

	public int getIdRegion() {
		return idRegion;
	}

	public void setIdRegion(int idRegion) {
		this.idRegion = idRegion;
	}

	public String getNomRegion() {
		return nomRegion;
	}

	public void setNomRegion(String nomRegion) {
		this.nomRegion = nomRegion;
	}

	@Override
	public String toString() {
		return "Region [idRegion=" + idRegion + ", nomRegion=" + nomRegion + "]";
	}

}
