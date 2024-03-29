package mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model.Modele;

/**
 * Controller permettant de gérer l'affichage des auteurs.
 */
public class ControllerAuteurs implements EventHandler<ActionEvent> {

    /**
     * modele de l'application
     */
    private Modele modele;

    /**
     * Instancie un nouveau Controller pour gérer l'affichage des auteurs.
     *
     * @param modele le modele de l'application
     */
    public ControllerAuteurs(Modele modele) {
        this.modele = modele;
    }

    /**
     * Permet de générer une petite page contenant les auteurs
     *
     * @param actionEvent ActionEvent
     */
    @Override
    public void handle(ActionEvent actionEvent) {
        Label l1 = new Label("PERRIER Rémi");
        Label l2 = new Label("GUENFOUDI Naël");
        Label l3 = new Label("PINCHON Théo");
        Label l4 = new Label("MIJATOVIC Yann");

        VBox vb = new VBox(l1, l2, l3, l4);
        vb.setAlignment(Pos.CENTER);
        vb.setSpacing(10);

        Scene scene = new Scene(vb, 150, 150);

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }
}
