package mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model.Modele;

/**
 * Controller permettant de gérer l'affichage des options.
 */
public class ControllerAffichageOptions implements EventHandler<ActionEvent> {

    /**
     * modele de l'application
     */
    private Modele modele;

    /**
     * Instancie un nouveau Controller pour gérer l'affichage des options.
     *
     * @param modele le modele de l'application
     */
    public ControllerAffichageOptions(Modele modele) {
        this.modele = modele;
    }


    /**
     * Permet de changer les états des boutons de navigation
     *
     * @param e ActionEvent
     */
    @Override
    public void handle(ActionEvent e) {
        Button b = (Button) e.getSource();
        String bouton = b.getText();
        switch (bouton) {
            case "C" -> modele.changerEtatsNav("C");
            case "A" -> modele.changerEtatsNav("A");
            case "M" -> modele.changerEtatsNav("M");
            case "H" -> modele.changerEtatsNav("H");
            default -> {
            }
        }
    }
}
