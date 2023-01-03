package mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model;

import javafx.scene.paint.Color;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.interfacesETabstract.Theme;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.Themes.ThemeClair;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.interfacesETabstract.Fabrique;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.interfacesETabstract.Observateur;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.interfacesETabstract.Sujet;

import java.util.*;

public class Modele implements Sujet {

    //-----------Attributs-----------
    private String nomFichier;

    private Theme theme;
    private Fabrique fabrique;
    private Classe classeCourante;
    private ArrayList<Classe> classes;

    private String cheminCourant;
    private ArrayList<Observateur> observateurs;
    private HashMap<String, Boolean> etatsNav;

    //-----------Constructeur-----------

    public Modele() {
        this.cheminCourant = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath().split("/")[1].split("\\\\")[0];
        this.observateurs = new ArrayList<Observateur>();
        this.etatsNav = new HashMap<String, Boolean>();
        this.theme=new ThemeClair();
    }


    //-----------Méthodes-----------

    /*
     * méthode servant à récupérer le code hexadécimal d'une couleur donnée afin de pouvoir plus facilement l'affecter au travers de la méthode setStyle().
     */
    public String couleurHexa(Color c){
        String hex1;
        String hex2;

        hex1 = Integer.toHexString(c.hashCode()).toUpperCase();

        switch (hex1.length()) {
            case 2:
                hex2 = "000000";
                break;
            case 3:
                hex2 = String.format("00000%s", hex1.substring(0,1));
                break;
            case 4:
                hex2 = String.format("0000%s", hex1.substring(0,2));
                break;
            case 5:
                hex2 = String.format("000%s", hex1.substring(0,3));
                break;
            case 6:
                hex2 = String.format("00%s", hex1.substring(0,4));
                break;
            case 7:
                hex2 = String.format("0%s", hex1.substring(0,5));
                break;
            default:
                hex2 = hex1.substring(0, 6);
        }
        return "#"+hex2;
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

    /**
     * Ajoute un bouton situé dans la top barre dans la liste des boutons sur lequels leur état est vérifié (actif ou non)
     * @param name : nom du bouton
     */
    public void addEtatsNav(String name) {
        if (!etatsNav.containsKey(name)) {
            etatsNav.put(name, false);
        }
    }

    /**
     * Change l'état d'un bouton
     * @param name : nom du bouton
     */
    public void changerEtatsNav(String name) {
        etatsNav.put(name, !etatsNav.get(name));
        this.notifierObservateurs();
    }

    public void changerTheme(Theme t){
        this.theme=t;
        this.notifierObservateurs();
    }

    /*
     * méthode de modifications du chemin courant sans mettre à jour toutes les vues sans trop de raison
     */
    public void setCheminCourant(String cheminCourant) {
        this.cheminCourant = cheminCourant;
        notifierObservateurs();
    }

    /*
     * méthode de modifications du chemin courant en notifiant toutes les vues
     */
    public void setRepAvecTextField(String s){
        setCheminCourant(s);
        this.notifierObservateurs();
    }

    //-----------Getter-----------

    public String getNomFichier() {
        return nomFichier;
    }

    public String getCheminCourant() {
        return cheminCourant;
    }

    public HashMap<String, Boolean> getEtatsNav() {
        return etatsNav;
    }

    public Classe getClasseCourante() {
        return classeCourante;
    }

    public ArrayList<Classe> getClasses() {
        return classes;
    }

    public Theme getTheme() {
        return theme;
    }

    //méthode du patron observateur

    @Override
    public void enregistrerObservateur(Observateur o) {if (o!=null){this.observateurs.add(o);}}

    @Override
    public void supprimerObservateur(Observateur o) {if (o!=null){this.observateurs.remove(o);}}

    @Override
    public void notifierObservateurs() {for (Observateur o:this.observateurs) {o.actualiser();}}
}

