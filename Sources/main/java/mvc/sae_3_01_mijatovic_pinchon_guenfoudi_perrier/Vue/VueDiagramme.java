package mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.Vue;

import javafx.scene.layout.*;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.interfacesETabstract.Observateur;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model.Classe;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model.Modele;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class VueDiagramme extends Pane implements Observateur {

    private Modele modele;

    private HashMap<Classe,VueClasse> table;

    public VueDiagramme(Modele modele){
        this.modele = modele;
        table = new HashMap<Classe,VueClasse>();
    }

    @Override
    public void actualiser() {
        // mise a jour du thème
        this.setBackground(new Background(new BackgroundFill(modele.getTheme().getColorFond2(), null, null)));

        // mise a jour des données
        table.clear();
        this.getChildren().clear();
        for (Classe c:modele.getClasses()) {
            VueClasse vue = new VueClasse(c);
            table.put(c,vue);
            this.getChildren().add(vue);
        }
        modele.changerGrapheCourant(this);

        for (Classe c:table.keySet()) {
            //ajout des dépendance selon les attributs
            for (String attr:c.getAttributs()){
                String[] t = attr.split(" ");
                Classe retour = lienExistant(t[3]);
                if (retour != null){
                    flecheAgreg(c,retour);
                }
            }
            //ajout des dépendance selon les interfaces
            for (Class inter:c.getInterfaces()){
                String[] nomComplet = c.getSuperClass().getName().split("\\.");
                String nom = nomComplet[nomComplet.length -1];
                Classe retour = lienExistant(nom);
                if (retour != null){
                    flecheAgreg(c,retour);
                }
            }
            //ajout des dépendance selon l'hérédité
            String[] nomComplet = c.getSuperClass().getName().split("\\.");
            String nom = nomComplet[nomComplet.length -1];
            Classe retour = lienExistant(nom);
            if (retour != null){
                flecheHeredite(c,retour);
            }
        }
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

    /**
     * methode qui prend un nom en parametre et verifie si une classe existe au nom la
     * @param nom
     * @return
     */
    private Classe lienExistant(String nom){
        Classe retour = null;
        Iterator it = table.keySet().iterator();
        while (it.hasNext() && retour == null){
            Classe current = (Classe) it.next();
            if (current.getNomClasse().trim().equalsIgnoreCase(nom))
                retour=current;
        }
        return retour;
    }

}
