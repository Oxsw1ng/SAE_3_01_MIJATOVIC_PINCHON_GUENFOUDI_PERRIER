package mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.Vue;

import javafx.scene.layout.Pane;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.interfacesETabstract.Observateur;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.interfacesETabstract.Sujet;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model.Classe;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model.Modele;

public class VueDiagramme extends Pane implements Observateur {

    private Modele m;

    public VueDiagramme(Sujet s){
        this.m = (Modele) s;
    }

    @Override
    public void actualiser() {
        for (Classe c:m.getClasses()) {

        }
    }

    private void flecheAgreg(Classe source, Classe target){

    }

    private void flecheHeredite(Classe source, Classe target){

    }
    private void flecheImplements(Classe source, Classe target){

    }

}
