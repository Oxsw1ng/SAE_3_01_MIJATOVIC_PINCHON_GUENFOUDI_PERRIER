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
import java.util.ArrayList;

public class Classe implements Sujet {

    //-----------Attributs-----------
    private String nomClasse;
    private ArrayList<String> attributs;
    private ArrayList<String> constructeurs;
    private ArrayList<String> methodes;
    private ArrayList<Observateur> observateurs;
    private Class superClass;
    private Class classeCourante;
    private Class[] interfaces;
    private double coordonnesX;
    private double coordonnesY;

    private Modele modele;

    private boolean isInterface;

    //-----------Constructeur-----------
    public Classe(String pathClass, Modele modele) {
        this.modele=modele;

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
        this.classeCourante = byteArrayClassLoader.findClass(nomClasse, pathClass);

        this.isInterface = this.classeCourante.isInterface();
        this.superClass = this.classeCourante.getSuperclass();
        this.interfaces = this.classeCourante.getInterfaces();

        this.methodes = new ArrayList<>();
        this.attributs = new ArrayList<>();
        this.constructeurs = new ArrayList<>();
        this.observateurs = new ArrayList<>();
        this.peuplerListeMethodes();
        this.peuplerListeConstructeurs();
        this.peuplerListeAttributs();
    }

    @Override
    public void enregistrerObservateur(Observateur o) {
        this.observateurs.add(o);
    }

    @Override
    public void supprimerObservateur(Observateur o) {
        this.observateurs.remove(o);
    }

    @Override
    public void notifierObservateurs() {
        for (Observateur observateurCourant : this.observateurs) {
            observateurCourant.actualiser();
        }
    }

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

    public void peuplerListeMethodes() {
        Method[] tabMethodes = this.classeCourante.getDeclaredMethods();
        for (Method m : tabMethodes) {
            StringBuilder sb = new StringBuilder();
            sb.append(this.faireModifiers(m.getModifiers()));
            sb.append(m.getName());
            sb.append("("+this.gererParametre(m.getParameterTypes()));
            sb.append(") : "+this.gererRetour(m.getReturnType()));
            this.methodes.add(sb.toString());
        }
    }
    public void peuplerListeConstructeurs() {
        Constructor[] tabConstructeurs = this.classeCourante.getDeclaredConstructors();
        for (Constructor c : tabConstructeurs) {
            StringBuilder sb = new StringBuilder();
            sb.append(this.faireModifiers(c.getModifiers()));
            sb.append(c.getName());
            sb.append("("+this.gererParametre(c.getParameterTypes()));
            sb.append(")");
            this.constructeurs.add(sb.toString());
        }
    }
    public void peuplerListeAttributs() {
        Field[] tabConstructeurs = this.classeCourante.getDeclaredFields();
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
        this.coordonnesX = coordonnesX;
    }

    public double getCoordonnesY() {
        return coordonnesY;
    }
    public void setCoordinates(double x,double y){
        coordonnesX=x;
        coordonnesY=y;
        notifierObservateurs();
    }
    public void setCoordonnesY(double coordonnesY) {
        this.coordonnesY = coordonnesY;
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

    public ArrayList<Observateur> getObservateurs() {
        return observateurs;
    }

    public Class getSuperClass() {
        return superClass;
    }

    public Class getClasseCourante() {
        return classeCourante;
    }

    public Class[] getInterfaces() {return interfaces;}

    public Theme getTheme(){
        return this.modele.getTheme();
    }

    public boolean isInterface() {
        return isInterface;
    }
}
