package mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model;

import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.interfacesETabstract.Observateur;

import java.util.ArrayList;

public class Modele {

    //-----------Attributs-----------
    private String nomFichier;
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

    public void ajouterCLasse(String path){}

    public void changerTheme(String nameT){}

    public void changerRepCourant(String path){}

    public void changerAffichageRepCourant(String nomRep) {

    }

    //-----------Getter-----------

    public String getNomFichier() {
        return nomFichier;
    }

    public String getCheminCourant() {
        return cheminCourant;
    }

    public ArrayList<Observateur> getObservateurs() {
        return observateurs;
    }

    public ArrayList<Boolean> getEtatsNav() {
        return etatsNav;
    }
}

