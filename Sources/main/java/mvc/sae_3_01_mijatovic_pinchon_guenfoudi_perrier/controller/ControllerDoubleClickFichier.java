package mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.controller;

import javafx.event.EventHandler;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model.Classe;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model.Modele;

import java.io.File;

/**
 * Controller permettant de controller le double clique sur l'explorateur de fichier à gauche
 */
public class ControllerDoubleClickFichier implements EventHandler<MouseEvent> {

    /**
     * modele de l'application
     */
    private Modele modele;

    /**
     * Instancie un nouveau Controller pour gérer le double clique
     *
     * @param modele le modele de l'application
     */
    public ControllerDoubleClickFichier(Modele modele) {
        this.modele = modele;
    }

    /**
     * Permet de gérer double clique et importer le fichier .class
     *
     * @param mouseEvent mouseEvent
     */
    @Override
    public void handle(MouseEvent mouseEvent) {
        // Si c'est un fichier java, ajout du controleur permettant de créer les classes
        TreeView<File> temp = (TreeView<File>) mouseEvent.getSource();
        TreeItem<File> ti = temp.getSelectionModel().getSelectedItem();
        if (ti != null) {
            File f = ti.getValue();
            if (f.isFile()) {
                Classe addClasse;
                try {
                    addClasse = new Classe(f.getAbsolutePath(), modele);
                } catch (Exception e) {
                    addClasse = null;
                }
                if (modele.getClasseCourante() == null && addClasse != null) {
                    modele.changerClasseCourante(addClasse);
                }
                if (mouseEvent.getClickCount() == 2) {
                    addClasse.setCoordinates(0, 0);
                    modele.ajouterClasse(addClasse);
                }
            }
        }
    }
}
