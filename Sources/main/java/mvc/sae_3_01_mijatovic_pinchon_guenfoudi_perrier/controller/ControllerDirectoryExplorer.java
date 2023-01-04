package mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model.Modele;

import java.io.File;

public class ControllerDirectoryExplorer implements EventHandler<ActionEvent> {

    private Modele modele;

    public ControllerDirectoryExplorer(Modele modele) {
        this.modele = modele;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        DirectoryChooser dc = new DirectoryChooser();
        File f = dc.showDialog(new Stage());
        if (f != null) {
            modele.setRepAvecTextField(f.getPath());
        }
    }
}
