package mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.controller;

import javafx.event.EventHandler;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model.Classe;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model.Modele;

import java.io.File;

public class ControllerDragFichier implements EventHandler<MouseEvent> {

    public Modele modele;

    public ControllerDragFichier(Modele modele) {
        this.modele = modele;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        TreeView<File> tv = (TreeView<File>) mouseEvent.getSource();
        Classe temp = modele.getClasseCourante();
        if (temp != null) {
            temp.setCoordinates(mouseEvent.getX()  - tv.getWidth(), mouseEvent.getY());
            modele.changerClasseCourante(temp);
        }

    }
}
