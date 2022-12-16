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

public class main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        BorderPane borderPane = new BorderPane();

        // menu en haut
        GridPane gridPaneTop = new GridPane();
        borderPane.setTop(gridPaneTop);

        //menu fichier
        Menu menuFichier = new Menu("Fichier");
        MenuItem m1 = new MenuItem("menu item 1");
        MenuItem m2 = new MenuItem("menu item 2");
        MenuItem m3 = new MenuItem("menu item 3");
        menuFichier.getItems().add(m1);
        menuFichier.getItems().add(m2);
        menuFichier.getItems().add(m3);
        MenuBar mb = new MenuBar();
        mb.getMenus().add(menuFichier);

        Menu menuAide = new Menu("Aide");
        MenuItem m4 = new MenuItem("menu item 1");
        MenuItem m5 = new MenuItem("menu item 2");
        MenuItem m6 = new MenuItem("menu item 3");
        menuAide.getItems().add(m4);
        menuAide.getItems().add(m5);
        menuAide.getItems().add(m6);
        mb.getMenus().add(menuAide);

        Menu menuAPropos = new Menu("APropos");
        MenuItem m7 = new MenuItem("menu item 1");
        MenuItem m8 = new MenuItem("menu item 2");
        MenuItem m9 = new MenuItem("menu item 3");
        menuAPropos.getItems().add(m7);
        menuAPropos.getItems().add(m8);
        menuAPropos.getItems().add(m9);
        mb.getMenus().add(menuAPropos);
        gridPaneTop.add(mb,0,0);

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
