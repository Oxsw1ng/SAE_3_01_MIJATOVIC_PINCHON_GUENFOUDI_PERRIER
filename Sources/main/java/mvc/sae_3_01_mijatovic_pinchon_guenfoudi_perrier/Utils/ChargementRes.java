package mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.Utils;

import javafx.scene.image.Image;

/**
 * Classe servant à charger les différentes images
 */
public class ChargementRes {

    /**
     * permet de charger l'icon de dossier
     */
    public static Image getDossierRes(){
        return new Image(ChargementRes.class.getResource("/mvc/sae_3_01_mijatovic_pinchon_guenfoudi_perrier/dossier.png").toExternalForm());
    }

    /**
     * permet de charger l'icon de fichier
     */
    public static Image getFichierRes(){
        return new Image(ChargementRes.class.getResource("/mvc/sae_3_01_mijatovic_pinchon_guenfoudi_perrier/fichier.png").toExternalForm());
    }

    /**
     * permet de charger le logo de l'application
     */
    public static Image getLogo(){
        return new Image(ChargementRes.class.getResource("/mvc/sae_3_01_mijatovic_pinchon_guenfoudi_perrier/logo.png").toExternalForm());
    }

    /**
     * permet de charger l'icon des classes
     */
    public static Image getLetterC(){
        return new Image(ChargementRes.class.getResource("/mvc/sae_3_01_mijatovic_pinchon_guenfoudi_perrier/letter-c.png").toExternalForm());
    }

    /**
     * permet de charger l'icon des classes
     */
    public static Image getLetterA(){
        return new Image(ChargementRes.class.getResource("/mvc/sae_3_01_mijatovic_pinchon_guenfoudi_perrier/A.png").toExternalForm());
    }

    /**
     * permet de charger l'icon des interfaces
     */
    public static Image getLetterI(){
        return new Image(ChargementRes.class.getResource("/mvc/sae_3_01_mijatovic_pinchon_guenfoudi_perrier/letter-i.png").toExternalForm());
    }

    /**
     * permet de charger l'icon de l'application
     */
    public static Image getIco(){
        return new Image(ChargementRes.class.getResource("/mvc/sae_3_01_mijatovic_pinchon_guenfoudi_perrier/dm.png").toExternalForm());
    }
}
