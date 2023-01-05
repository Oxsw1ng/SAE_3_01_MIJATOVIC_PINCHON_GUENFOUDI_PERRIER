package mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TreeItem;
import javafx.scene.input.MouseEvent;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model.Classe;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model.Modele;

import java.io.File;

public class ControllerArborescenceRepertoire implements EventHandler<MouseEvent> {

    private Modele modele;
    private File chemin;

    public ControllerArborescenceRepertoire(Modele modele, File chemin) {
        this.modele = modele;
        this.chemin = chemin;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        System.out.println("Controleur marche");
        Classe addClasse = new Classe(chemin.getPath());
        modele.ajouterClasse(addClasse);
        if (mouseEvent.isPrimaryButtonDown()) {
            if (mouseEvent.isDragDetect()) {
                addClasse.setCoordinates(mouseEvent.getX(), mouseEvent.getY());
            }
        }
    }
}
