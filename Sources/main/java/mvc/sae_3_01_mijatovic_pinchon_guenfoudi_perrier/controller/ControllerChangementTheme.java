package mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ChoiceBox;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.Vue.VueChoixCouleurThemePerso;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.Themes.Theme;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model.Modele;

public class ControllerChangementTheme implements EventHandler<ActionEvent> {

    private Modele modele;

    public ControllerChangementTheme(Modele modele) {
        this.modele = modele;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        ChoiceBox source = (ChoiceBox) actionEvent.getSource();
        if (source.getValue() instanceof Theme){
            this.modele.changerTheme((Theme) source.getValue());
        }else {
            VueChoixCouleurThemePerso.lancerPersoTheme(modele);
        }
    }
}
