package mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.Vue;

import javafx.event.EventHandler;
import javafx.scene.input.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.controller.ControllerDragExterieur;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.interfacesETabstract.Observateur;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model.Classe;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model.Modele;
import java.util.*;

/**
 * Vue permettant d'afficher le diagramme entièrement
 */
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

    /**
     * Actualisation à chaque changement du modèle principal ou des classes
     */
    @Override
    public void actualiser() {
        // mise a jour du thème
        this.setBackground(new Background(new BackgroundFill(modele.getTheme().getColorFond2(), null, null)));

        // mise a jour des données
        table.clear();
        fleches.clear();
        this.getChildren().clear();
        for (Classe c:modele.getClasses()) {
            c.setModele(this.modele);
            VueClasse vue = new VueClasse(c, this);
            table.put(c,vue);
            this.getChildren().add(vue);
            vue.toFront();
        }
        modele.changerGrapheCourant(this);


       if (!modele.getEtatNav("H")) {
           for (Classe c : table.keySet()) {
               //ajout des dépendance selon les attributs
               for (String attr : c.getAttributs()) {
                   String[] t = attr.split(" ");
                   Classe retour = lienExistant(t[t.length-1]);
                   if (retour != null) {
                       String[] partie1 = attr.split(" :")[0].split(" ");
                       String texte = partie1[partie1.length-1];
                       Fleche f = new Fleche(c, retour, Fleche.FLECHE_AGREGATION, modele, this, texte, levelFLeche(c,retour));
                       fleches.add(f);
                       this.getChildren().add(f);
                       f.toBack();
                   }
               }
               //ajout des dépendance selon les interfaces
               for (String inter : c.getInterfaces()) {
                   Classe retour = lienExistant(inter);
                   if (retour != null) {
                       Fleche f = new Fleche(c, retour, Fleche.FLECHE_IMPLEMENTATION, modele, this,levelFLeche(c,retour));
                       fleches.add(f);
                       this.getChildren().add(f);
                       f.toBack();
                   }
               }
               //ajout des dépendance selon l'hérédité
               Classe retour = lienExistant(c.getSuperClass());
               if (retour != null) {
                   Fleche f = new Fleche(c, retour, Fleche.FLECHE_HEREDITE, modele, this,levelFLeche(c,retour));
                   fleches.add(f);
                   this.getChildren().add(f);
                   f.toBack();
               }
           }
       }
    }



    /**
     * methode qui prend un nom en parametre et verifie si une classe existe à ce nom
     * @param nom
     * @return
     */
    private Classe lienExistant(String nom){
        Classe retour = null;
        if (nom==null)return null;
        if (nom.contains("<")){
            nom = nom.split("<")[1].split(">")[0];
        }
        Iterator it = table.keySet().iterator();
        while (it.hasNext() && retour == null){
            Classe current = (Classe) it.next();
            if (current.getNomClasse().trim().equalsIgnoreCase(nom))
                retour=current;
        }
        return retour;
    }

    /**
     * Autorise le glisser-déposer depuis l'extérieur
     * @param event
     */
    private void handleDragOverEvent(DragEvent event) {
        Dragboard db = event.getDragboard();
        if (db.hasFiles()) {
            event.acceptTransferModes(TransferMode.COPY);
        } else {
            event.consume();
        }
    }

    /**
     * Déplace la flèche selon la position des classes
     */
    public void deplacerFleche(){
        for (Fleche f:fleches) {
            f.deplacer();
        }
    }

    /**
     * Retourne la vueClasse de la classe c entrée en paramètre
     * @param c
     * @return
     */
    public VueClasse getVueClasse(Classe c){
        return table.get(c);
    }

    /**
     * Retourne les flèches
     * @return
     */
    public HashSet<Fleche> getFleches() {
        return fleches;
    }

    private int levelFLeche(Classe c1, Classe c2){
        int i = 0;
        for (Fleche f:fleches) {
            if ((f.getClSource()==c1 && f.getClTarget()==c2)||(f.getClSource()==c2 && f.getClTarget()==c1))
                i++;
        }
        return i;
    }
}
