package mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model.Modele;

import java.io.File;

public class ControllerOpenExporterWindow implements EventHandler<ActionEvent> {

    /**
     * Modèle de l'application
     */
    private Modele modele;

    /**
     * Répertoire où le fichier sera enregistré
     */
    private String repertoireSauvegarde;

    /**
     * Constructeur du contrôleur
     * @param modele
     */
    public ControllerOpenExporterWindow(Modele modele) {
        this.modele = modele;
        this.repertoireSauvegarde = null;
    }

    /**
     * Crée la fenêtre pour l'export du diagramme
     * @param actionEvent
     */
    @Override
    public void handle(ActionEvent actionEvent) {
        ChoiceBox<String> cb = new ChoiceBox<String>();
        cb.getItems().addAll("JPG","PNG","UML","DMOV");

        TextField cheminField = new TextField();
        cheminField.setPromptText("Donner un nom à votre création :");
        cheminField.setStyle("-fx-prompt-text-fill: gray;");

        Button directoryChoose = new Button("Choisir la destination de sauvegarde");

        Button valider = new Button("Valider");
        Button quitter = new Button("Quitter");

        HBox hb = new HBox(valider, quitter);
        hb.setAlignment(Pos.CENTER);
        hb.setSpacing(8);

        VBox vb = new VBox(cb,cheminField,directoryChoose,hb);
        vb.setAlignment(Pos.CENTER);
        vb.setSpacing(18);

        Scene scene = new Scene(vb, 300, 300);

        Stage newWindow = new Stage();
        newWindow.initModality(Modality.APPLICATION_MODAL);
        newWindow.setScene(scene);
        newWindow.setTitle("Choisir le mode d'export");
        newWindow.show();

        /**
         * Gère l'explorateur de dossier pour choisir le répertoire de sauvegarde
         */
        directoryChoose.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DirectoryChooser dc = new DirectoryChooser();
                dc.setInitialDirectory(new File("."));
                File f = dc.showDialog(null);
                if (f != null) {
                    repertoireSauvegarde = f.getPath();
                    String system = System.getProperty("os.name").toLowerCase();
                    if (system.contains("win")) {
                        repertoireSauvegarde+="\\";
                    } else {
                        repertoireSauvegarde+="/";
                    }
                }
            }
        });

        /**
         * Contrôleur pour valider l'export
         */
        valider.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String chemin = cheminField.getText();
                String choix = cb.getValue();
                if (chemin != null && choix != null && repertoireSauvegarde != null) {
                    modele.changerModeExport(choix);
                    modele.getExport().exporter(modele, repertoireSauvegarde + chemin);
                    newWindow.close();
                }
            }
        });

        /**
         * Contrôleur pour le bouton quitter (ferme la fenêtre)
         */
        quitter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                newWindow.close();
            }
        });
    }
}
