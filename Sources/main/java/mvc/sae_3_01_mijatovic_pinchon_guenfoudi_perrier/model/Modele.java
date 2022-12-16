package mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model;

import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.interfacesETabstract.Observateur;

import java.util.ArrayList;

public class Modele {

    //-----------Attributs-----------
    private String nomFicheir;
    private String cheminCourant;
    private ArrayList<Observateur> observateurs;
    private ArrayList<Boolean> etatsNav;

    //-----------Constructeur-----------
    public Modele(){}

    //-----------Méthodes-----------
    public String[] genererArborescence(){
        return null;
    }

    public void changerEtatNav(String nomBouton){}

    public void changerNom(String n){}

    public void changerClasseCourante(String nomClasse){}

    public void moveClasse(){}

    public void deleteClasse(){}

    public void ajouterCLasse(String path){}

    public void changerTheme(String nameT){}

    public void changerRepCourant(String path){}

    public void changerAffichageRepCourant(String nomRep){}

    //-----------Getter-----------

    public String getNomFicheir() {
        return nomFicheir;
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

