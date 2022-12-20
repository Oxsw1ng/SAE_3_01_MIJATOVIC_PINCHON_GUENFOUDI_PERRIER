package mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model.Modele;

import java.io.File;

/*
 * Contrôleur opérant sur le textField
 */
public class ControllerChoisirRepertoire implements EventHandler<KeyEvent> {

    private Modele modele;

    public ControllerChoisirRepertoire(Modele modele) {
        this.modele = modele;
    }

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
