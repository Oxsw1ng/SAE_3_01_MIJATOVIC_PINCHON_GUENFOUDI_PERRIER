package mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.Vue.VueRepCourant;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.Vue.VueTopBarre;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.interfacesETabstract.Sujet;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model.Modele;

public class main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        Modele modele = new Modele("../SAE_3_01_MIJATOVIC_PINCHON_GUENFOUDI_PERRIER");

        BorderPane borderPane = new BorderPane();

        // menu en haut
        VueTopBarre vueTopBarre = new VueTopBarre(modele);
        borderPane.setTop(vueTopBarre);
        vueTopBarre.actualiser();

        // explorateur de fichier a gauche
        VueRepCourant vueRepCourant = new VueRepCourant(modele);
        borderPane.setLeft(vueRepCourant);
        vueRepCourant.actualiser();

        // panneau central de l'application
        Pane paneCenter = new Pane();
        borderPane.setCenter(paneCenter);


        Scene scene = new Scene(borderPane,1000,800);
        stage.setScene(scene);
        stage.setTitle("DiagMov'");
        stage.setMinWidth(310);
        stage.setMinHeight(400);
        stage.setMaximized(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}