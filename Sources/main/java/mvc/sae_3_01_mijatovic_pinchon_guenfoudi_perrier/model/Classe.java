package mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model;

import javafx.scene.control.Label;
import javafx.scene.layout.*;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.Themes.ThemeClair;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.interfacesETabstract.Observateur;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.interfacesETabstract.Sujet;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.interfacesETabstract.Theme;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

public class Classe implements Comparable<Classe> {

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
    private String[] interfaces;
    private String superClass;

    //-----------Constructeur-----------
    /**
     * Crée une nouvelle instance de la classe en lisant le fichier .class correspondant au chemin donné en paramètre.
     *
     * @param pathClass chemin du fichier .class
     * @param modele objet Modele utilisé pour accéder à des données nécessaires au fonctionnement de cette classe
     */
    public Classe(String pathClass, Modele modele) {
        this.modele=modele;
        this.cheminClasse = pathClass;

        // Change le split selon l'OS de l'utilisateur
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            this.nomClasse = pathClass;
            this.nomClasse.replace("\\", "\\\\");
            this.nomClasse = pathClass.split("\\\\")[pathClass.split("\\\\").length - 1].split("\\.")[0];
        } else {
            this.nomClasse = pathClass.split("/")[pathClass.split("/").length - 1].split("\\.")[0];
        }
        if (nomClasse.split("/").length == 2) {
            nomClasse = nomClasse.split("/")[1];
        }

        // lecture du fichier .class créé à partir du chemin donné en paramètre



        ByteArrayClassLoader byteArrayClassLoader = new ByteArrayClassLoader();
        for (Classe c : this.modele.getClasses()) {
            byteArrayClassLoader.findClass(c.nomClasse, c.cheminClasse);
        }
        Class<?> classeCourante;
        classeCourante = byteArrayClassLoader.findClass(nomClasse, pathClass);

        boolean neFonctionnePas = true;
        Class<?> superClass;
        Class<?>[] interfaces;
        while (neFonctionnePas) {
            try {
                this.isInterface = classeCourante.isInterface();
                superClass = classeCourante.getSuperclass();
                interfaces = classeCourante.getInterfaces();

                this.methodes = new ArrayList<>();
                this.attributs = new ArrayList<>();
                this.constructeurs = new ArrayList<>();
                this.peuplerListeMethodes(classeCourante);
                this.peuplerListeConstructeurs(classeCourante);
                this.peuplerListeAttributs(classeCourante);
                neFonctionnePas = false;
            } catch (NoClassDefFoundError e) {
                String path;
                if (os.contains("win")) {
                    path = pathClass;
                    path.replace("\\", "\\\\");
                    path = String.join("\\", Arrays.copyOf(pathClass.split("\\\\"), pathClass.split("\\\\").length-1)) + "\\";
                } else {
                    path = String.join("/", Arrays.copyOf(pathClass.split("/"), pathClass.split("/").length-1)) + "/";
                }
                String nomClasseNonPrimitive =  e.getMessage();
                byteArrayClassLoader.findClass(nomClasseNonPrimitive, path+nomClasseNonPrimitive+".class");
            }
        }

        this.interfaces = new String[100];
        for (int i = 0; i<classeCourante.getInterfaces().length; i++) {
            String[] nomComplet = classeCourante.getInterfaces()[i].getName().split("\\.");
            String nom = nomComplet[nomComplet.length - 1];
            this.interfaces[i] = nom;
        }
        String[] nomCompletSuperClasse = classeCourante.getSuperclass().getName().split("\\.");
        this.superClass = nomCompletSuperClasse[nomCompletSuperClasse.length -1];
    }

    /**
     * Gère les modificateurs d'un membre de la classe (attribut, méthode, etc.) et renvoie une chaîne de caractères
     * représentant ces modificateurs sous forme de caractères spéciaux.
     *
     * @param acces entier représentant les modificateurs du membre de la classe
     * @return chaîne de caractères représentant les modificateurs du membre de la classe
     */
    public String faireModifiers(int acces) {
        StringBuilder sb = new StringBuilder();
        if (Modifier.isPublic(acces)) {
            sb.append("+ ");
        } if (Modifier.isPrivate(acces)) {
            sb.append("- ");
        } if (Modifier.isProtected(acces)) {
            sb.append("# ");
        } if (Modifier.isStatic(acces)) {
            sb.append("static ");
        } if (Modifier.isFinal(acces)) {
            sb.append("final ");
        } if (Modifier.isAbstract(acces)) {
            sb.append("abstract ");
        } if (Modifier.isNative(acces)) {
            sb.append("native ");
        } if (Modifier.isSynchronized(acces)) {
            sb.append("synchronized ");
        } if (Modifier.isTransient(acces)) {
            sb.append("transient ");
        } if (Modifier.isVolatile(acces)) {
            sb.append("volatile ");
        }
        return sb.toString();
    }
    /**
     * Gère les paramètres d'une méthode ou d'un constructeur et renvoie une chaîne de caractères représentant ces
     * paramètres.
     *
     * @param parametres tableau d'objets Class représentant les paramètres de la méthode ou du constructeur
     * @return chaîne de caractères représentant les paramètres de la méthode ou du constructeur
     */
    private String gererParametre(Class[] parametres) {
        StringBuilder sb = new StringBuilder();
        boolean aDesParametres = false;
        for (Class classParam : parametres) {
            aDesParametres = true;
            if (classParam.isArray()) {
                String[] type = classParam.getCanonicalName().split("\\.");
                if (type[type.length - 1].contains("$"))
                    type[type.length - 1] = type[type.length - 1].split("\\$")[type[type.length - 1].split("\\$").length - 1];
                sb.append(type[type.length - 1] + ", ");
            } else {
                String[] type = classParam.getName().split("\\.");
                sb.append(type[type.length - 1] + ", ");
            }
        }
        if (aDesParametres)
            sb.setLength(sb.length() - 2); //permet de supprimer la dernière virgule et l'espace en trop si il y a des paramètres
        return sb.toString();
    }

    private String gererRetour(Class retour) {
        StringBuilder sb = new StringBuilder();
        if (retour.isArray()) {
            String[] type = retour.getCanonicalName().split("\\.");
            if (type[type.length - 1].contains("$"))
                type[type.length - 1] = type[type.length - 1].split("\\$")[type[type.length - 1].split("\\$").length - 1];
            sb.append(type[type.length - 1]);
        } else {
            String[] type = retour.getName().split("\\.");
            sb.append(type[type.length - 1]);
        }
        return sb.toString();
    }

    public void peuplerListeMethodes(Class<?> classeCourante) throws NoClassDefFoundError {
        Method[] tabMethodes = classeCourante.getDeclaredMethods();
        for (Method m : tabMethodes) {
            StringBuilder sb = new StringBuilder();
            sb.append(this.faireModifiers(m.getModifiers()));
            sb.append(m.getName());
            sb.append("("+this.gererParametre(m.getParameterTypes()));
            sb.append(") : "+this.gererRetour(m.getReturnType()));
            this.methodes.add(sb.toString());
        }
    }
    public void peuplerListeConstructeurs(Class<?> classeCourante) throws NoClassDefFoundError{
        URL url = classeCourante.getResource("Date.class");
        if (url != null) {
            System.out.println("MyClass.class found at " + url);
        } else {
            System.out.println("MyClass.class not found on classpath");
        }
        Constructor<?>[] tabConstructeurs = classeCourante.getConstructors();
        System.out.println("0");
        for (Constructor<?> c : tabConstructeurs) {
            StringBuilder sb = new StringBuilder();
            sb.append(this.faireModifiers(c.getModifiers()));
            sb.append(c.getName());
            sb.append("("+this.gererParametre(c.getParameterTypes()));
            sb.append(")");
            this.constructeurs.add(sb.toString());
        }
    }
    public void peuplerListeAttributs(Class<?> classeCourante) throws NoClassDefFoundError {
        Field[] tabConstructeurs = classeCourante.getDeclaredFields();
        for (Field f : tabConstructeurs) {
            StringBuilder sb = new StringBuilder();
            sb.append(this.faireModifiers(f.getModifiers()));
            sb.append(f.getName());
            sb.append(" : "+this.gererRetour(f.getType()));
            this.attributs.add(sb.toString());
        }
    }

    public VBox affichageBidon() {
        VBox vBoxRetour = new VBox();
        ThemeClair thc = new ThemeClair();


        VBox vBoxHaut = new VBox();
        Label lbNom = new Label(this.nomClasse);
        vBoxHaut.getChildren().add(lbNom);
        vBoxHaut.setBorder(new Border(new BorderStroke(thc.getBordureEtBtnImportant(), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));


        VBox vBoxMilieu = new VBox();
        StringBuilder sba = new StringBuilder();
        for (String s : this.attributs) {
            sba.append(s+"\n");
        }
        Label lbAttributs = new Label(sba.toString());
        vBoxMilieu.getChildren().add(lbAttributs);
        vBoxMilieu.setBorder(new Border(new BorderStroke(thc.getBordureEtBtnImportant(), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0,2,2,2))));

        VBox vBoxBas = new VBox();
        StringBuilder sbc = new StringBuilder();
        for (String s : this.constructeurs) {
            sbc.append(s+"\n");
        }
        Label lbConstructeurs = new Label(sbc.toString());
        StringBuilder sbm = new StringBuilder();
        for (String s : this.methodes) {
            sbm.append(s+"\n");
        }
        Label lbMethodes = new Label(sbm.toString());
        vBoxBas.getChildren().addAll(lbConstructeurs, lbMethodes);
        vBoxBas.setBorder(new Border(new BorderStroke(thc.getBordureEtBtnImportant(), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0,2,2,2))));


        vBoxRetour.getChildren().addAll(vBoxHaut,vBoxMilieu,vBoxBas);
        vBoxRetour.setBackground(new Background(new BackgroundFill(thc.getFondClasse(), null, null)));
        return vBoxRetour;
    }

    public String getNomClasse() {
        return nomClasse;
    }

    public void setNomClasse(String nomClasse) {
        this.nomClasse = nomClasse;
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
        return this.interfaces;
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
}
