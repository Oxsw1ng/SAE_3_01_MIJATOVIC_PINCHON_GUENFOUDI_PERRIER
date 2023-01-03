package mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.Vue;

import javafx.geometry.Insets;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.interfacesETabstract.Theme;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.controller.ControllerAffichageOptions;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.controller.ControllerChangementTheme;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.interfacesETabstract.Observateur;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model.Modele;

import java.util.ArrayList;
import java.util.HashMap;

public class TopBarre extends HBox implements Observateur {

    private Modele modele;

    public TopBarre(Modele m) {
        this.modele = m;
        this.actualiser();
    }

    @Override
    public void actualiser() {
        this.getChildren().clear();
        Theme t = modele.getTheme();
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

        // paramétrage de l'affichage de la menuBar
        mb.setBackground(new Background(new BackgroundFill(t.getBoutonClassiques(),null,null)));
        mb.setBorder(new Border(new BorderStroke(t.getBordureEtBtnImportant(), BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(0, 2, 2, 0))));

        vBoxGauche.getChildren().add(mb);

        HBox boutonModifications = new HBox();

        //création des différents boutons
        ArrayList<Button> listeBoutons = new ArrayList<>();
        listeBoutons.add(new Button("C"));
        modele.addEtatsNav("C");
        listeBoutons.add(new Button("A"));
        modele.addEtatsNav("A");
        listeBoutons.add(new Button("M"));
        modele.addEtatsNav("M");
        listeBoutons.add(new Button("H"));
        modele.addEtatsNav("H");

        // affichage des booutons selon le thème du modèle
        CornerRadii co = new CornerRadii(15);
        HashMap<String, Boolean> etatsTemp = modele.getEtatsNav();
        ControllerAffichageOptions controlOptions = new ControllerAffichageOptions(modele);

        for (Button b : listeBoutons) {
            b.setOnAction(controlOptions);
            b.setTextFill(t.getColorText());
            b.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,co,new BorderWidths(1))));
            if (!etatsTemp.get(b.getText())) {
                b.setBackground(new Background(new BackgroundFill(t.getBoutonClassiques(), co, new Insets(0))));
            } else {
                b.setBackground(new Background(new BackgroundFill(t.getFondQuandClicke(), co, new Insets(0))));
            }
            boutonModifications.getChildren().add(b);
        }
        boutonModifications.setSpacing(8.0);
        boutonModifications.setPadding(new Insets(20));

        vBoxGauche.getChildren().add(boutonModifications);
        this.getChildren().add(vBoxGauche);

        // création du bouton Exporter et son affichage
        Button boutonExporter = new Button("exporter");
        CornerRadii co2 = new CornerRadii(10);
        boutonExporter.setStyle("-fx-font-weight: 900; -fx-font-size: 20");
        boutonExporter.setBorder(new Border(new BorderStroke(t.getBoutonClassiques(),BorderStrokeStyle.SOLID,co2,new BorderWidths(3))));
        boutonExporter.setMinHeight(60.0);
        boutonExporter.setMinWidth(120.0);
        boutonExporter.setBackground(new Background(new BackgroundFill(t.getBordureEtBtnImportant(),co2,null)));
        boutonExporter.setTextFill(t.getColorTextTitle());

        StackPane sp = new StackPane(boutonExporter);
        sp.setPadding(new Insets(10));

        // Création du bouton changement de thème
        Button btnTheme = new Button("Thème : "+modele.getTheme().getNom());
        btnTheme.setBorder(new Border(new BorderStroke(t.getBoutonClassiques(),BorderStrokeStyle.SOLID,new CornerRadii(0),new BorderWidths(1))));
        btnTheme.setAlignment(Pos.CENTER);

        // Ajout du contrôleur sur le bouton
        ControllerChangementTheme controlTheme = new ControllerChangementTheme(modele);
        btnTheme.setOnAction(controlTheme);

        // création d'un objet Region afin d'espacer le bouton Exporter du reste des objets
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        VBox vb = new VBox(sp, btnTheme);
        vb.setAlignment(Pos.CENTER);

        this.getChildren().add(spacer);
        this.getChildren().add(vb);
        this.setSpacing(10);
        this.setBackground(new Background(new BackgroundFill(t.getFondNavEtArbo(),null,null)));
        this.setBorder(new Border(new BorderStroke(t.getBordureEtBtnImportant(), BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(0, 0, 2, 0))));
    }
}
