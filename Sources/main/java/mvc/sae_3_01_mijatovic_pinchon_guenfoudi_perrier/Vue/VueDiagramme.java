package mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.Vue;

import javafx.animation.PauseTransition;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.controller.ControllerDragExterieur;
import javafx.scene.shape.Polygon;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.interfacesETabstract.Observateur;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model.Classe;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model.Modele;


import java.awt.*;
import java.util.*;

public class VueDiagramme extends Pane implements Observateur {

    private Modele modele;

    private HashMap<Classe,VueClasse> table;
    private HashSet<Fleche> fleches;

    public VueDiagramme(Modele modele){
        this.modele = modele;
        table = new HashMap<Classe,VueClasse>();
        this.setOnDragOver(this::handleDragOverEvent);
        this.setOnDragDropped(new ControllerDragExterieur(modele));
        fleches = new HashSet<Fleche>();

    }

    @Override
    public void actualiser() {
        // mise a jour du thème
        this.setBackground(new Background(new BackgroundFill(modele.getTheme().getColorFond2(), null, null)));

        // mise a jour des données
        table.clear();
        fleches.clear();
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
                   Fleche f = new Fleche(c,retour,Fleche.FLECHE_AGREGATION,modele,this,t[1]);
                   fleches.add(f);
                   this.getChildren().add(f);
               }
           }
           //ajout des dépendance selon les interfaces
           for (Class inter:c.getInterfaces()){
               String[] nomComplet = c.getSuperClass().getName().split("\\.");
               String nom = nomComplet[nomComplet.length -1];
               Classe retour = lienExistant(nom);
               if (retour != null){
                   Fleche f = new Fleche(c,retour,Fleche.FLECHE_IMPLEMENTATION,modele,this);
                   fleches.add(f);
                   this.getChildren().add(f);
               }
           }
           //ajout des dépendance selon l'hérédité
           String[] nomComplet = c.getSuperClass().getName().split("\\.");
           String nom = nomComplet[nomComplet.length -1];
           Classe retour = lienExistant(nom);
           if (retour != null){
               Fleche f = new Fleche(c,retour,Fleche.FLECHE_HEREDITE,modele,this);
               fleches.add(f);
               this.getChildren().add(f);
           }
       }
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
    private void handleDragOverEvent(DragEvent event) {
        Dragboard db = event.getDragboard();
        if (db.hasFiles()) {
            event.acceptTransferModes(TransferMode.COPY);
        } else {
            event.consume();
        }
    }

    public void deplacerFleche(){
        for (Fleche f:fleches) {
            f.deplacer();
        }
    }

    public VueClasse getVueClasse(Classe c){
        return table.get(c);
    }

}
