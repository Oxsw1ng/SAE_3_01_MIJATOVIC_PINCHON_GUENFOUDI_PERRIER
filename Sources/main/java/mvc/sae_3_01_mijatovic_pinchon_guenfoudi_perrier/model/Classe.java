package mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model;

import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.interfacesETabstract.Observateur;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.interfacesETabstract.Sujet;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.TreeMap;

public class Classe implements Sujet {

    //-----------Attributs-----------
    private String nom;
    private TreeMap<String, Integer> constructeurs; // constructeurs possede a c
    private TreeMap<String, Integer> attributs;// attributs contient à chaque fois le nom de la methode et sa portée.
    private TreeMap<String, Integer> methodes;
    private ArrayList<Observateur> observateurs;
    private Classe ClasseMere;
    private boolean abstractOrNot;
    private int modifierDeLaClasse;

    //-----------Constructeur-----------
    public Classe(String nomClasse) {
        Class classe = null;
        try {
            classe = Class.forName(nomClasse);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        this.nom=classe.getName();
        definirMethodes(classe.getMethods());
        definirAttributs(classe.getFields());
        definirConstructeurs(classe.getConstructors());
        this.modifierDeLaClasse=classe.getModifiers();


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
        for (Observateur observateurCourant : this.observateurs
        ) {
            observateurCourant.actualiser();

        }
    }

    private void definirMethodes(Method[] methods) {
        for (Method methodeCourant : methods
        ) {
            String nomMethodeCourant = methodeCourant.getName();
            methodes.put(nomMethodeCourant, methodeCourant.getModifiers());
        }
    }

    private void definirAttributs(Field[] attributsParam) {
        for (Field attributCourant : attributsParam
        ) {
            String nomAttributCourant = attributCourant.getName();
            attributs.put(nomAttributCourant, attributCourant.getModifiers());
        }
    }

    private void definirConstructeurs(Constructor[] constructors) {
        for (Constructor constructorCourant : constructors
        ) {
            String nomConstructeurCourant = constructorCourant.getName();
            constructeurs.put(nomConstructeurCourant, constructorCourant.getModifiers());
        }
    }






    //-----------Méthodes-----------
}
