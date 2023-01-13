package mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.controller;

import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model.Modele;

import java.io.File;

/**
 * Controller opérant sur le textField
 */
public class ControllerChoisirRepertoire implements EventHandler<KeyEvent> {

    /**
     * modele de l'application
     */
    private Modele modele;

    /**
     * Instancie un nouveau Controller pour gérer le textField
     *
     * @param modele le modele de l'application
     */
    public ControllerChoisirRepertoire(Modele modele) {
        this.modele = modele;
    }

    /**
     * Permet de gérer le textField du choix de repertoire
     *
     * @param keyEvent keyEvent du textField
     */
    @Override
    public void handle(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (keyEvent.getTarget() instanceof TextField) {
                String s = (((TextField) keyEvent.getTarget()).getText());
                File f = new File(s);
                if (f.exists()&&f.isDirectory())
                    //si c est bien un dossier existant alors on change l'affichage par l'intermédiaire du modèle
                    modele.setRepAvecTextField(s);
            }
        }
    }
}
