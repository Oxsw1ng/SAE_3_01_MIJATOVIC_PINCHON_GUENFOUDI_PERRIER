package mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.Vue;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.interfacesETabstract.Observateur;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.interfacesETabstract.Sujet;

import java.util.ArrayList;

public class VueTopBarre extends HBox implements Observateur {

    private Sujet sujet;

    public VueTopBarre(Sujet sujet) {
        this.sujet = sujet;
    }

    @Override
    public void actualiser() {
        this.setPrefSize(1920,120);
        VBox vBoxGauche = new VBox();
        MenuBar mb = new MenuBar();

        // menu fichier
        Menu menuFichier = new Menu("fichier");
        MenuItem m1 = new MenuItem("menu item 1");
        MenuItem m2 = new MenuItem("menu item 2");
        MenuItem m3 = new MenuItem("menu item 3");
        menuFichier.getItems().add(m1);
        menuFichier.getItems().add(m2);
        menuFichier.getItems().add(m3);
        mb.getMenus().add(menuFichier);

        // menu aide
        Menu menuAide = new Menu("aide");
        MenuItem m4 = new MenuItem("menu item 1");
        MenuItem m5 = new MenuItem("menu item 2");
        MenuItem m6 = new MenuItem("menu item 3");
        menuAide.getItems().add(m4);
        menuAide.getItems().add(m5);
        menuAide.getItems().add(m6);
        mb.getMenus().add(menuAide);

        // menu a propos
        Menu menuAPropos = new Menu("à propos");
        MenuItem m7 = new MenuItem("menu item 1");
        MenuItem m8 = new MenuItem("menu item 2");
        MenuItem m9 = new MenuItem("menu item 3");
        menuAPropos.getItems().add(m7);
        menuAPropos.getItems().add(m8);
        menuAPropos.getItems().add(m9);


        mb.getMenus().add(menuAPropos);
        vBoxGauche.getChildren().add(mb);

        HBox boutonModifications = new HBox();

        ArrayList<Button> listeBoutons = new ArrayList<>();
        listeBoutons.add(new Button("C"));
        listeBoutons.add(new Button("A"));
        listeBoutons.add(new Button("M"));
        listeBoutons.add(new Button("H"));

        for (Button b : listeBoutons) {
            boutonModifications.getChildren().add(b);
        }
        boutonModifications.setSpacing(8.0);

        vBoxGauche.getChildren().add(boutonModifications);
        this.getChildren().add(vBoxGauche);

        Button boutonExporter = new Button("exporter");
        boutonExporter.setMinHeight(60.0);
        boutonExporter.setMinWidth(120.0);
        HBox hBoxExporter = new HBox();
        hBoxExporter.setPadding(new Insets(8,0,0,1218));
        hBoxExporter.getChildren().add(boutonExporter);
        this.getChildren().add(hBoxExporter);

    }

}
