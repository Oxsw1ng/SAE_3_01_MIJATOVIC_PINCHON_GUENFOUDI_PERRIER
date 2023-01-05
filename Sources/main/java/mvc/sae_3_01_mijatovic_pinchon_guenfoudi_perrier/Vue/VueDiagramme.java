package mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.Vue;

import javafx.scene.layout.Pane;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.interfacesETabstract.Observateur;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model.Classe;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model.Modele;

import java.util.ArrayList;

public class VueDiagramme extends Pane implements Observateur {

    private Modele modele;

    public VueDiagramme(Modele modele){
        this.modele = modele;
    }

    @Override
    public void actualiser() {
        this.getChildren().clear();
        ArrayList<Classe> list = modele.getClasses();
        for (Classe c:list) {
            VueClasse vue = new VueClasse(c);
            this.getChildren().add(vue);
        }
        modele.changerGrapheCourant(this);
    }

    private void pointBordClasse(Classe source, Classe target) {


        int[] pointA = {};
        int[] pointB = {}; //point centre classe source
        int[] pointC = {}; //point centre classe target

        int distanceBA = (int) Math.sqrt( (pointA[0]-pointB[0])*(pointA[0]-pointB[0]) + (pointA[1]-pointB[1])*(pointA[1]-pointB[1]));
        int[] vecteurBA = {(pointA[0]-pointB[0]),(pointA[1]-pointB[1])};
        int[] vecteurBC = {(pointC[0]-pointB[0]),(pointC[1]-pointB[1])};

        int[] pointD = {vecteurBC[1] * ((distanceBA * distanceBA) / (vecteurBA[0] * vecteurBC[0] + vecteurBA[1] * vecteurBC[1])) + pointB[0], 1};
    }

    private void flecheAgreg(Classe source, Classe target){

    }

    private void flecheHeredite(Classe source, Classe target){

    }
    private void flecheImplements(Classe source, Classe target){

    }

}
