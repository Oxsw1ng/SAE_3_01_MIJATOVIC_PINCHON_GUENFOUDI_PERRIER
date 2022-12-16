package mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model;

import javafx.scene.control.TreeItem;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.Themes.Theme;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.interfacesETabstract.Fabrique;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.interfacesETabstract.Observateur;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.interfacesETabstract.Sujet;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

public class Modele implements Sujet {

    //-----------Attributs-----------
    private String nomFichier;

    private Theme theme;
    private Fabrique fabrique;
    private Classe classeCourante;
    private ArrayList<Classe> classes;

    private String cheminCourant;
    private ArrayList<Observateur> observateurs;
    private ArrayList<Boolean> etatsNav;

    //-----------Constructeur-----------

    public Modele(String cheminCourant) {
        this.cheminCourant = cheminCourant;
        this.observateurs = new ArrayList<Observateur>();
        this.etatsNav = new ArrayList<Boolean>();
    }


    //-----------Méthodes-----------

    /**
     * Permet de créer l'arborescence à l'aide d'appel récursif de cette méthode
     * @return
     */
    public TreeItem<File> genererArborescence(TreeItem<File> chemin){
        TreeItem<File> item = null;
        TreeItem<File> temp = null; // Permet de gérer le fichier ou répertoire courant

        File f = new File(chemin.getValue().getPath());
        if (f.isDirectory()) {
            item = new TreeItem<File>(f);
            String[] contenuRep = f.list();
            File verification = null;
            for (int i = 0; i < contenuRep.length; i++) {

                verification = new File(contenuRep[i]);
                temp = new TreeItem<>(verification);

                // Récursivité si c'est un répertoire, sinon ajout direct à la racine
                if (verification.isDirectory()) {
                    item.getChildren().add(genererArborescence(temp));
                } else {
                    item.getChildren().add(temp);
                }
            }
        } else {
            System.out.println("Problème dans le code java");
        }

        return item;
    }

    public void changerEtatNav(String nomBouton){}

    public void changerNom(String n){
        this.nomFichier = n;
    }

    public void changerClasseCourante(String nomClasse) {
    }

    public void moveClasse(){}

    public void deleteClasse(){}

    public void ajouterClasse(String path){}

    public void changerTheme(String nameT){}

    public void changerRepCourant(String path){
        cheminCourant = path;
        this.notifierObservateurs();
    }

    //-----------Getter-----------

    public String getNomFichier() {
        return nomFichier;
    }

    public String getCheminCourant() {
        return cheminCourant;
    }

    public ArrayList<Boolean> getEtatsNav() {
        return etatsNav;
    }

    public Classe getClasseCourante() {
        return classeCourante;
    }

    //méthode du patron observateur

    @Override
    public void enregistrerObservateur(Observateur o) {if (o!=null){this.observateurs.add(o);}}

    @Override
    public void supprimerObservateur(Observateur o) {if (o!=null){this.observateurs.remove(o);}}

    @Override
    public void notifierObservateurs() {for (Observateur o:this.observateurs) {o.actualiser();}}
}

