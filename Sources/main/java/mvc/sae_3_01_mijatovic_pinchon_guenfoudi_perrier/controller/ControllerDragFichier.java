package mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.controller;

import javafx.event.EventHandler;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model.Classe;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model.Modele;
import java.io.File;

/**
 * Controller permettant de gérer le glisser deposer de fichier .class depuis l'interieur de l'application
 */
public class ControllerDragFichier implements EventHandler<MouseEvent> {
    /**
     * modele de l'application
     */
    public Modele modele;

    /**
     * booleen pour verifier que le clique depose un fichier
     */
    public static boolean isDragging = false;

    /**
     * Instancie un nouveau Controller pour gérer le drag and drop depuis l'interieur de l'application
     *
     * @param modele le modele de l'application
     */
    public ControllerDragFichier(Modele modele) {
        this.modele = modele;
    }

    /**
     * Permet de gérer le drag and drop depuis l'interieur de l'application
     *
     * @param mouseEvent MouseEvent
     */
    @Override
    public void handle(MouseEvent mouseEvent) {
        if (!isDragging) {
            isDragging = true;
        }
        TreeView<File> tv = (TreeView<File>) mouseEvent.getSource();
        Classe temp = modele.getClasseCourante();
        if (temp != null) {
            temp.setCoordinates(mouseEvent.getX()  - tv.getWidth(), mouseEvent.getY());
            modele.changerClasseCourante(temp);
        }

    }
}
