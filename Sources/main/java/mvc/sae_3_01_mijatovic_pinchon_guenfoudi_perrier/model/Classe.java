package mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model;

import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.interfacesETabstract.Theme;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;

public class Classe implements Comparable<Classe>, Serializable {
    static final long serialVersionUID = 648175960228010030L;


    //-----------Attributs-----------
    /** Nom de la classe */
    private String nomClasse;
    private String cheminClasse;
    /** Liste des attributs de la classe */
    private ArrayList<String> attributs;
    /** Liste des constructeurs de la classe */
    private ArrayList<String> constructeurs;
    /** Liste des méthodes de la classe */
    private ArrayList<String> methodes;
    /** Coordonnées X de la classe dans l'interface graphique */
    private double coordonnesX;
    /** Coordonnées Y de la classe dans l'interface graphique */
    private double coordonnesY;

    /** Objet Modele utilisé pour accéder à des données nécessaires au fonctionnement de cette classe */
    private Modele modele;

    /** Indique si la classe est une interface ou non */
    private boolean isInterface;
    private String[] implemente;
    private String superClass;

    //-----------Constructeur-----------

    public void setModele(Modele modele) {
        this.modele = modele;
    }

    /**
     * Crée une nouvelle instance de la classe en lisant le fichier .class correspondant au chemin donné en paramètre.
     *
     * @param pathClass chemin du fichier .class
     * @param modele objet Modele utilisé pour accéder à des données nécessaires au fonctionnement de cette classe
     */
    public Classe(String pathClass, Modele modele) {
        this.attributs = new ArrayList<>();
        this.methodes = new ArrayList<>();
        this.constructeurs = new ArrayList<>();
        this.implemente = new String[100];
        try {
            // Execute the javap command

            Process process = Runtime.getRuntime().exec("javap -p " +"\""+ pathClass+"\"");

            // Read the output of the command
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = reader.readLine();

            // recuperer le nom de la classe
            String[] nom = line.split("\"");
            nomClasse = nom[1].split("\\.")[0];

            // test pour savoir si c'est une interface
            line = reader.readLine();
            String[] mots = line.split(" ");
            for (int i = 0; i < mots.length; i++) {
                switch (mots[i]) {
                    case "interface" :
                        this.isInterface = true;
                        break;
                    case "implements" :
                        String[] classes = mots[i+1].split(",");
                        for (int j = 0; j < classes.length; j++) {
                            String c = classes[j];
                            this.implemente[j] = recupType(c);
                        }
                        break;
                    case "extends" :
                        this.superClass = recupType(mots[i+1]);
                        break;
                }
            }



            // continuer pour le reste du contenu
            while ((line = reader.readLine()) != null) {
                line = gererMapEtc(line);
                if (estConstructeur(line)) {
                    peuplerListeMethodes(line,false);
                } else if (estMethode(line)) {
                    peuplerListeMethodes(line,true);
                } else if (estAttribut(line)) {
                    peuplerListeAttributs(line);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.modele = modele;
        this.cheminClasse = pathClass;
    }

    private boolean estAttribut(String line) {
        return line.startsWith("  ");
    }

    public boolean estConstructeur(String ligne) {
        if (ligne.startsWith("  ") && ligne.contains("(") && ligne.contains(")")) {
            ligne = ligne.replaceAll("  public |  private |  protected ", "");
            String[] mots = ligne.split("\\(");
            for (String m : mots) {
                if (recupType(m).equals(this.nomClasse)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean estMethode(String ligne) {
        return (ligne.startsWith("  ") && ligne.contains("(") && ligne.contains(")"));
    }

    public void peuplerListeAttributs(String ligne) {
        ligne = ligne.substring(2);
        String[] mots = ligne.split(" ");
        StringBuilder sb = new StringBuilder();
        int index = 0;

        while ((index<mots.length) && (mots[index].equals("public")
                                    ||  mots[index].equals("private")
                                    ||  mots[index].equals("protected")
                                    ||  mots[index].equals("final")
                                    ||  mots[index].equals("static")
                                    ||  mots[index].equals("abstract")
                                    ||  mots[index].equals("native")
                                    ||  mots[index].equals("synchronized")
                                    ||  mots[index].equals("transient")
                                    ||  mots[index].equals("volatile"))) {
            switch(mots[index]) {
                case "public" :
                    sb.append("+ ");
                    break;
                case "private" :
                    sb.append("- ");
                    break;
                case "protected" :
                    sb.append("# ");
                    break;
                default :
                    sb.append(mots[index]+" ");
                    break;
            }
            index++;
        }
        sb.append(mots[mots.length -1].substring(0, mots[mots.length -1].length()-1)+" : ");
        sb.append(recupType(mots[index]));
        String s = sb.toString();
        s = s.replace(";","");
        this.attributs.add(s);
    }


    public void peuplerListeMethodes(String line, boolean isMethod) throws NoClassDefFoundError {
        String retour = "";

        // preparatifs
        line = line.substring(2);
        String[] parties = line.split(" ");
        switch (parties[0]){
            case "public":
                retour += "+ ";
                break;
            case "private":
                retour += "- ";
                break;
            case "protected":
                retour += "# ";
                break;
        }
        // les elements avant le nom de la methode ou du constructeur
        int i = 1;
        String tmp = "";

        while(!parties[i].contains("(")){
            retour += tmp;
            tmp = recupType(parties[i]);
            i++;
        }
        String typeRetour = tmp;
        if (!isMethod)
            retour += tmp;

        // on arrive au nom
        String[] p = parties[i].split("\\(");
        retour += recupType(p[0])+"(";
        if(p[1].contains(")")){ // si la methode contient 1 ou 0 parametres
            retour += recupType(p[1]);
        }else { // sinon
            i++;
            while(!parties[i-1].contains(")")){
                retour += recupType(parties[i]);
                i++;
            }
        }
        retour = retour.replace(";","");
        // on ajoute
        if (isMethod){
            retour += " : "+typeRetour;
            methodes.add(retour);
        }else{
            constructeurs.add(retour);
        }
    }

    public String getNomClasse() {
        return nomClasse;
    }

    public double getCoordonnesX() {
        return coordonnesX;
    }

    public void setCoordonnesX(double coordonnesX) {
        if (coordonnesX >= 0) {
            this.coordonnesX = coordonnesX;
        } else {
            this.coordonnesX = 0;
        }
    }

    public double getCoordonnesY() {
        return coordonnesY;
    }
    public void setCoordinates(double x,double y){
        this.setCoordonnesX(x);
        this.setCoordonnesY(y);
    }
    public void setCoordonnesY(double coordonnesY) {
        if (coordonnesY >= 0) {
            this.coordonnesY = coordonnesY;
        } else {
            this.coordonnesY = 0;
        }
    }

    public ArrayList<String> getAttributs() {
        return attributs;
    }

    public ArrayList<String> getConstructeurs() {
        return constructeurs;
    }

    public ArrayList<String> getMethodes() {
        return methodes;
    }

    public Theme getTheme(){
        return this.modele.getTheme();
    }

    public boolean isInterface() {
        return isInterface;
    }

    public Modele getModele() {
        return modele;
    }

    public String[] getInterfaces() {
        return this.implemente;
    }

    public String getSuperClass() {
        return this.superClass;
    }

    @Override
    public int compareTo(Classe o) {
        return this.getNomClasse().compareTo(o.getNomClasse());
    }
    public void supprimerClasseDansModele(){
        modele.supprimerClasse(this);
    }

    public String convertirPlantUml() {
        StringBuilder sb = new StringBuilder();
        if (isInterface) {
            sb.append("interface ");
        } else {
            sb.append("class ");
        }
        sb.append(nomClasse);

        sb.append(" {\n");
        for (String attribut : attributs) {
            sb.append("  ").append(attribut).append("\n");
        }
        for (String constructeur : constructeurs) {
            sb.append("  ").append(constructeur).append("\n");
        }
        for (String methode : methodes) {
            sb.append("  ").append(methode).append("\n");
        }
        sb.append("}");
        return sb.toString();

    }

    private String recupType(String s){
        String[] l = s.split("\\.");
        /*
        if (s.contains("<")){
            String[] s1 = s.split("<")[0].split("\\.");
            return s1[s1.length-1] + "<" + l[l.length-1];
        }

         */
        return l[l.length-1];
    }


    private String gererMapEtc(String s){
        String retour="";
        if (s.matches(".*<.*,.*>.*")){ // cas pour tout ce qui est map etc ...
            String[] partiesPossibles = s.split("\\(");
            for (int i = 0; i < partiesPossibles.length; i++) {
                if (partiesPossibles[i].matches(".*<.*,.*>.*")){
                    String partie1 = "";
                    String[] sp1 = partiesPossibles[i].split("<");
                    partie1 += sp1[0] + "<";
                    String[] sp2 = sp1[1].split(">");
                    sp2[0] = sp2[0].replace(" ","") + ">";
                    String[] sp3 = sp2[0].split(",");
                    partie1 += recupType(sp3[0])+","+recupType(sp3[1]);
                    if (sp2.length>1)
                        partie1 += sp2[1];
                    partiesPossibles[i] = partie1 ;
                }
                retour += partiesPossibles[i];
                if (i==0) retour+="(";
            }
        }else if (s.matches(".*<.*>.*")){ // cas des lists etc...
            String[] sp1 = s.split("<");
            String[] sp2 = sp1[1].split(">");
            retour += sp1[0] + "<" + recupType(sp2[0]) + ">";
            if (sp2.length>1) retour += sp2[1];
        }else retour=s;// autres cas
        return retour;
    }
}
