import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.Format.JPG;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.Themes.ThemeClair;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.interfacesETabstract.Theme;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model.Modele;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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
        @Test
        public void testChangerTheme() {
            // Crée une nouvelle instance de la classe Theme
            Theme themeClair = new ThemeClair();

            // Crée une nouvelle instance de la classe Modele qui contient la méthode changerTheme
            Modele modele = new Modele();

            // Appelle la méthode changerTheme et passe l'objet themeClair en argument
            modele.changerTheme(themeClair);

            // Vérifie que le champ theme a été correctement mis à jour
            assertEquals(themeClair, modele.getTheme());
        }
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



