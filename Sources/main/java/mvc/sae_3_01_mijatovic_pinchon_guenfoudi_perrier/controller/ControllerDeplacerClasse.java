package mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.controller;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.main;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model.Classe;

import static mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.main.SIZEX;
import static mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.main.SIZEY;

public class ControllerDeplacerClasse implements EventHandler<MouseEvent> {
    private Classe model;

    public ControllerDeplacerClasse(Classe model) {
        this.model = model;
    }


    @Override
    public void handle(MouseEvent mouseEvent) {

        model.setCoordinates(mouseEvent.getScreenX(), mouseEvent.getScreenY());

    }
}


