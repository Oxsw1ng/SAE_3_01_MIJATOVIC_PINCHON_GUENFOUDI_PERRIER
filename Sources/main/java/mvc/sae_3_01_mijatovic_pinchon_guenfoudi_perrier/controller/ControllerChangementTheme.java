package mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.Themes.ThemeClair;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.Themes.ThemeSombre;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model.Modele;

public class ControllerChangementTheme implements EventHandler<ActionEvent> {

    private Modele modele;

    public ControllerChangementTheme(Modele modele) {
        this.modele = modele;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        Button theme = (Button) actionEvent.getSource();
        String s = theme.getText();
        switch (s) {
            case "Thème : Clair":
                modele.changerTheme(new ThemeSombre());
                break;
            case "Thème : Sombre":
                modele.changerTheme(new ThemeClair());
                break;
            default:
                break;
        }
    }
}
