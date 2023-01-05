package mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model.Modele;

public class ControllerOpenExporterWindow implements EventHandler<ActionEvent> {

    private Modele modele;

    public ControllerOpenExporterWindow(Modele modele) {
        this.modele = modele;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        ChoiceBox<String> cb = new ChoiceBox<String>();
        cb.getItems().addAll("JPG","PNG","UML","DMOV");

        Label format = new Label("Format courant : "+modele.getExport().getNom());

        Button valider = new Button("Valider");
        valider.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String choix = cb.getValue();
                modele.changerModeExport(choix);
                modele.getExport().exporter(modele, "essaiGraphe");
            }
        });
        Button quitter = new Button("Quitter");

        HBox hb = new HBox(valider, quitter);
        hb.setAlignment(Pos.CENTER);
        hb.setSpacing(5);

        VBox vb = new VBox(cb,format,hb);
        vb.setAlignment(Pos.CENTER);
        vb.setSpacing(15);

        Scene scene = new Scene(vb, 200, 200);

        Stage newWindow = new Stage();
        newWindow.setScene(scene);
        newWindow.setTitle("Choisir le mode d'export");
        newWindow.show();

        quitter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                newWindow.close();
            }
        });
    }
}
