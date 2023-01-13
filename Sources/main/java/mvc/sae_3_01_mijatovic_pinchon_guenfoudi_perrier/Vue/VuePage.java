package mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.Vue;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.interfacesETabstract.Observateur;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model.Modele;

public class VuePage extends BorderPane implements Observateur {

    /**
     * Modèle
     */
    private Modele modele;

    /**
     *
     * @param modele
     */
    public VuePage(Modele modele) {
        this.modele=modele;
    }

    /**
     * Actualise le background du BorderPane à la modification du thème
     */
    @Override
    public void actualiser() {
        this.setBackground(new Background(new BackgroundFill(modele.getTheme().getFondNavEtArbo(), null, null)));
    }
}
