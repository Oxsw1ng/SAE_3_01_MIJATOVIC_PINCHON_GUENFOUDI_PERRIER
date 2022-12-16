module mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier {
    requires javafx.controls;
    requires javafx.fxml;


    exports mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier;
    opens mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier to javafx.fxml;
    exports mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model;
    opens mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model to javafx.fxml;
}