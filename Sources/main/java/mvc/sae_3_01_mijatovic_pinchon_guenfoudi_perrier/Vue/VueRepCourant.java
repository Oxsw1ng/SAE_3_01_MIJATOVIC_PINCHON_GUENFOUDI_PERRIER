package mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.Vue;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.controller.ControllerArborescenceRepertoire;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.controller.ControllerChoisirRepertoire;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.interfacesETabstract.Observateur;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.interfacesETabstract.Sujet;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model.Modele;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

public class VueRepCourant extends VBox implements Observateur {

    private Modele modele;

    public VueRepCourant(Modele modele) {
        this.modele = modele;
    }

    @Override
    public void actualiser() {
        this.getChildren().clear();
        TextField tf = new TextField(modele.getCheminCourant());
        TreeItem<File> affichageContenuRepertoire = modele.genererArborescence(new TreeItem<File>(new File(modele.getCheminCourant())));
        TreeView<File> tv = new TreeView<>(affichageContenuRepertoire);
        tv.setShowRoot(false);
        ControllerChoisirRepertoire control = new ControllerChoisirRepertoire(modele);
        tf.setOnKeyPressed(control);
        this.getChildren().addAll(tf, tv);
        this.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3))));
    }

}
