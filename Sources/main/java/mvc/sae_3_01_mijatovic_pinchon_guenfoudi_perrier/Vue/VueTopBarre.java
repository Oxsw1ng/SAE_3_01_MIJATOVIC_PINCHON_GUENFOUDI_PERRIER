package mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.Vue;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.interfacesETabstract.Observateur;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.interfacesETabstract.Sujet;

public class VueTopBarre extends GridPane implements Observateur {

    @Override
    public void actualiser(Sujet s) {

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
        this.add(mb,0,0);
        GridPane.setMargin(mb, new Insets(-60,0,0,0));

        Button boutonExporter = new Button("exporter");
        boutonExporter.setMinHeight(60.0);
        boutonExporter.setMinWidth(120.0);
        this.add(boutonExporter,1,0);
        GridPane.setMargin(boutonExporter, new Insets(20,0,0,1205));


        Button boutonCacherConstructeur = new Button("cacher constructeur");
        Button boutonCacherAttributs = new Button("cacher attributs");
        Button boutonCacherMethodes = new Button("cacher méthodes");
        Button boutonCacherHeritage = new Button("cacher héritage");

        this.add(boutonCacherConstructeur,0,1);
        this.add(boutonCacherAttributs,1,1);
        GridPane.
        this.add(boutonCacherMethodes,2,1);
        GridPane.setMargin(boutonCacherMethodes, new Insets(0,0,0,-1205));
        this.add(boutonCacherHeritage,3,1);
        GridPane.setMargin(boutonCacherHeritage, new Insets(0,0,0,-1005));

    }

}
