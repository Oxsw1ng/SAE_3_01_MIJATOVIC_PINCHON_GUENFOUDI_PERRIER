package mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.Vue.*;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model.Classe;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model.Modele;

public class main extends Application {
    public static double SIZEX = 0;
    public static  double SIZEY = 0;
    @Override
    public void start(Stage stage) throws Exception {
        Modele modele = new Modele();

        VuePage borderPane = new VuePage(modele);
        borderPane.actualiser();

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
        VueDiagramme paneCenter = new VueDiagramme(modele);
        Classe classe1 = new Classe("fichiers_test/Date.class",modele);
        Classe classe2 = new Classe("fichiers_test/Date.class",modele);
        modele.ajouterClasse(classe1);
        modele.ajouterClasse(classe2);
//        paneCenter.flecheAgreg(classe1, classe2);
        borderPane.setCenter(paneCenter);
        paneCenter.actualiser();
        borderPane.setCenter(paneCenter);

        //permet de visualier la flèche entre les deux classes
        PauseTransition pauseTransition = new PauseTransition(new Duration(3000.0));
        pauseTransition.setOnFinished(e -> paneCenter.fleche(paneCenter.pointBordClasse(classe1,classe2),paneCenter.pointBordClasse(classe2,classe1),VueDiagramme.FLECHE_HEREDITE, null));
        pauseTransition.play();

        modele.enregistrerObservateur(paneCenter);
        modele.enregistrerObservateur(borderPane);

        Scene scene = new Scene(borderPane,1000,800);
        stage.setScene(scene);
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