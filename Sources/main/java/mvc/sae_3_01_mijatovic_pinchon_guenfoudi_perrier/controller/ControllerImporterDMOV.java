package mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model.Modele;

import java.io.File;

public class ControllerImporterDMOV implements EventHandler<ActionEvent> {

    private Modele modele;
    private String cheminDMOV;

    public ControllerImporterDMOV(Modele modele) {
        this.modele = modele;
    }

    @Override
    public void handle(ActionEvent e) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("."));
        fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("extension dmov",".dmov"));
        File f = fileChooser.showOpenDialog(null);
        if (f != null) {
            this.cheminDMOV = f.getPath();
        }
        this.modele.setModele(new Modele(cheminDMOV));
    }
}
