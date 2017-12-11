package facture;

import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class FactureTest {

    Catalogue catalogue;
    Client bastide;
    Facture f;
    Article disk, iPad;
    LigneFacture ligne;

    @Before
    public void setUp() {
        catalogue = new Catalogue();
        /// "disk" : code de l'article
        disk = new Article("disk", "Disque 2To", 100f);
        iPad = new Article("ipad", "IPad 2", 700f);
        catalogue.addArticle(disk);
        catalogue.addArticle(iPad);

        bastide = new Client("Rémi Bastide", "Rue du Bac, Castres");
        Date dateDuJour = new Date();
        int numeroFacture = 345;
        f = new Facture(bastide, dateDuJour, numeroFacture);
        f.ajouteLigne(iPad, 1, 0f); // 700€, 0% de remise
        f.ajouteLigne(disk, 5, 0.1f); //5*100€, 10% de remise

    }

    @Test
    public void testCatalogue() {
        assertSame(iPad, catalogue.findByCode("ipad"));
        assertSame(disk, catalogue.findByCode("disk"));
        assertNull(catalogue.findByCode(null));

    }

    @Test
    public void testTTC() {
        float tva = 0.2f;
        float expected = (1 * iPad.getPrix() + 5 * disk.getPrix() * (0.9f)) * (1 + tva);
        assertEquals(expected, f.montantTTC(tva), 0.001f);
    }

    @Test
    public void testgetNumero() {
        assertEquals(345, f.getNumero());
    }

    @Test
    public void testName() {
        assertEquals("Rémi Bastide", bastide.getName());
        bastide.setName("Castres");
        assertEquals("Castres", bastide.getName());
    }

    @Test
    public void testAddress() {
        bastide = new Client("Rémi Bastide", "Rue du Bac, Castres");
        assertEquals("Rue du Bac, Castres", bastide.getAddress());
        bastide.setAddress("Castres");
        assertEquals("Castres", bastide.getAddress());
    }

    @Test
    public void testGetDestinataire() {
        assertEquals(bastide, f.getDestinataire());
    }

    @Test
    public void testGetEmission() {
        Date dateDuJour = new Date();
        assertEquals(dateDuJour, f.getEmission());
    }

    @Test
    public void testGetArticleFacture() {
        LigneFacture ligne = new LigneFacture(3, f, disk, (float) 0.2);
        assertEquals(disk, ligne.getArticleFacture());
    }

    @Test
    public void testFigureDans() {
        LigneFacture ligne = new LigneFacture(3, f, disk, (float) 0.2);
        assertEquals(f, ligne.getFigureDans());
    }

    @Test
    public void testgetNombre() {
        LigneFacture ligne = new LigneFacture(3, f, disk, (float) 0.2);
        assertEquals(3, ligne.getNombre());
        ligne.setNombre(1);
        assertEquals(1,ligne.getNombre());
        try {
            ligne.setNombre(-1);
            fail();
        } catch (IllegalArgumentException e) {
            
        }
        
    }

    @Test
    public void testTauxRemise() {
        LigneFacture ligne = new LigneFacture(3, f, disk, (float) 0.2);
        assertEquals(0.2, ligne.getTauxRemise(), 0.001);
        ligne.setTauxRemise((float) 0.1);
        assertEquals(0.1, ligne.getTauxRemise(), 0.001);
    }

    @Test
    public void testGetNom() {
        assertEquals("IPad 2", iPad.getNom());
    }

    @Test
    public void testSetCode() {
        disk.setCode("ipad");
        assertEquals("ipad", disk.getCode());
    }

    @Test
    public void testSetNom() {
        disk.setNom("ipad");
        assertEquals("ipad", disk.getNom());
    }

    @Test
    public void testSetPrix() {
        disk.setPrix(500);
        assertEquals(500, disk.getPrix(), 0.001);
    }

    /*@Test
    public void testtoString() {
        disk.setPrix(500);
        assertEquals("[disk] Disque 2To, prix unitaire 500€", disk.toString());
        
    }*/
}

