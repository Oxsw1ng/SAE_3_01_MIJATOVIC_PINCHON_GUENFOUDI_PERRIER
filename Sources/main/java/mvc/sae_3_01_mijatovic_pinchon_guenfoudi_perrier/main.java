package mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.Utils.ChargementRes;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.Vue.*;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model.Modele;

public class main extends Application {
    @Override
    public void start(Stage stage) {
        Modele modele = new Modele();

        VuePage borderPane = new VuePage(modele);
        borderPane.actualiser();

        // menu en haut
        VueTopBarre vueTopBarre = new VueTopBarre(modele);
        borderPane.setTop(vueTopBarre);
        modele.enregistrerObservateur(vueTopBarre);

        // explorateur de fichier a gauche
        VueRepCourant vueRepCourant = new VueRepCourant(modele);
        borderPane.setLeft(vueRepCourant);
        BorderPane.setMargin(vueRepCourant, new Insets(0, 0, 5, 3));
        vueRepCourant.actualiser();
        modele.enregistrerObservateur(vueRepCourant);

        // panneau central de l'application
        VueDiagramme paneCenter = new VueDiagramme(modele);
        // TEST TEST TEST
        borderPane.setCenter(paneCenter);
        paneCenter.actualiser();

        modele.enregistrerObservateur(paneCenter);
        modele.enregistrerObservateur(borderPane);

        Scene scene = new Scene(borderPane, 1000, 800);
        stage.setScene(scene);
        stage.getIcons().add(ChargementRes.getIco());
        stage.setTitle("DiagMov'");
        stage.setMinWidth(310);
        stage.setMinHeight(400);
        stage.setMaximized(true);
        stage.show();

        vueRepCourant.majBoutonParent();
    }

    public static void main(String[] args) {
        launch(args);
    }
}