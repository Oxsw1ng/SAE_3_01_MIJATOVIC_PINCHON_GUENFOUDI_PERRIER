package mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.Vue.TopBarre;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.Vue.VueRepCourant;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model.Classe;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model.Modele;

public class main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Modele modele = new Modele();

        BorderPane borderPane = new BorderPane();

        // menu en haut
        TopBarre vueTopBarre = new TopBarre(modele);
        borderPane.setTop(vueTopBarre);
        modele.enregistrerObservateur(vueTopBarre);

        // explorateur de fichier a gauche
        VueRepCourant vueRepCourant = new VueRepCourant(modele);
        borderPane.setLeft(vueRepCourant);
        BorderPane.setMargin(vueRepCourant,new Insets(0,0,5,3));
        vueRepCourant.actualiser();
        modele.enregistrerObservateur(vueRepCourant);

        // panneau central de l'application
        Pane paneCenter = new Pane();
        Classe classe = new Classe("fichiers_test/Grep.java");
        VBox vBoxclasse = classe.affichageBidon();
        paneCenter.getChildren().add(vBoxclasse);
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