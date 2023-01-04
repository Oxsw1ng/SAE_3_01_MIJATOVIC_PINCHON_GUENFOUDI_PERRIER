package mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model;

import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.Themes.ThemeClair;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.interfacesETabstract.Observateur;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.interfacesETabstract.Sujet;

import javax.tools.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;

public class Classe implements Sujet {

    //-----------Attributs-----------
    private String nomClasse;
    private ArrayList<String> attributs;
    private ArrayList<String> constructeurs;
    private ArrayList<String> methodes;
    private ArrayList<Observateur> observateurs;
    private Classe superClass;
    private Class classeCourante;

    private boolean abstractOrNot;
    private int modifierDeLaClasse;

    //-----------Constructeur-----------
    public Classe(String pathJava) {
        this.methodes = new ArrayList<>();
        this.attributs = new ArrayList<>();
        this.constructeurs = new ArrayList<>();
        try {
            //solution 1 : pas très fonctionnelle
            File fichierJava = new File(pathJava);
            String pathClass = pathJava.substring(0, pathJava.length() - 5) + ".class";

            //compilation du fichier .java (argument path du constructeur)
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
            StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null);
            Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromFiles(Arrays.asList(fichierJava));
            JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, diagnostics, null, null, compilationUnits);
            task.call();
            fileManager.close();

            this.nomClasse = pathJava.split("/")[pathJava.split("/").length - 1].split("\\.")[0];
            // lecture du fichier .class créé à partir du chemin donné en paramètre
            ByteArrayClassLoader byteArrayClassLoader = new ByteArrayClassLoader();
            this.classeCourante = byteArrayClassLoader.findClass(nomClasse, pathClass);

            //suppression du fichier .class après utilisation
            File fichierClass = new File(pathClass);
            fichierClass.delete();

            this.peuplerListeMethodes();
            this.peuplerListeConstructeurs();
            this.peuplerListeAttributs();

//            //solution 2
//
//            this.nomClasse = pathJava.split("/")[pathJava.split("/").length - 1].split("\\.")[0];
//            Files.copy(fichierJava.toPath(), new File("Sources/main/java/mvc/sae_3_01_mijatovic_pinchon_guenfoudi_perrier/cache/"+this.nomClasse+".java").toPath());
//            this.classeCourante = Class.forName("mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.cache."+nomClasse);


        } catch (IOException e) {
            System.out.println("erreur lors du chargement du fichier java : " + e.getMessage());
        }


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
            sb.append(" : "+f.getType().getName().split("\\.")[f.getType().getName().split("\\.").length-1]);
            this.attributs.add(sb.toString());
        }
    }

    public VBox affichageBidon() {
        VBox vBoxRetour = new VBox();
        Label lbNom = new Label(this.nomClasse);

        StringBuilder sba = new StringBuilder();
        for (String s : this.attributs) {
            sba.append(s+"\n");
        }
        Label lbAttributs = new Label(sba.toString());


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

        vBoxRetour.getChildren().addAll(lbNom,new Line(0,10,250,10), lbAttributs, new Line(0,250,250,250), lbConstructeurs, lbMethodes);

        ThemeClair thc = new ThemeClair();
        vBoxRetour.setBackground(new Background(new BackgroundFill(thc.getFondClasse(), null, null)));
        vBoxRetour.setBorder(new Border(new BorderStroke(thc.getBordureEtBtnImportant(), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));

        return vBoxRetour;
    }

    public Method[] getMethodes() {
        return classeCourante.getDeclaredMethods();
    }

    public Field[] getAttributs() {
        return classeCourante.getDeclaredFields();
    }

    public Constructor[] getConstructeurs() {
        return classeCourante.getDeclaredConstructors();
    }

}
