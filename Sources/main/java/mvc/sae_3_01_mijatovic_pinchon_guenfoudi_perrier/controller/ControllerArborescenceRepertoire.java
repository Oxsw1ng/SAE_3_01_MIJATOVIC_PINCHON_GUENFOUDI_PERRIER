package mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model.Modele;

public class ControllerArborescenceRepertoire implements EventHandler<MouseEvent> {

    private Modele modele;

    public ControllerArborescenceRepertoire(Modele modele) {
        this.modele = modele;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        if (mouseEvent.isPrimaryButtonDown()) {

        }
    }
}
