package mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model.Modele;
import java.io.File;

/**
 * Controller permettant de gérer l'explorateur de fichiers
 */
public class ControllerDirectoryExplorer implements EventHandler<ActionEvent> {

    /**
     * modele de l'application
     */
    private Modele modele;

    /**
     * Instancie un nouveau Controller pour gérer l'explorateur de fichiers
     *
     * @param modele le modele de l'application
     */
    public ControllerDirectoryExplorer(Modele modele) {
        this.modele = modele;
    }

    /**
     * Permet de trouver le chemin de l'explorateur de fichier
     *
     * @param actionEvent actionEvent
     */
    @Override
    public void handle(ActionEvent actionEvent) {
        DirectoryChooser dc = new DirectoryChooser();
        File f = dc.showDialog(new Stage());
        if (f != null) {
            modele.setRepAvecTextField(f.getPath());
        }
    }
}
