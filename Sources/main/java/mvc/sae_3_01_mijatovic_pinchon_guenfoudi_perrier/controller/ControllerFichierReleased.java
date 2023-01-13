package mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.controller;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model.Classe;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model.Modele;

public class ControllerFichierReleased implements EventHandler<MouseEvent> {

    /**
     * Modèle de l'application
     */
    private Modele modele;

    /**
     * Crée le contrôleur
     * @param modele
     */
    public ControllerFichierReleased(Modele modele) {
        this.modele = modele;
    }

    /**
     * Permet d'ajouter la classe que l'on était en train de glisser sur le diagramme au modèle et affiche donc son visuel
     * @param mouseEvent
     */
    @Override
    public void handle(MouseEvent mouseEvent) {
        if (ControllerDragFichier.isDragging) {
            Classe temp = modele.getClasseCourante();
            if (temp != null) {
                modele.ajouterClasse(temp);
            }
            ControllerDragFichier.isDragging = false;
        }
        modele.changerClasseCourante(null);
    }
}
