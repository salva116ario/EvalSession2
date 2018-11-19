package metier;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import model.Activite;
import model.Apprenant;
import model.Region;

class InterrogeBDDTest {
	
	ArrayList<Activite> activites;
	ArrayList<Apprenant> apprenants;
	Apprenant apprenant;
	Activite activite;
	
	
	@Before
	public void initialize() {
	      activites = new ArrayList<Activite>();
	      apprenants = new ArrayList<Apprenant>();
	      apprenant = new Apprenant();
	      activite = new Activite();
	    }
	

	@Test
	void testGetRegionById() throws SQLException {
		assertEquals("ILE DE FRANCE",InterrogeBDD.getRegionById(1).getNomRegion());
		assertEquals(1,InterrogeBDD.getRegionById(1).getIdRegion());
		assertEquals("PAYS DE LOIRE",InterrogeBDD.getRegionById(2).getNomRegion());
	}

	@Test
	void testGetAllActivites() throws ClassNotFoundException, SQLException {
		activites = InterrogeBDD.getAllActivites();
		assertEquals(activites.get(0).getNomActivite(),"Programmer en java");
		assertEquals(activites.get(1).getNomActivite(),"Ecouter les mouches");
		assertEquals(activites.get(7).getNomActivite(),"Caresser le chat");

	}

	@Test
	void testGetAllApprenants() throws ClassNotFoundException, SQLException {
		apprenants = InterrogeBDD.getAllApprenants();
		assertEquals(apprenants.size(),16);
		assertEquals(apprenants.get(11).getPrenom(),"Julien");
		assertEquals(apprenants.get(3).getRegion().getIdRegion(),1);
	}

	@Test
	void testGetApprenantByNom() throws SQLException {
		apprenant = InterrogeBDD.getApprenantByNom("Picard");
		assertEquals(apprenant.getPrenom(),"Laurent");
	}

	@Test
	void testGetActiviteById() throws ClassNotFoundException, SQLException {
		activite = InterrogeBDD.getActiviteById(5);
		assertEquals("Chercher un stage à Haïti",activite.getNomActivite());
	}

	@Test
	void testGetActivitesByApprenantId() throws SQLException {
		activites = InterrogeBDD.getActivitesByApprenant(7);
		assertEquals(activites.get(0).getIdActivite(),2);
		assertEquals(activites.get(1).getIdActivite(),3);
		assertEquals(activites.get(2).getIdActivite(),4);
		assertEquals(activites.get(3).getIdActivite(),6);
		assertEquals(activites.get(2).getNomActivite(),"Dormir pendant le cours");
	}

	@Test
	void testGetApprenantsByActivite() throws SQLException {
		apprenants = InterrogeBDD.getApprenantsByActivite(1);
		assertEquals(apprenants.size(),1);
		apprenants = InterrogeBDD.getApprenantsByActivite(2);
		assertEquals(apprenants.size(),3);
		assertEquals(apprenants.get(0).getNom(),"Khamvongsa");
	}

	@Test
	void testCheckIfNomApprenantExiste() throws ClassNotFoundException, SQLException {
		assertTrue(InterrogeBDD.checkIfNomApprenantExiste("Sabot"));
		assertTrue(InterrogeBDD.checkIfNomApprenantExiste("Lebegue"));
		assertFalse(InterrogeBDD.checkIfNomApprenantExiste("Pouchkine"));
	}

	@Test
	void testCheckIfApprenantExiste() throws ClassNotFoundException, SQLException {
		assertTrue(InterrogeBDD.checkIfApprenantIdExiste(1));
		assertTrue(InterrogeBDD.checkIfApprenantIdExiste(7));
		assertFalse(InterrogeBDD.checkIfApprenantIdExiste(20));
	}

	@Test
	void testGetActivitesOrphelines() throws ClassNotFoundException, SQLException {
		activites = InterrogeBDD.getActivitesOrphelines();
		assertEquals(activites.size(), 1);
		assertEquals(activites.get(0).getIdActivite(), 7);
		
	}

	@Test
	void testGetApprenantById() throws ClassNotFoundException, SQLException {
		apprenant = InterrogeBDD.getApprenantById(1);
		assertEquals(apprenant,null);
		apprenant = InterrogeBDD.getApprenantById(13); 
		assertEquals(apprenant.getPrenom(),"Samuel");
		apprenant = InterrogeBDD.getApprenantById(8); 
		assertEquals(apprenant.geteMail(),"thomas.longueville@laposte.fr");
}

	@Test
	void testCheckIfActiviteExiste() throws ClassNotFoundException, SQLException {
		assertFalse(InterrogeBDD.checkIfActiviteExiste(22));
		assertTrue(InterrogeBDD.checkIfActiviteExiste(5));
	}

	@Test
	void testGetApprenantsByRegion() throws ClassNotFoundException, SQLException {
		apprenants = InterrogeBDD.getApprenantsByRegion(1);
		assertEquals(apprenants.size(),8);
		assertEquals(apprenants.get(0).getIdApprenant(),2);
		assertEquals(apprenants.get(1).getIdApprenant(),3);
		assertEquals(apprenants.get(2).getIdApprenant(),4);
		assertEquals(apprenants.get(3).getIdApprenant(),6);
		assertEquals(apprenants.get(4).getIdApprenant(),9);
		assertEquals(apprenants.get(5).getIdApprenant(),10);
		assertEquals(apprenants.get(6).getIdApprenant(),12);
		assertEquals(apprenants.get(7).getIdApprenant(),14);
		
	}

	@Test
	void testGetAllRegions() throws ClassNotFoundException, SQLException {
		ArrayList<Region> regions = InterrogeBDD.getAllRegions();
		assertEquals(regions.size(),4);
		assertEquals(regions.get(0).getNomRegion(),"ILE DE FRANCE");
		assertEquals(regions.get(2).getNomRegion(),"AQUITAINE");
	}
}
