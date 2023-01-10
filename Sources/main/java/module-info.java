module mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;
    requires java.compiler;
    requires java.desktop;


    exports mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier;
    opens mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier to javafx.fxml;
    exports mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model;
    opens mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model to javafx.fxml;
    exports mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.interfacesETabstract;
    opens mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.interfacesETabstract to javafx.fxml;
    exports mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.Utils;
    opens mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.Utils to javafx.fxml;
}