package mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.Vue;

import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.Themes.ThemeClair;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.controller.ControllerDeplacerClasse;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.interfacesETabstract.Observateur;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.interfacesETabstract.Theme;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model.Classe;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model.Modele;

/**
 * Une vue qui permet d'afficher les informations d'une classe (nom, attributs, constructeurs et méthodes).
 */
public class VueClasse extends VBox {

    private Classe modele;


    public VueClasse(Classe modele) {
        this.modele = modele;
       ControllerDeplacerClasse controller = new ControllerDeplacerClasse(modele,this);
        setOnMouseDragged(controller);
        this.setCursor(Cursor.OPEN_HAND);
        setOnMousePressed(e -> {this.setCursor(Cursor.CLOSED_HAND);controller.setxDuClique(e.getX());controller.setyDuClique(e.getY());});
        setOnMouseReleased(e -> this.setCursor(Cursor.OPEN_HAND));

        this.creerVue();


    }

    /**
     * Affiche les informations de la classe dans une boîte de type VBox.
     *
     * @return une boîte de type VBox contenant les informations de la classe
     */
    public void creerVue() {
        Theme t = this.modele.getTheme();

        VBox vBoxHaut = new VBox();
        vBoxHaut.setBackground(new Background(new BackgroundFill(t.getTopClasse(), null, null)));
        vBoxHaut.setBorder(new Border(new BorderStroke(t.getBordureEtBtnImportant(), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2, 2, 2, 2))));
        Label lbNom = new Label(modele.getNomClasse());
        vBoxHaut.getChildren().add(lbNom);


        VBox vBoxMilieu = new VBox();
        vBoxMilieu.setBackground(new Background(new BackgroundFill(t.getFondClasse(), null, null)));
        vBoxMilieu.setBorder(new Border(new BorderStroke(t.getBordureEtBtnImportant(), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0, 2, 2, 2))));
        StringBuilder sba = new StringBuilder();
        for (String s : modele.getAttributs()) {
            sba.append(s + "\n");
        }
        Label lbAttributs = new Label(sba.toString());
        vBoxMilieu.getChildren().add(lbAttributs);

        VBox vBoxBas = new VBox();
        StringBuilder sbc = new StringBuilder();
        for (String s : modele.getConstructeurs()) {
            sbc.append(s + "\n");
        }
        Label lbConstructeurs = new Label(sbc.toString());
        StringBuilder sbm = new StringBuilder();
        for (String s : modele.getMethodes()) {
            sbm.append(s + "\n");
        }
        Label lbMethodes = new Label(sbm.toString());
        vBoxBas.setBackground(new Background(new BackgroundFill(t.getFondClasse(), null, null)));
        vBoxBas.setBorder(new Border(new BorderStroke(t.getBordureEtBtnImportant(), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0, 2, 2, 2))));
        vBoxBas.getChildren().addAll(lbConstructeurs, lbMethodes);


        this.getChildren().addAll(vBoxHaut, vBoxMilieu, vBoxBas);
        this.setBackground(new Background(new BackgroundFill(t.getFondClasse(), null, null)));
        placerClasse(modele.getCoordonnesX(),modele.getCoordonnesY());

    }
    public void placerClasse(double x, double y){
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
}

