package mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.Vue;

import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.interfacesETabstract.Observateur;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.interfacesETabstract.Sujet;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model.Modele;

import java.util.ArrayList;
import java.util.Iterator;

public class VueRepCourant extends VBox implements Observateur {

    private Modele modele;

    public VueRepCourant(Modele modele) {
        this.modele = modele;
    }

    @Override
    public void actualiser(Sujet s) {
        HBox ajout = new HBox();
        ArrayList<String> repFichAffiches = modele.getAffichageRepCourant();
        Iterator<String> it = repFichAffiches.iterator();
        while (it.hasNext()) {
            ajout.getChildren().add(new Label(it.next()));
            this.getChildren().add(ajout);
            ajout = new HBox();
        }
        this.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3))));
    }

}
