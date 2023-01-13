package mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model;

import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.Themes.Theme;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe représentant une classe afin de récupérer distinctement les attributs, méthodes et constructeurs
 */
public class Classe implements Comparable<Classe>, Serializable {


    static final long serialVersionUID = 648175960228010030L;


    //-----------Attributs-----------
    /** Nom de la classe */

    /**
     * nom de la classe
     */
    private String nomClasse;

    /**
     * chemin absolut du fichier classe
     */
    private String cheminClasse;
    /**
     * Liste des attributs de la classe
     */
    private ArrayList<String> attributs;
    /**
     * Liste des constructeurs de la classe
     */
    private ArrayList<String> constructeurs;
    /**
     * Liste des méthodes de la classe
     */
    private ArrayList<String> methodes;
    /**
     * Coordonnées X de la classe dans l'interface graphique
     */
    private double coordonnesX;
    /**
     * Coordonnées Y de la classe dans l'interface graphique
     */
    private double coordonnesY;

    /**
     * Objet Modele utilisé pour accéder à des données nécessaires au fonctionnement de cette classe
     */
    private Modele modele;

    /**
     * Indique si la classe est une interface ou non
     */
    private boolean isInterface;
    /**
     * tableau des nom de classe que celle-ci implemente
     */
    private String[] implemente;
    /**
     * nom de la classe parente
     */
    private String superClass;

    /**
     * Permet d'afficher ou non les attributs de la classe
     */
    private boolean isAttributActive = true;
    /**
     * Permet d'afficher ou non les constructeurs de la classe
     */
    private boolean isConstructeurActive = true;
    /**
     * Permet d'afficher ou non les méthodes de la classe
     */
    private boolean isMethodesActive = true;

    //-----------Constructeur-----------

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
            Process process;
            String system = System.getProperty("os.name").toLowerCase();
            if (system.contains("win")) {
                process = Runtime.getRuntime().exec("javap -p " + "\"" + pathClass + "\"");
            } else {
                process = Runtime.getRuntime().exec("javap -p " + pathClass);
            }


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

    //-----------Méthodes-----------

    /**
     * si la ligne en parametre correspond à un attribut
     *
     * @param line ligne du fichier en cours de lecture
     * @return boolean
     */
    private boolean estAttribut(String line) {
        return line.startsWith("  ");
    }

    /**
     * si la ligne en parametre correspond à un constructeur
     *
     * @param ligne ligne du fichier en cours de lecture
     * @return boolean
     */
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

    /**
     * si la ligne en parametre correspond à une méthode
     *
     * @param ligne ligne du fichier en cours de lecture
     * @return boolean
     */
    public boolean estMethode(String ligne) {
        return (ligne.startsWith("  ") && ligne.contains("(") && ligne.contains(")"));
    }

    /**
     * permet d'ajouter l'attribut représenté par la ligne en paramètre à la liste des attributs
     *
     * @param ligne ligne du fichier en cours de lecture
     * @return
     */
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

    /**
     * permet d'ajouter la méthode représentée par la ligne en paramètre à la liste des méthodes ou des constructeurs selon le boolean en paramètre
     *
     * @param line ligne du fichier en cours de lecture
     * @param isMethod différencie les méthodes des constructeurs
     * @return
     */
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
            retour += tmp+" ";
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

    /**
     * getter du nom de la classe
     *
     * @return String
     */
    public String getNomClasse() {
        return nomClasse;
    }

    /**
     * getter de la coordonnées X de la classe
     *
     * @return double
     */
    public double getCoordonnesX() {
        return coordonnesX;
    }

    /**
     * setter de la coordonnées X de la classe
     *
     * @param coordonnesX
     */
    public void setCoordonnesX(double coordonnesX) {
        if (coordonnesX >= 0) {
            this.coordonnesX = coordonnesX;
        } else {
            this.coordonnesX = 0;
        }
    }

    /**
     * getter de la coordonnée Y de la classe
     *
     * @return double
     */
    public double getCoordonnesY() {
        return coordonnesY;
    }

    /**
     * setter des coordonnées x y
     *
     * @param x coordonnée x
     * @param y coordonnée y
     */
    public void setCoordinates(double x,double y){
        this.setCoordonnesX(x);
        this.setCoordonnesY(y);
    }

    /**
     * setter de la coordonnée Y de la classe
     *
     * @param coordonnesY coordonnée Y
     */
    public void setCoordonnesY(double coordonnesY) {
        if (coordonnesY >= 0) {
            this.coordonnesY = coordonnesY;
        } else {
            this.coordonnesY = 0;
        }
    }


    /**
     * getter des attributs de la classe
     *
     * @return liste de String
     */
    public ArrayList<String> getAttributs() {
        return attributs;
    }

    /**
     * getter des constructeurs
     *
     * @return liste de String
     */
    public ArrayList<String> getConstructeurs() {
        return constructeurs;
    }

    /**
     * getter des méthodes
     *
     * @return lsite de String
     */
    public ArrayList<String> getMethodes() {
        return methodes;
    }

    /**
     * getter du thème courant
     *
     * @return Thème
     */
    public Theme getTheme(){
        return this.modele.getTheme();
    }

    /**
     * si la classe est une interface
     *
     * @return boolean
     */
    public boolean isInterface() {
        return isInterface;
    }

    /**
     * getter du modèle
     *
     * @return Modele
     */
    public Modele getModele() {
        return modele;
    }

    /**
     * getter des interfaces
     *
     * @return tableau de String
     */
    public String[] getInterfaces() {
        return this.implemente;
    }

    /**
     * getter de la classe parente
     *
     * @return String
     */
    public String getSuperClass() {
        return this.superClass;
    }

    /**
     * compare la classe a l'objet en paramètre
     *
     * @param o l'objet à comparer
     * @return
     */
    @Override
    public int compareTo(Classe o) {
        return this.getNomClasse().compareTo(o.getNomClasse());
    }

    /**
     * méthode pour supprimer une classe de la liste de classes du modèle
     */
    public void supprimerClasseDansModele(){
        modele.supprimerClasse(this);
    }

    /**
     * methode pour convertir la classe en plantUml
     *
     * @return String
     */
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

    /**
     * méthode pour récupérer le Type selon une chaine donnée
     *
     * @param s String
     * @return String
     */
    private String recupType(String s){
        String[] l = s.split("\\.");
        return l[l.length-1];
    }


    /**
     * méthode qui va enlever les espaces entre les différents types
     *
     * @param s String
     * @return String
     */
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

    /**
     * si les attributs sont activés et visibles
     *
     * @return boolean
     */
    public boolean isAttributActive() {
        return isAttributActive;
    }

    /**
     * setter du boolean représentant les attributs actifs
     *
     * @param attributActive boolean
     */
    public void setAttributActive(boolean attributActive) {
        isAttributActive = attributActive;
    }

    /**
     * si les Constructeurs sont activés et visibles
     *
     * @return boolean
     */
    public boolean isConstructeurActive() {
        return isConstructeurActive;
    }

    /**
     * setter du boolean représentant les constructeurs actifs
     *
     * @param constructeurActive boolean
     */
    public void setConstructeurActive(boolean constructeurActive) {
        isConstructeurActive = constructeurActive;
    }

    /**
     * si les méthodes sont activées et visibles
     *
     * @return boolean
     */
    public boolean isMethodesActive() {
        return isMethodesActive;
    }

    /**
     * setter du boolean représentant les méthodes actives
     *
     * @param methodesActive boolean
     */
    public void setMethodesActive(boolean methodesActive) {
        isMethodesActive = methodesActive;
    }

    /**
     * setter du modele
     *
     * @param modele Modele
     */
    public void setModele(Modele modele) {
        this.modele = modele;
    }
}
