package mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.Themes.ThemeClair;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.Themes.ThemeSombre;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.Vue.VueChoixCouleurThemePerso;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.interfacesETabstract.Theme;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model.Modele;

public class ControllerChangementTheme implements EventHandler<ActionEvent> {

    private Modele modele;

    public ControllerChangementTheme(Modele modele) {
        this.modele = modele;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        ChoiceBox theme = (ChoiceBox) actionEvent.getSource();
        if (theme.getValue() instanceof Theme){
            this.modele.changerTheme((Theme) theme.getValue());
        }else {
            VueChoixCouleurThemePerso.lancerPersoTheme(modele);
        }
    }
}
