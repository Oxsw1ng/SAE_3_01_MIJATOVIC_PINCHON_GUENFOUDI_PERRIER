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
    public static Classe classe;

    public ControllerDragFichier(Modele modele) {
        this.modele = modele;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        if (classe == null) {
            TreeView<File> temp = (TreeView<File>) mouseEvent.getSource();
            TreeItem<File> ti = temp.getSelectionModel().getSelectedItem();
            if (ti != null) {
                File f = ti.getValue();
                if (f.isFile()) {
                    System.out.println("Choisit la classe");
                    classe = new Classe(f.getAbsolutePath(), modele);
                }
            }
        if (mouseEvent.isPrimaryButtonDown()) {
            System.out.println("Drag détecté");
            if (classe != null) {
                classe.setCoordinates(mouseEvent.getX(), mouseEvent.getY());
            } else {
                System.out.println("Classe nulle");
            }
        }
        } else {
            modele.ajouterClasse(classe);
            classe = null;
        }
    }
}
