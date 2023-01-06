import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.Format.JPG;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.Themes.ThemeClair;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model.Modele;
import org.testng.annotations.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.testng.TestNGAntTask.Mode.junit;

public class TestModele {

    @Test
    public void test01_setCheminCourant() {
        // Initialisation des données
        Modele m = new Modele();

        // Méthode Testée
        m.setCheminCourant("essai/du/chemin");

        // Vérification
        assertEquals("essai/du/chemin", m.getCheminCourant());
    }

    @Test
    public void test02_addEtatsNav() {

        // Initialisation des données
        Modele m = new Modele();

        // Méthode Testée
        m.addEtatsNav("essai");
        m.addEtatsNav("test");

        // Vérification : la méthode ajoute 2 etats false de base
        assertFalse(m.getEtatNav("essai"));
        assertFalse(m.getEtatNav("test"));
    }

    @Test
    public void test03_changerEtatsNav() {

        // Initialisation des données
        Modele m = new Modele();
        m.addEtatsNav("essai");
        m.addEtatsNav("test");

        // Méthode Testée
        m.changerEtatsNav("essai");

        // Vérification
        assertTrue(m.getEtatNav("essai"));
        assertFalse(m.getEtatNav("test"));
    }

    //@Test
    //public void test04_changerTheme() {

        // Initialisation des données
    //    Modele m = new Modele();

        // Méthode Testée
    //    m.changerTheme(new ThemeClair());

        // Vérification
    //    assertTrue(m.getTheme() instanceof ThemeClair);
    //}


}
