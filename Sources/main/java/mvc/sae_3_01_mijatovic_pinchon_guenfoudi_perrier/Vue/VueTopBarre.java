package mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.Vue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.Utils.ChargementRes;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.controller.*;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.Themes.Theme;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.interfacesETabstract.Observateur;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model.Modele;

import java.util.ArrayList;
import java.util.HashMap;

public class VueTopBarre extends HBox implements Observateur {

    private Modele modele;

    public VueTopBarre(Modele m) {
        this.modele = m;
        this.actualiser();
    }

    /**
     * Actualise l'affichage à chaque modification du modèle
     */
    @Override
    public void actualiser() {
        this.getChildren().clear();
        Theme t = modele.getTheme();
        VBox vBoxGauche = new VBox();
        MenuBar mb = new MenuBar();

        // menu fichier
        Menu menuFichier = new Menu("Fichier");
        MenuItem m1 = new MenuItem("Ouvrir un fichier .dmov");
        ControllerImporterDMOV controllerImporterDMOV = new ControllerImporterDMOV(this.modele);
        m1.setOnAction(controllerImporterDMOV);
        MenuItem m2 = new MenuItem("Sauvegarder");
        if (this.modele.getCheminDMOV() == null) {
            m2.setDisable(true);
        } else {
            m2.setDisable(false);
        }
        ControllerSauvegarderDMOV controllerSauvegarderDMOV = new ControllerSauvegarderDMOV(this.modele);
        m2.setOnAction(controllerSauvegarderDMOV);
        menuFichier.getItems().add(m1);
        menuFichier.getItems().add(m2);
        mb.getMenus().add(menuFichier);

        // menu aide
        Menu menuAide = new Menu("Aide");
        MenuItem m4 = new MenuItem("Prise en main");
        MenuItem m5 = new MenuItem("Export");

        // Evenement pour les bulles d'aide
        m4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                Label l1 = new Label("Quelques petites informations sur le fonctionnement de l'application");
                Label l2 = new Label("- Cliquez sur les répertoires contenus dans votre répertoire courant pour les déplier\n\n" +
                        "- Cliquez sur le dossier \"..\" pour revenir au répertoire précédent \n\n" +
                        "- Vous pouvez changer de répertoire courant soit en cliquant sur l'explorateur, soit en tapant le chemin du dossier à la main\n\n" +
                        "- Pour ajouter des classes au diagramme, il faut soit faire un double-clic dessus, soit les glisser depuis l'arborescence de l'application " +
                        "ou soit en les glissant depuis de fichiers hors application\n\n" +
                        "- Vous pouvez bouger les classes en restant appuyé dessus et en bougeant tout simplement\n\n" +
                        "- Vous pouvez désactiver avec les 4 boutons ronds du haut l'affichage respectifs dans l'ordre des constructeurs, des attributs, des méthodes et des dépendances\n\n" +
                        "- Mais vous pouvez aussi le faire individuellement en faisant un clic droit sur les classes");

                VBox vb = new VBox(l1,l2);
                vb.setSpacing(30);
                vb.setAlignment(Pos.CENTER);

                Scene scene = new Scene(vb,1000, 400);

                Stage newWindow = new Stage();
                newWindow.initModality(Modality.APPLICATION_MODAL);
                newWindow.setScene(scene);
                newWindow.setTitle("Prise en main");
                newWindow.show();
            }
        });
        m5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                Label l1 = new Label("Guide pour l'export");
                Label l2 = new Label("Pour pouvoir enregistrer votre diagramme fraîchement réalisé, il faudra bien évidemment cliquer sur le boton exporter en haut à droite\n\n" +
                        "Ensuite,il faudra rentrer le nom sous lequel vous voulez enregistrer votre diagramme\n\n" +
                        "Puis il vous faut choisir un répertoire dans lequel enregistrer le diagramme en cliquant sur \"Choisir la destination de sauvegarde\" \n\n" +
                        "En dernier lieu, il faudra choisir le format sous lequel enregistrer votre diagramme, puis cliquer sur valider et le tour est joué!");

                VBox vb = new VBox(l1,l2);
                vb.setSpacing(30);
                vb.setAlignment(Pos.CENTER);


                Scene scene = new Scene(vb,1000, 400);

                Stage newWindow = new Stage();
                newWindow.initModality(Modality.APPLICATION_MODAL);
                newWindow.setScene(scene);
                newWindow.setTitle("Export");
                newWindow.show();
            }
        });
        menuAide.getItems().add(m4);
        menuAide.getItems().add(m5);
        mb.getMenus().add(menuAide);

        // menu a propos
        Menu menuAPropos = new Menu("A propos");
        MenuItem m7 = new MenuItem("Auteurs");
        ControllerAuteurs controlAuteurs = new ControllerAuteurs(modele);
        m7.setOnAction(controlAuteurs);
        menuAPropos.getItems().add(m7);
        mb.getMenus().add(menuAPropos);

        // paramétrage de l'affichage de la menuBar
        mb.setBackground(new Background(new BackgroundFill(t.getBoutonClassiques(), null, null)));
        mb.setBorder(new Border(new BorderStroke(t.getBordureEtBtnImportant(), BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(0, 2, 2, 0))));
        for (Menu menu : mb.getMenus()) {
            Label label = new Label(menu.getText());
            label.setTextFill(modele.getTheme().getColorText());
            menu.setGraphic(label);
            menu.setText("");
        }

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
            b.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, co, new BorderWidths(1))));
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
        // Ajout du controleur pour exporter
        ControllerOpenExporterWindow controlExport = new ControllerOpenExporterWindow(modele);
        boutonExporter.setOnAction(controlExport);

        CornerRadii co2 = new CornerRadii(10);
        boutonExporter.setStyle("-fx-font-weight: 900; -fx-font-size: 20");
        boutonExporter.setBorder(new Border(new BorderStroke(t.getBoutonClassiques(), BorderStrokeStyle.SOLID, co2, new BorderWidths(3))));
        boutonExporter.setMinHeight(60.0);
        boutonExporter.setMinWidth(120.0);
        boutonExporter.setBackground(new Background(new BackgroundFill(t.getBordureEtBtnImportant(), co2, null)));
        boutonExporter.setTextFill(t.getColorTextTitle());

        StackPane sp = new StackPane(boutonExporter);
        sp.setPadding(new Insets(10));

        // Création du bouton changement de thème
        HBox them = new HBox();
        Label lbThm = new Label("Thème : ");
        lbThm.setTextFill(modele.getTheme().getColorText());
        ChoiceBox btnTheme = new ChoiceBox();
        for (Theme theme : modele.getListThemes()) {
            btnTheme.getItems().add(theme);
        }
        btnTheme.getItems().add("personnaliser");
        if (btnTheme.getValue() == null)
            btnTheme.setValue(modele.getTheme());

        //changement du thème
        btnTheme.setBorder(new Border(new BorderStroke(t.getBoutonClassiques(), BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(1))));
        btnTheme.setBackground(new Background(new BackgroundFill(t.getBoutonClassiques(),null,null)));

        // Ajout du contrôleur sur le bouton
        ControllerChangementTheme controlTheme = new ControllerChangementTheme(modele);
        btnTheme.setOnAction(controlTheme);

        them.getChildren().addAll(lbThm, btnTheme);
        them.setAlignment(Pos.CENTER);


        // création d'un objet Region afin d'espacer le bouton Exporter du reste des objets
        Region spacer1 = new Region();
        HBox.setHgrow(spacer1, Priority.ALWAYS);
        ImageView img = new ImageView(ChargementRes.getLogo());
        img.setFitHeight(120);
        img.setPreserveRatio(true);
        Region spacer2 = new Region();
        HBox.setHgrow(spacer2, Priority.ALWAYS);

        VBox vb = new VBox(sp, them);
        VBox.setMargin(them, new Insets(0, 10, 0, 0));
        vb.setAlignment(Pos.CENTER);

        this.getChildren().addAll(spacer1, img, spacer2);
        this.getChildren().add(vb);
        this.setSpacing(10);
        this.setBackground(new Background(new BackgroundFill(t.getFondNavEtArbo(), null, null)));
        this.setBorder(new Border(new BorderStroke(t.getBordureEtBtnImportant(), BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(0, 0, 2, 0))));
    }
}
