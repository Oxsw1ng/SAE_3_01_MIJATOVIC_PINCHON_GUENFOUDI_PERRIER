package mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
        // selecteur de fichier
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("."));
        // le selecteur de fichier ne peut ouvrir que des fichiers .dmov
        fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("extension dmov",".dmov"));

        // permet de fermer le selecteur de fichier
        File f = fileChooser.showOpenDialog(null);
        Modele modeleDMOV;

        // si f n'est pas null, l'importation du fichier s'est bien déroulée
        if (f != null) {
            this.cheminDMOV = f.getPath();
            modeleDMOV = new Modele(cheminDMOV);
        // si f est null cela veut dire que l'exploration de fichier s'est mal déroulé, le modèle ne change pas
        } else {
            modeleDMOV = this.modele;
        }
        // on change ensuite le modèle existant par le modèle présent dans le fichier .dmov
        this.modele.setModele(modeleDMOV);
    }
}
