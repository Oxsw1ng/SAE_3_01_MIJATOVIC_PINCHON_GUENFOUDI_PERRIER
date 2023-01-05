package mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.Vue;

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
       ControllerDeplacerClasse controller = new ControllerDeplacerClasse(modele);
        setOnDragDone(controller);
        
        this.creerVue();


    }

    /**
     * Affiche les informations de la classe dans une boîte de type VBox.
     *
     * @return une boîte de type VBox contenant les informations de la classe
     */
    public void creerVue() {

        VBox vBoxHaut = new VBox();
        Label lbNom = new Label(modele.getNomClasse());
        vBoxHaut.getChildren().add(lbNom);


        VBox vBoxMilieu = new VBox();
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
        vBoxBas.getChildren().addAll(lbConstructeurs, lbMethodes);


        this.getChildren().addAll(vBoxHaut, vBoxMilieu, vBoxBas);
        actualiser(new ThemeClair());
    }
    private void placerClasse(){
        System.out.println(this.modele.getCoordonnesX()+":"+modele.getCoordonnesY());
        this.setLayoutX(this.modele.getCoordonnesX());
        this.setLayoutY(this.modele.getCoordonnesY());
    }
    public void actualiser(Theme theme) {
        placerClasse();
        //changer theme entier
        this.setBackground(new Background(new BackgroundFill(theme.getFondClasse(), null, null)));
        for (Node n:this.getChildren()) {
            VBox v = ((VBox) n);
            v.setBackground(new Background(new BackgroundFill(theme.getFondClasse(), null, null)));
            v.setBorder(new Border(new BorderStroke(theme.getBordureEtBtnImportant(), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0, 2, 2, 2))));
            for (Node n2:v.getChildren()) {
                ((Label)n2).setTextFill(theme.getColorText());
            }
        }
        //le top c est différent
        VBox top = (VBox)this.getChildren().get(0);// au pire c est 1
        top.setBackground(new Background(new BackgroundFill(theme.getTopClasse(), null, null)));
        for (Node n:top.getChildren()) {
            ((Label)n).setTextFill(theme.getColorTextTitle());
        }
    }
}

