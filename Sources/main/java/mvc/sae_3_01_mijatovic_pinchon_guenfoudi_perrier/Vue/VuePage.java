package mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.Vue;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.interfacesETabstract.Observateur;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model.Modele;

public class VuePage extends BorderPane implements Observateur {
    private Modele modele;

    public VuePage(Modele model) {
        this.modele=model;
    }

    @Override
    public void actualiser() {
        this.setBackground(new Background(new BackgroundFill(modele.getTheme().getFondNavEtArbo(), null, null)));
    }
}
