package mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.Vue;

import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.Utils.ChargementRes;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.controller.ControllerDeplacerClasse;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.interfacesETabstract.Theme;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model.Classe;

import java.util.ArrayList;

/**
 * Une vue qui permet d'afficher les informations d'une classe (nom, attributs, constructeurs et méthodes).
 */
public class VueClasse extends VBox {

    private Classe modele;
    private VBox attributs;
    private Label methodes;
    private Label constructeurs;

    public VueClasse(Classe modele) {
        this.modele = modele;
        ControllerDeplacerClasse controller = new ControllerDeplacerClasse(modele, this);
        setOnMousePressed(e -> {
            System.out.println(e.getX());
            if (estSurBordure(e)) {
                controller.RedimensionnementEstActif();
                this.setCursor(Cursor.SE_RESIZE);
            } else {
                this.setCursor(Cursor.CLOSED_HAND);
            }

            controller.setxDuClique(e.getX());
            controller.setyDuClique(e.getY());
        });
        setOnMouseReleased(e -> {
           /* if(estSurStroke(e))
                this.setCursor(Cursor.SE_RESIZE);
            else {*/
            controller.redimensionnementEstInnactif();
            this.setCursor(Cursor.CLOSED_HAND);
//            }

        });
        setOnMouseDragged(controller);
        this.setCursor(Cursor.OPEN_HAND);
        this.creerVue();
    }

    /**
     * Affiche les informations de la classe dans une boîte de type VBox.
     *
     * @return une boîte de type VBox contenant les informations de la classe
     */
    public void creerVue() {
        VBox vBoxHaut = creerVBoxHaut();

        attributs = creerVBoxAttributs();
        constructeurs = creerLabelConstructeur();
        methodes = creerLabelMethode();

        ArrayList<Node> listNode = new ArrayList<>();
        listNode.add(vBoxHaut);
        if (!modele.getModele().getEtatNav("A"))
            listNode.add(attributs);
        if (!modele.getModele().getEtatNav("C"))
            listNode.add(constructeurs);
        if (!modele.getModele().getEtatNav("M"))
            listNode.add(methodes);
        this.getChildren().addAll(listNode);

        ContextMenu contextMenu = creerMenuContextuel();
        this.setOnContextMenuRequested(event -> contextMenu.show(this, event.getScreenX(), event.getScreenY()));

        mettreStrokeAndBackground();
        placerClasse(modele.getCoordonnesX(), modele.getCoordonnesY());

    }

    public void mettreStrokeAndBackground() {
        Theme t = modele.getTheme();

        this.setBackground(new Background(new BackgroundFill(t.getFondClasse(), null, null)));
        this.setBorder(new Border(new BorderStroke(t.getBordureEtBtnImportant(), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2, 2, 2, 2))));

    }

    public VBox creerVBoxHaut() {
        Theme t = modele.getTheme();

        VBox vBoxHaut = new VBox();
        vBoxHaut.setBackground(new Background(new BackgroundFill(t.getTopClasse(), null, null)));
        vBoxHaut.setBorder(new Border(new BorderStroke(t.getBordureEtBtnImportant(), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0, 0, 2, 0))));
        ImageView iv;
        if (modele.isInterface()) {
            iv = new ImageView(ChargementRes.getLetterI());
        } else {
            iv = new ImageView(ChargementRes.getLetterC());
        }
        iv.setPreserveRatio(true);
        iv.setFitHeight(15);

        Label lbNom = new Label(modele.getNomClasse());
        lbNom.setTextFill(t.getColorTextTitle());
        lbNom.setGraphic(iv);
        vBoxHaut.getChildren().add(lbNom);

        return vBoxHaut;
    }

    public VBox creerVBoxAttributs() {
        Theme t = modele.getTheme();

        VBox vBoxMilieu = new VBox();
        vBoxMilieu.setBorder(new Border(new BorderStroke(t.getBordureEtBtnImportant(), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0, 0, 2, 0))));

        StringBuilder sba = new StringBuilder();
        for (String s : modele.getAttributs()) {
            sba.append(s + "\n");
        }
        Label lbAttributs = new Label(sba.toString());
        lbAttributs.setTextFill(t.getColorText());
        vBoxMilieu.getChildren().add(lbAttributs);

        return vBoxMilieu;
    }

    public Label creerLabelConstructeur() {
        Theme t=modele.getTheme();
        StringBuilder sbc = new StringBuilder();
        for (String s : modele.getConstructeurs()) {
            sbc.append(s + "\n");
        }
        Label lbConstructeurs = new Label(sbc.toString());
        lbConstructeurs.setTextFill(t.getColorText());
        return lbConstructeurs;
    }

    public Label creerLabelMethode() {
        Theme t=modele.getTheme();
        StringBuilder sbm = new StringBuilder();
        for (String s : modele.getMethodes()) {
            sbm.append(s + "\n");
        }
        Label lbMethodes = new Label(sbm.toString());
        lbMethodes.setTextFill(t.getColorText());
        return lbMethodes;
    }


    public void placerClasse(double x, double y) {
        this.setLayoutX(x);
        this.setLayoutY(y);
    }

    public Classe getModele() {
        return modele;
    }

    public int getLargeurClasse() {
        return (int) this.getWidth();
    }

    public int getHauteurClasse() {
        return (int) this.getHeight();
    }

    private ContextMenu creerMenuContextuel() {
        ContextMenu contextMenu = new ContextMenu();

        MenuItem suppression = new MenuItem("Supprimer la classe");

        MenuItem masquerAttribut = new MenuItem("masquer attribut");
        MenuItem masquerMethode = new MenuItem("masquer methode");
        MenuItem masquerConstructeur = new MenuItem("masquer constructeur");

        MenuItem afficherAttribut = new MenuItem("afficher attributs");
        MenuItem afficherMethode = new MenuItem("afficher methode");
        MenuItem afficherConstructeur = new MenuItem("afficher constructeur");

        suppression.setOnAction(actionEvent -> modele.supprimerClasseDansModele());

        masquerAttribut.setOnAction(actionEvent -> {
            cacherAttribut();
            int index = contextMenu.getItems().indexOf(masquerAttribut);
            contextMenu.getItems().remove(masquerAttribut);
            contextMenu.getItems().add(index, afficherAttribut);
        });
        masquerMethode.setOnAction(actionEvent -> {
            cacherMethodes();
            int index = contextMenu.getItems().indexOf(masquerMethode);
            contextMenu.getItems().remove(masquerMethode);
            contextMenu.getItems().add(index, afficherMethode);
        });
        masquerConstructeur.setOnAction(actionEvent -> {
            cacherConstructeurs();
            int index = contextMenu.getItems().indexOf(masquerConstructeur);
            contextMenu.getItems().remove(masquerConstructeur);
            contextMenu.getItems().add(index, afficherConstructeur);
        });

        afficherAttribut.setOnAction(actionEvent -> {
            voirAttribut();
            int index = contextMenu.getItems().indexOf(afficherAttribut);
            contextMenu.getItems().remove(afficherAttribut);
            contextMenu.getItems().add(index, masquerAttribut);
        });
        afficherMethode.setOnAction(actionEvent -> {
            voirMethode();
            int index = contextMenu.getItems().indexOf(afficherMethode);
            contextMenu.getItems().remove(afficherMethode);
            contextMenu.getItems().add(index, masquerMethode);
        });
        afficherConstructeur.setOnAction(actionEvent -> {
            voirConstructeur();
            int index = contextMenu.getItems().indexOf(afficherConstructeur);
            contextMenu.getItems().remove(afficherConstructeur);
            contextMenu.getItems().add(index, masquerConstructeur);
        });

        contextMenu.getItems().addAll(suppression, masquerAttribut, masquerMethode, masquerConstructeur);
        return contextMenu;
    }

    private void voirAttribut() {
        this.getChildren().add(1, attributs);
    }

    private void voirMethode() {
        int sizeVbox = this.getChildren().size();
        this.getChildren().add(sizeVbox, methodes);

    }

    private void voirConstructeur() {
        int sizeVbox=this.getChildren().size();
        if(sizeVbox>3)
            this.getChildren().add(2, constructeurs);
        else
            this.getChildren().add(1,constructeurs);
    }

    private void cacherAttribut() {
        this.getChildren().remove(attributs);
    }

    private void cacherMethodes() {
        this.getChildren().remove(methodes);
    }

    private void cacherConstructeurs() {
        this.getChildren().remove(constructeurs);
    }
    /**

     Méthode pour vérifier si le clic de souris est sur toute la bordure de l'élément.

     @param mouseEvent l'événement de clic de souris

     @return vrai si le clic est sur toute la bordure, faux sinon
     */
    public boolean estSurBordure(MouseEvent mouseEvent) {
        Node node = (Node) mouseEvent.getSource();
        double x = mouseEvent.getX();
        double y = mouseEvent.getY();
        double width = node.getBoundsInLocal().getWidth();
        double height = node.getBoundsInLocal().getHeight();

        return (x < 5 || x > width - 5 || y < 5 || y > height - 5);
    }

    /**

     Méthode pour vérifier si le clic de souris est sur le bord inférieur et droit de l'élément.

     @param mouseEvent l'événement de clic de souris

     @return vrai si le clic est sur le bord inférieur et droit, faux sinon
     */
    public boolean estSurBordInferieurEtDroit(MouseEvent mouseEvent) {
        Node node = (Node) mouseEvent.getSource();
        double x = mouseEvent.getX();
        double y = mouseEvent.getY();
        double width = node.getBoundsInLocal().getWidth();
        double height = node.getBoundsInLocal().getHeight();

        return (x > width - 5 || y > height - 5);
    }

    /**

     Méthode pour vérifier si le clic de souris est sur le bord gauche et haut de l'élément.
     @param mouseEvent l'événement de clic de souris
     @return vrai si le clic est sur le bord gauche et haut, faux sinon
     */
    public boolean estSurBordGaucheEtHaut(MouseEvent mouseEvent) {
        Node node = (Node) mouseEvent.getSource();
        double x = mouseEvent.getX();
        double y = mouseEvent.getY();
        return (x < 5 || y < 5);
    }
}

