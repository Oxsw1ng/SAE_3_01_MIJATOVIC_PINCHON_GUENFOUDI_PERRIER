package mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier;

import javafx.scene.image.Image;

/*
 * Classe servant à charger les différentes images
 */
public class ChargementRes {

    // icon du dossier
    public static Image getDossierRes(){
        return new Image(ChargementRes.class.getResource("/mvc/sae_3_01_mijatovic_pinchon_guenfoudi_perrier/dossier.png").toExternalForm());
    }
    // icon du fichier
    public static Image getFichierRes(){
        return new Image(ChargementRes.class.getResource("/mvc/sae_3_01_mijatovic_pinchon_guenfoudi_perrier/fichier.png").toExternalForm());

    }
}
