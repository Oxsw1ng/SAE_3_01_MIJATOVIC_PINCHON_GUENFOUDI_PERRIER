module mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier {
    requires javafx.controls;
    requires javafx.fxml;


    opens mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier to javafx.fxml;
    exports mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier;
    exports main.java.mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier;
    opens main.java.mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier to javafx.fxml;
}