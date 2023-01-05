package mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.Vue;

import javafx.scene.layout.*;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.Themes.ThemeClair;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.controller.ControllerDeplacerClasse;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.interfacesETabstract.Observateur;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model.Classe;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model.Modele;

/**
 * Une vue qui permet d'afficher les informations d'une classe (nom, attributs, constructeurs et méthodes).
 */
public class VueClasse extends VBox implements Observateur {

    private Classe modele;


    public VueClasse(Classe modele) {
        this.modele = modele;
        modele.enregistrerObservateur(this);
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
        ThemeClair thc = new ThemeClair();


        VBox vBoxHaut = new VBox();
        Label lbNom = new Label(modele.getNomClasse());
        vBoxHaut.getChildren().add(lbNom);
        vBoxHaut.setBorder(new Border(new BorderStroke(thc.getBordureEtBtnImportant(), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));


        VBox vBoxMilieu = new VBox();
        StringBuilder sba = new StringBuilder();
        for (String s : modele.getAttributs()) {
            sba.append(s + "\n");
        }
        Label lbAttributs = new Label(sba.toString());
        vBoxMilieu.getChildren().add(lbAttributs);
        vBoxMilieu.setBorder(new Border(new BorderStroke(thc.getBordureEtBtnImportant(), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0, 2, 2, 2))));

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
        vBoxBas.setBorder(new Border(new BorderStroke(thc.getBordureEtBtnImportant(), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0, 2, 2, 2))));


        this.getChildren().addAll(vBoxHaut, vBoxMilieu, vBoxBas);
        this.setBackground(new Background(new BackgroundFill(thc.getFondClasse(), null, null)));
        placerClasse();
    }
    private void placerClasse(){
        System.out.println(this.modele.getCoordonnesX()+":"+modele.getCoordonnesY());
        this.setLayoutX(this.modele.getCoordonnesX());
        this.setLayoutY(this.modele.getCoordonnesY());
    }
    @Override
    public void actualiser() {
        placerClasse();

    }
}

