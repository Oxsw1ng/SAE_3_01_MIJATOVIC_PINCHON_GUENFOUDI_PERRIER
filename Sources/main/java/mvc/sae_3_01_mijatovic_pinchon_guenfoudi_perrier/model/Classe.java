package mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model;

import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.interfacesETabstract.Observateur;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.interfacesETabstract.Sujet;

import javax.tools.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;

public class Classe implements Sujet {

    //-----------Attributs-----------
    private String nomClasse;
    private TreeMap<String, Integer> constructeurs; // constructeurs possede a c
    private TreeMap<String, Integer> attributs;// attributs contient à chaque fois le nom de la methode et sa portée.
    private TreeMap<String, Integer> methodes;
    private ArrayList<Observateur> observateurs;
    private Classe superClass;
    private Class classeCourante;

    private boolean abstractOrNot;
    private int modifierDeLaClasse;

    //-----------Constructeur-----------
    public Classe(String path) throws IOException {
        //compilation du fichier .java (argument path du constructeur)
        File fichierSource = new File(path);
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null);
        fileManager.getJavaFileObjectsFromFiles(Arrays.asList(fichierSource));
        fileManager.close();

        this.nomClasse = path.split("/")[path.split("/").length-1].split("\\.")[0];
        // lecture du fichier .class créé à partir du chemin donné en paramètre
        ByteArrayClassLoader byteArrayClassLoader = new ByteArrayClassLoader();
        this.classeCourante = byteArrayClassLoader.findClass(nomClasse, path.substring(0,path.length()-5)+".class");
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

    private Method[] getMethodes() {
        return classeCourante.getDeclaredMethods();
    }

    private Field[] getAttributs() {
        return classeCourante.getDeclaredFields();
    }

    private Constructor[] getConstructeurs() {
        return classeCourante.getDeclaredConstructors();
    }

}
