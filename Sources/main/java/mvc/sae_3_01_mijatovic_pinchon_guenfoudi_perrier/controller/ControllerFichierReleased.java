package mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.controller;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model.Classe;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model.Modele;

public class ControllerFichierReleased implements EventHandler<MouseEvent> {

    private Modele modele;

    public ControllerFichierReleased(Modele modele) {
        this.modele = modele;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        if (ControllerDragFichier.isDragging) {
            Classe temp = modele.getClasseCourante();
            if (temp != null) {
                modele.ajouterClasse(temp);
                modele.changerClasseCourante(null);
            }
            ControllerDragFichier.isDragging = false;
        }
    }
}
