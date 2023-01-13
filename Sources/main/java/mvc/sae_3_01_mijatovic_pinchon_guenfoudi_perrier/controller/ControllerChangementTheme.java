package mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ChoiceBox;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.Vue.VueChoixCouleurThemePerso;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.Themes.Theme;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model.Modele;

/**
 * Controller permettant de changer le theme de l'application
 */
public class ControllerChangementTheme implements EventHandler<ActionEvent> {

    /**
     * modele de l'application
     */
    private Modele modele;

    /**
     * Instancie un nouveau Controller pour gérer le changement de theme.
     *
     * @param modele le modele de l'application
     */
    public ControllerChangementTheme(Modele modele) {
        this.modele = modele;
    }

    /**
     * Permet de gérer le changement de theme
     *
     * @param actionEvent ActionEvent
     */
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
