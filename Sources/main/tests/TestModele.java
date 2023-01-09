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
   /* public void testSupprimerClasse_ObjetPresent() {
        // Créez une instance de la classe Modele et ajoutez des éléments à la liste classes
        Modele modele = new Modele();
        Classe classe1 = new Classe();
        Classe classe2 = new Classe();
        modele.ajouterClasse(classe1);
        modele.ajouterClasse(classe2);

        // Vérifiez que la méthode supprime bien l'objet de la liste
        modele.supprimerClasse(classe1);
        assertFalse(modele.getClasses().contains(classe1));
    }

    @Test
    public void testSupprimerClasse_ObjetAbsent() {
        // Créez une instance de la classe Modele et ajoutez des éléments à la liste classes
        Modele modele = new Modele();
        Classe classe1 = new Classe();
        Classe classe2 = new Classe();
        modele.ajouterClasse(classe1);
        modele.ajouterClasse(classe2);

        // Vérifiez que la méthode ne fait rien lorsqu'on essaie de retirer un objet qui n'est pas présent dans la liste
        modele.supprimerClasse(new Classe());
        assertEquals(2, modele.getClasses().size());
    }

    @Test
    public void testSupprimerClasse_Notification() {
        // Créez une instance de la classe Modele et ajoutez des éléments à la liste classes
        Modele modele = new Modele();
        Classe classe1 = new Classe();
        modele.ajouterClasse(classe1);

        // Vérifiez que la méthode appelle bien la méthode notifierObservateurs
        // (ce test peut être plus compliqué à écrire, vous pouvez par exemple utiliser un mock de la classe Observateur)
    }

    @Test
    public void testSupprimerClasse_ListeVide() {
        // Créez une instance de la classe Modele sans ajouter d'éléments à la liste classes
        Modele modele = new Modele();

        // Vérifiez que la méthode ne fait rien lorsque la liste est vide
        modele.supprimerClasse(new Classe());
        assertTrue(modele.getClasses().isEmpty());
    }*/

    //@Test
    //public void test04_changerTheme() {

        // Initialisation des données
    //    Modele m = new Modele();

        // Méthode Testée
    //    m.changerTheme(new ThemeClair());

        // Vérification
    //    assertTrue(m.getTheme() instanceof ThemeClair);
    //}



