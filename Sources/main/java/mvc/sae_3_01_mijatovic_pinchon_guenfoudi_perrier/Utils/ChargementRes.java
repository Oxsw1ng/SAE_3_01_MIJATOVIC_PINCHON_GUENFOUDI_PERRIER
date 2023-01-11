package mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.Utils;

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
    public static Image getLogo(){
        return new Image(ChargementRes.class.getResource("/mvc/sae_3_01_mijatovic_pinchon_guenfoudi_perrier/logo.png").toExternalForm());
    }

    public static Image getLetterC(){
        return new Image(ChargementRes.class.getResource("/mvc/sae_3_01_mijatovic_pinchon_guenfoudi_perrier/letter-c.png").toExternalForm());
    }

    public static Image getLetterI(){
        return new Image(ChargementRes.class.getResource("/mvc/sae_3_01_mijatovic_pinchon_guenfoudi_perrier/letter-i.png").toExternalForm());
    }
    public static Image getIco(){
        return new Image(ChargementRes.class.getResource("/mvc/sae_3_01_mijatovic_pinchon_guenfoudi_perrier/dm.png").toExternalForm());
    }
}
