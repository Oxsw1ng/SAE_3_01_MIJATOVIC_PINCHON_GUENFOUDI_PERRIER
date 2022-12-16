package mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.Vue.VueTopBarre;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model.Modele;

public class main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        BorderPane borderPane = new BorderPane();

        // menu en haut
        VueTopBarre vueTopBarre = new VueTopBarre();
        borderPane.setTop(vueTopBarre);
        vueTopBarre.actualiser(new Modele());

        // explorateur de fichier a gauche
        VBox vBoxLeft = new VBox();
        borderPane.setLeft(vBoxLeft);

        // panneau central de l'application
        Pane paneCenter = new Pane();
        borderPane.setCenter(paneCenter);


        Scene scene = new Scene(borderPane, 1920, 1080);
        stage.setScene(scene);
        stage.setTitle("DiagMov'");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
