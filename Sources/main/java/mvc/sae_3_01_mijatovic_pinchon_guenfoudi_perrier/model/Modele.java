package mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model;

import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.Themes.Theme;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.interfacesETabstract.Fabrique;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.interfacesETabstract.Observateur;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.interfacesETabstract.Sujet;

import java.util.ArrayList;

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
    private ArrayList<String> affichageRepCourant;

    //-----------Constructeur-----------
    public Modele(){}

    //-----------Méthodes-----------
    public void genererArborescence(){

        String finChemin = "";
        String[] temp = cheminCourant.split("/");

        for (int i = 0; i< temp.length; i++) {
            finChemin = temp[i];
        }
        affichageRepCourant.add(finChemin);
    }

    public ArrayList<String> getAffichageRepCourant() {
        return affichageRepCourant;
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
        this.genererArborescence();
        this.notifierObservateurs();
    }

    public void changerAffichageRepCourant(String nomRep) {

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

    //méthode du patron observateur

    @Override
    public void enregistrerObservateur(Observateur o) {if (o!=null){this.observateurs.add(o);}}

    @Override
    public void supprimerObservateur(Observateur o) {if (o!=null){this.observateurs.remove(o);}}

    @Override
    public void notifierObservateurs() {for (Observateur o:this.observateurs) {o.actualiser(this);}}
}

