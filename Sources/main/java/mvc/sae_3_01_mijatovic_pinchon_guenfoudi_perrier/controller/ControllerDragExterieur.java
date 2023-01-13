package mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.controller;

import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model.Classe;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model.Modele;

import java.io.File;

/**
 * Controller permettant de gérer le glisser deposer de fichier .class depuis l'exterieur de l'application
 */
public class ControllerDragExterieur implements EventHandler<DragEvent> {
    /**
     * modele de l'application
     */
    private  Modele modele;

    /**
     * Instancie un nouveau Controller pour gérer le drag and drop depuis l'exterieur de l'application
     *
     * @param modele le modele de l'application
     */
    public ControllerDragExterieur(Modele modele) {
        this.modele=modele;
    }

    /**
     * Permet de gérer le drag and drop depuis l'exterieur de l'application
     *
     * @param dragEvent dragEvent
     */
    @Override
    public void handle(DragEvent dragEvent) {
        File fichierClasse=recupererFichierDepose(dragEvent);
        if(fichierClasse!=null){
            String pathClasse=fichierClasse.getPath();
            Classe nouvelleClasse=new Classe(pathClasse,modele);
            nouvelleClasse.setCoordinates(dragEvent.getX(),dragEvent.getY());
            modele.ajouterClasse(nouvelleClasse);
        }
    }

    /**
     * Traite un événement de glisser-déposer de fichier.
     *
     * @param event l'événement de glisser-déposer
     * @return le fichier .class déposé, ou null si aucun fichier .class n'a été déposé
     */
    private File recupererFichierDepose(DragEvent event) {
        Dragboard db = event.getDragboard();
        File classFile = null;
        if (db.hasFiles()) {
            // Récupérez le premier fichier de la liste de fichiers glissés et déposés
            File file = db.getFiles().get(0);
            // Vérifiez si c'est un fichier .class
            if (file.getName().endsWith(".class")) {
                classFile = file;
            }
        }
        return classFile;
    }

}
