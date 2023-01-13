package mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.Vue;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.Utils.ChargementTheme;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.Themes.Theme;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model.Modele;

import java.util.ArrayList;

/**
 * Crée la fenêtre pour le choix du thème personnalisé
 */
public class VueChoixCouleurThemePerso {

    private static String nom;
    private static Color bordureEtBtnImportant ; //bordure
    private static Color boutonClassiques ; //4
    private static Color fondDiagEtTextField ; //zone de texte
    private static Color fondClasse ; //fond classe
    private static Color fondQuandClicke ;
    private static Color topClasse ; //top classe
    private static Color fondNavEtArbo; //1
    private static Color colorText ; //texte
    private static Color colorTextTitle; // texte important
    private static Color ColorFond2 ; //fond diag
    private static Color colorTxtCls;
    private static TextField tfNom;
    private static VBox droite;
    private static Modele model;
    private static ArrayList<ColorPicker> colorPickers;

    /**
     * Crée le visuel de la fenêtre
     * @param modele
     */
    public static void lancerPersoTheme(Modele modele){
        model = modele;
        colorPickers = new ArrayList<>();
        Stage stage = new Stage();

        BorderPane root = new BorderPane();

        //partie sup
        Label titre = new Label("Personnalisation du thème :");
        titre.setFont(Font.font(titre.getFont().getName(), FontWeight.BOLD, 28));
        root.setTop(titre);
        BorderPane.setAlignment(titre, Pos.CENTER);

        //partie gauche ---------------------------------------
        VBox gauche = new VBox(10);
        //choisir le nom du theme
        HBox nomHb = new HBox();
        {
            Label nomLb = new Label("Nom du thème : ");
            tfNom = new TextField();
            tfNom.setPromptText("Entrer un nom de thème !");
            tfNom.setPrefWidth(200);
            nomHb.getChildren().addAll(nomLb, tfNom);
        }
        gauche.getChildren().add(nomHb);


        VBox app = new VBox(3);
        Label titreApp = new Label("application :");
        titreApp.setFont(new Font(18));
        {
            // ColorPickers
            HBox bordure = new HBox();
            {
                ColorPicker cp = new ColorPicker();
                colorPickers.add(cp);
                cp.setOnAction(e -> {
                    bordureEtBtnImportant = cp.getValue();
                    chargerCouleur();
                });
                bordure.getChildren().addAll(new Label("Bordure : "), cp);
            }
            bordure.setAlignment(Pos.CENTER_RIGHT);
            HBox fondTopArbo = new HBox();
            {
                ColorPicker cp = new ColorPicker();
                colorPickers.add(cp);
                cp.setOnAction(e -> {
                    fondNavEtArbo = cp.getValue();
                    chargerCouleur();
                });
                fondTopArbo.getChildren().addAll(new Label("Fond des éléments : "), cp);
            }
            fondTopArbo.setAlignment(Pos.CENTER_RIGHT);
            HBox fondDiag = new HBox();
            {
                ColorPicker cp = new ColorPicker();
                colorPickers.add(cp);
                cp.setOnAction(e -> {
                    ColorFond2 = cp.getValue();
                    chargerCouleur();
                });
                fondDiag.getChildren().addAll(new Label("Fond du diagramme : "), cp);
            }
            fondDiag.setAlignment(Pos.CENTER_RIGHT);
            HBox boutons = new HBox();
            {
                ColorPicker cp = new ColorPicker();
                colorPickers.add(cp);
                cp.setOnAction(e -> {
                    boutonClassiques = cp.getValue();
                    chargerCouleur();
                });
                boutons.getChildren().addAll(new Label("Boutons : "), cp);
            }
            boutons.setAlignment(Pos.CENTER_RIGHT);
            HBox btnActif = new HBox();
            {
                ColorPicker cp = new ColorPicker();
                colorPickers.add(cp);
                cp.setOnAction(e -> {
                    fondQuandClicke = cp.getValue();
                    chargerCouleur();
                });
                btnActif.getChildren().addAll(new Label("Bouton activé : "), cp);
            }
            btnActif.setAlignment(Pos.CENTER_RIGHT);
            HBox texte = new HBox();
            {
                ColorPicker cp = new ColorPicker();
                colorPickers.add(cp);
                cp.setOnAction(e -> {
                    colorText = cp.getValue();
                    chargerCouleur();
                });
                texte.getChildren().addAll(new Label("Texte : "), cp);
            }
            texte.setAlignment(Pos.CENTER_RIGHT);
            HBox texteImp = new HBox();
            {
                ColorPicker cp = new ColorPicker();
                colorPickers.add(cp);
                cp.setOnAction(e -> {
                    colorTextTitle = cp.getValue();
                    chargerCouleur();
                });
                texteImp.getChildren().addAll(new Label("Texte Important : "), cp);
            }
            texteImp.setAlignment(Pos.CENTER_RIGHT);
            HBox textField = new HBox();
            {
                ColorPicker cp = new ColorPicker();
                colorPickers.add(cp);
                cp.setOnAction(e -> {
                    fondDiagEtTextField = cp.getValue();
                    chargerCouleur();
                });
                textField.getChildren().addAll(new Label("Zone de texte : "), cp);
            }
            textField.setAlignment(Pos.CENTER_RIGHT);

            // on les ajoute
            app.getChildren().addAll(titreApp, bordure, fondTopArbo, fondDiag, boutons, btnActif, texte, texteImp, textField);
        }
        app.setAlignment(Pos.CENTER_RIGHT);
        VBox.setMargin(titreApp, new Insets(0, 180, 0, 0));

        VBox Classe = new VBox(3);
        Label titreClass = new Label("classe :");
        titreClass.setFont(new Font(18));
        {
            // ColorPickers
            HBox HtopClasse = new HBox();
            {
                ColorPicker cp = new ColorPicker();
                colorPickers.add(cp);
                cp.setOnAction(e -> {
                    topClasse = cp.getValue();
                    chargerCouleur();
                });
                HtopClasse.getChildren().addAll(new Label("Entête : "), cp);
            }
            HtopClasse.setAlignment(Pos.CENTER_RIGHT);
            HBox HfondClasse = new HBox();
            {
                ColorPicker cp = new ColorPicker();
                colorPickers.add(cp);
                cp.setOnAction(e -> {
                    fondClasse = cp.getValue();
                    chargerCouleur();
                });
                HfondClasse.getChildren().addAll(new Label("Corps : "), cp);
            }
            HfondClasse.setAlignment(Pos.CENTER_RIGHT);
            HBox HTxtClasse = new HBox();
            {
                ColorPicker cp = new ColorPicker();
                colorPickers.add(cp);
                cp.setOnAction(e -> {
                    colorTxtCls = cp.getValue();
                    chargerCouleur();
                });
                HTxtClasse.getChildren().addAll(new Label("Texte : "), cp);
            }
            HTxtClasse.setAlignment(Pos.CENTER_RIGHT);
            // on les ajoute
            app.getChildren().addAll(titreClass, HtopClasse, HfondClasse, HTxtClasse);
        }
        app.setAlignment(Pos.CENTER_RIGHT);
        VBox.setMargin(titreClass, new Insets(0, 180, 0, 0));


        gauche.getChildren().addAll(app, Classe);
        root.setLeft(gauche);
        BorderPane.setMargin(gauche, new Insets(0, 0, 0, 5));

        //partie doite --------------------------------------
        droite = new VBox(20);
        BorderPane bp = new BorderPane();
        {
            //creation
            HBox top = new HBox();
            top.setPrefHeight(28);
            {
                Pane p = new Pane();
                p.setPrefSize(30, 10);
                HBox.setMargin(p, new Insets(0, 0, 18, 0));
                Pane p2 = new Pane();
                p2.setPrefWidth(25);
                HBox.setMargin(p2, new Insets(6.5, 6.5, 6.5, 0));
                Region spacer = new Region();
                HBox.setHgrow(spacer, Priority.ALWAYS);
                top.getChildren().addAll(p, spacer, p2);
                top.setAlignment(Pos.CENTER);
            }
            Pane center = new Pane();
            center.setPrefSize(150, 100);
            Pane left = new Pane();
            left.setPrefSize(50, 100);

            bp.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));


            //ajout
            bp.setLeft(left);
            bp.setCenter(center);
            bp.setTop(top);
        }

        VBox tf = new VBox(3, new TextField("zone de texte"), new TextField("texte normal"), new TextField("texte important"));

        StackPane p1 = new StackPane();
        p1.setPrefSize(100, 25);
        Label lb = new Label("exemple");
        lb.setTextFill(colorTxtCls);
        lb.setFont(Font.font(lb.getFont().getName(),FontWeight.SEMI_BOLD,FontPosture.REGULAR,14));
        p1.getChildren().add(lb);

        StackPane p2 = new StackPane();
        p2.setPrefSize(100, 100);
        Label lb2 = new Label("exemple");
        lb2.setTextFill(colorTxtCls);
        p2.getChildren().add(lb2);

        VBox classe = new VBox(p1, p2);

        VBox.setMargin(classe,new Insets(0,40,0,40));

        droite.getChildren().addAll(bp, tf, classe);
        droite.setAlignment(Pos.CENTER);

        root.setRight(droite);
        BorderPane.setMargin(droite, new Insets(0, 30, 0, 0));

        initialisationAttrEtCouleurs();

        //partie basse -----------------------------------------------------
        HBox btns = new HBox(10);

        Button btnValidate = new Button("Valider");
        btnValidate.setFont(Font.font(btnValidate.getFont().getName(),FontWeight.BOLD, FontPosture.REGULAR,20));
        btnValidate.setOnAction(e -> {
            if (!alerter()) {
                Theme t = new Theme(nom, bordureEtBtnImportant, boutonClassiques, fondDiagEtTextField, fondQuandClicke, fondClasse, topClasse, fondNavEtArbo, colorText, colorTextTitle, ColorFond2, colorTxtCls);
                model.ajouterTheme(t);
                ChargementTheme.ecrireTheme(t,model,model.getListThemes().size()-1);
                stage.close();
            }
        });

        Button btnReinitialise = new Button("Réinitialiser");
        btnReinitialise.setFont(Font.font(btnValidate.getFont().getName(),FontWeight.BOLD, FontPosture.REGULAR,20));
        btnReinitialise.setOnAction(e -> {initialisationAttrEtCouleurs();});

        btns.getChildren().addAll(btnValidate, btnReinitialise);

        btns.setAlignment(Pos.CENTER);
        root.setBottom(btns);
        BorderPane.setMargin(btns,new Insets(5,0,10,0));


        Scene scene = new Scene(root, 600, 500);
        stage.setResizable(false);
        stage.setTitle("Choix couleurs");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Crée les couleurs de base pour le rendu attendu sur la fenêtre de personnalisation
     */
    private static void initialisationAttrEtCouleurs(){
        nom="";
        bordureEtBtnImportant=Color.BLACK;
        boutonClassiques=Color.WHITE;
        fondDiagEtTextField=Color.WHITE;
        fondClasse=Color.WHITE;
        fondQuandClicke=Color.BLACK;
        topClasse=Color.WHITE;
        fondNavEtArbo=Color.WHITE;
        colorText=Color.BLACK;
        colorTextTitle=Color.WHITE;
        ColorFond2=Color.WHITE;
        colorTxtCls = Color.BLACK;
        chargerCouleur();
        for (ColorPicker cp : colorPickers) {
            cp.setValue(Color.WHITE);
        }
        colorPickers.get(0).setValue(Color.BLACK);
        colorPickers.get(5).setValue(Color.BLACK);
        colorPickers.get(colorPickers.size()-1).setValue(Color.BLACK);
    }

    /**
     * Permet de récupérer le thème personnalisé en relançant l'application
     */
    private static void chargerCouleur() {
        BorderPane bp = (BorderPane) droite.getChildren().get(0);
        HBox top = (HBox) bp.getTop();
        Pane left = (Pane) bp.getLeft();
        Pane right = (Pane) bp.getCenter();
        right.setBackground(new Background(new BackgroundFill(ColorFond2, null, null)));
        left.setBackground(new Background(new BackgroundFill(fondNavEtArbo, null, null)));
        left.setBorder(new Border(new BorderStroke(bordureEtBtnImportant, BorderStrokeStyle.SOLID, null, new BorderWidths(0, 1, 0, 0))));
        top.setBorder(new Border(new BorderStroke(bordureEtBtnImportant, BorderStrokeStyle.SOLID, null, new BorderWidths(0, 0, 1, 0))));
        top.setBackground(new Background(new BackgroundFill(fondNavEtArbo, null, null)));
        {
            Pane p1 = (Pane) top.getChildren().get(0);
            Pane p2 = (Pane) top.getChildren().get(2);
            p1.setBorder(new Border(new BorderStroke(bordureEtBtnImportant, BorderStrokeStyle.SOLID, null, new BorderWidths(0, 1, 1, 0))));
            p2.setBorder(new Border(new BorderStroke(boutonClassiques, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
            p1.setBackground(new Background(new BackgroundFill(boutonClassiques, null, null)));
            p2.setBackground(new Background(new BackgroundFill(bordureEtBtnImportant, null, null)));
        }

        VBox vBox = (VBox) droite.getChildren().get(1);
        TextField tf = (TextField) vBox.getChildren().get(0);
        tf.setEditable(false);
        tf.setStyle("-fx-text-fill: " + model.couleurHexa(colorText));
        tf.setBackground(new Background(new BackgroundFill(fondDiagEtTextField, null, null)));
        TextField tf1 = (TextField) vBox.getChildren().get(1);
        tf1.setEditable(false);
        tf1.setStyle("-fx-text-fill: " + model.couleurHexa(colorText));
        tf1.setBackground(new Background(new BackgroundFill(boutonClassiques, null, null)));
        TextField tf2 = (TextField) vBox.getChildren().get(2);
        tf2.setEditable(false);
        tf2.setStyle("-fx-text-fill: " + model.couleurHexa(colorTextTitle));
        tf2.setBackground(new Background(new BackgroundFill(bordureEtBtnImportant, null, null)));

        VBox classe = (VBox) droite.getChildren().get(2);
        StackPane PtopClasse = (StackPane) classe.getChildren().get(0);
        PtopClasse.setBorder(new Border(new BorderStroke(bordureEtBtnImportant, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
        classe.setBackground(new Background(new BackgroundFill(topClasse, null, null)));
        Label lb1 = (Label) PtopClasse.getChildren().get(0);
        lb1.setTextFill(colorTxtCls);

        StackPane corpsClasse = (StackPane) classe.getChildren().get(1);
        corpsClasse.setBorder(new Border(new BorderStroke(bordureEtBtnImportant, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
        corpsClasse.setBackground(new Background(new BackgroundFill(fondClasse, null, null)));
        Label lb2 = (Label) corpsClasse.getChildren().get(0);
        lb2.setTextFill(colorTxtCls);

    }

    /**
     * Oblige l'utilisateur à donner un nom au thème
     * @return
     */
    private static boolean alerter(){
        if (tfNom.getText()=="") {
            tfNom.setStyle("-fx-prompt-text-fill: red;");
            return true;
        }
        nom=tfNom.getText();
        return false;
    }

}
