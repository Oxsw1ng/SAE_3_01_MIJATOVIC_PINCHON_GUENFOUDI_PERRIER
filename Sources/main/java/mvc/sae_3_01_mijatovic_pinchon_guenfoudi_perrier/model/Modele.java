package mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.Format.DMOV;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.Format.JPG;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.Format.PNG;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.Format.UML;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.interfacesETabstract.*;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.Themes.ThemeClair;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.*;

public class Modele implements Sujet, Serializable {
    static final long serialVersionUID = 648175960228010030L;
    private String cheminDMOV;

    //-----------Attributs-----------
    private String nomFichier;

    private transient Theme theme;
    private Classe classeCourante;
    private TreeSet<Classe> classes;
    private transient Pane grapheCourant;
    private transient Format export;
    private String cheminCourant;
    private transient ArrayList<Observateur> observateurs;
    private HashMap<String, Boolean> etatsNav;

    //-----------Constructeur-----------

    public Modele() {

        //afficher ce projet dans l arborescence
        String[] s = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath().split("/");
        int i=2;
        this.cheminCourant = "";
        boolean Nofind = true;
        if (s.length>1){
            this.cheminCourant = s[1];
            while (Nofind){
                this.cheminCourant+="\\"+s[i];
                if (s[i].equals("SAE_3_01_MIJATOVIC_PINCHON_GUENFOUDI_PERRIER"))
                    Nofind=false;
                i++;
            }
        }
        this.cheminCourant = this.cheminCourant.replace("%20"," ");

        this.observateurs = new ArrayList<Observateur>();
        this.etatsNav = new HashMap<String, Boolean>();
        this.theme=new ThemeClair();
        this.export = new PNG();
        this.grapheCourant = new Pane();
        this.classes = new TreeSet<Classe>();
        this.cheminDMOV = null;
    }

    public Modele (String cheminObjetDMOV) {
        try {
            // Ouvrir un flux de lecture à partir du fichier
            FileInputStream fis = new FileInputStream(cheminObjetDMOV);
            ObjectInputStream ois = new ObjectInputStream(fis);

            // Lire l'objet à partir du fichier
            Modele objDMOV = (Modele) ois.readObject();

            // Fermer les flux
            ois.close();
            fis.close();

            // Accéder et afficher les valeurs des attributs de l'objet
            this.nomFichier = objDMOV.nomFichier;
            this.classeCourante = objDMOV.classeCourante;
            this.classes = objDMOV.classes;
            this.cheminCourant = objDMOV.cheminCourant;
            this.etatsNav = objDMOV.etatsNav;
            this.observateurs = new ArrayList<Observateur>();
            this.theme = new ThemeClair();
            this.export = new PNG();
            this.grapheCourant = new Pane();
            this.cheminDMOV = cheminObjetDMOV;

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
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

    public void changerGrapheCourant(Pane graphe) {
        this.grapheCourant = graphe;
    }
    public void changerModeExport(String type) {
        switch (type) {
            case "JPG":
                this.export = new JPG();
                break;
            case "UML":
                this.export = new UML();
                break;
            case "DMOV":
                this.export = new DMOV();
                break;
            default:
                this.export = new PNG();
                break;
        }
    }

    public void changerNom(String n){
        this.nomFichier = n;
    }

    public void changerClasseCourante(Classe nomClasse) {
        this.classeCourante = nomClasse;
    }

    public void moveClasse(){}

    public void deleteClasse(){}

    public void ajouterClasse(Classe classe){
        if (!classes.contains(classe)) {
            this.classes.add(classe);
        }
        notifierObservateurs();
    }

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



    //-------------Setter------------
    public void setModele(Modele modele) {
        this.nomFichier = modele.getNomFichier();
        this.theme = modele.getTheme();
        this.classeCourante = modele.getClasseCourante();
        this.classes = modele.getClasses();
        this.grapheCourant = modele.getGrapheCourant();
        this.export = modele.getExport();
        this.cheminCourant = modele.getCheminCourant();
        this.etatsNav = modele.getEtatsNav();
        this.cheminDMOV = modele.getCheminDMOV();
        this.notifierObservateurs();
    }

    /*
     * méthode de modifications du chemin courant sans mettre à jour toutes les vues sans trop de raison
     */
    public void setCheminCourant(String cheminCourant) {
        this.cheminCourant = cheminCourant;

        // Si on laisse cette ligne, on peut remonter au répertoire parent en cliquant dessus
        // L'idée est sympathique mais gourmande en temps
        //notifierObservateurs();
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

    public TreeSet<Classe> getClasses() {
        return classes;
    }

    public Theme getTheme() {
        return theme;
    }

    public Format getExport() {
        return export;
    }

    public Pane getGrapheCourant() {
        return grapheCourant;
    }
    public boolean getEtatNav(String clef) {
        return etatsNav.get(clef);
    }
    public String getCheminDMOV() {
        return cheminDMOV;
    }


    //méthode du patron observateur

    @Override
    public void enregistrerObservateur(Observateur o) {if (o!=null){this.observateurs.add(o);}}

    @Override
    public void supprimerObservateur(Observateur o) {if (o!=null){this.observateurs.remove(o);}}

    @Override
    public void notifierObservateurs() {for (Observateur o:this.observateurs) {o.actualiser();}}

    public void supprimerClasse(Classe classeASupprimer){
        classes.remove(classeASupprimer);
        notifierObservateurs();
    }
}

