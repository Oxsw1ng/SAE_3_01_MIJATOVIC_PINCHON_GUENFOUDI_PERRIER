package mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model;

import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.Themes.Theme;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.interfacesETabstract.Fabrique;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.interfacesETabstract.Observateur;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.interfacesETabstract.Sujet;

import java.util.ArrayList;

public class Modele implements Sujet {

    //-----------Attributs-----------
    private String nomFicheir;
    private Theme theme;
    private Fabrique fabrique;
    private Classe classeCourante;
    private ArrayList<Classe> classes;
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

