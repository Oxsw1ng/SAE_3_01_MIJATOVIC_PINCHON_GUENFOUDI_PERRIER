package mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.Vue;

import javafx.animation.PauseTransition;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.interfacesETabstract.Observateur;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model.Classe;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model.Modele;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

public class VueDiagramme extends Pane implements Observateur {

    private Modele modele;

    private HashMap<Classe,VueClasse> table;

    public VueDiagramme(Modele modele){
        this.modele = modele;
        table = new HashMap<Classe,VueClasse>();
    }

    @Override
    public void actualiser() {
        // mise a jour du thème
        this.setBackground(new Background(new BackgroundFill(modele.getTheme().getColorFond2(), null, null)));

        // mise a jour des données
        table.clear();
        this.getChildren().clear();
        for (Classe c:modele.getClasses()) {
            VueClasse vue = new VueClasse(c);
            table.put(c,vue);
            this.getChildren().add(vue);
        }
        modele.changerGrapheCourant(this);

//        for (Classe c:table.keySet()) {
//            //ajout des dépendance selon les attributs
//            for (String attr:c.getAttributs()){
//                String[] t = attr.split(" ");
//                Classe retour = lienExistant(t[3]);
//                if (retour != null){
//                    flecheAgreg(c,retour);
//                }
//            }
//            //ajout des dépendance selon les interfaces
//            for (Class inter:c.getInterfaces()){
//                String[] nomComplet = c.getSuperClass().getName().split("\\.");
//                String nom = nomComplet[nomComplet.length -1];
//                Classe retour = lienExistant(nom);
//                if (retour != null){
//                    flecheAgreg(c,retour);
//                }
//            }
//            //ajout des dépendance selon l'hérédité
//            String[] nomComplet = c.getSuperClass().getName().split("\\.");
//            String nom = nomComplet[nomComplet.length -1];
//            Classe retour = lienExistant(nom);
//            if (retour != null){
//                flecheHeredite(c,retour);
//            }
//        }
    }

    public static boolean pointAppartientSegment(Point C, Point A, Point B, int cote) {
        int[] vecteurAB = new int[] {(int) (B.getX()-A.getX()), (int) (B.getY()-A.getY())};
        int[] vecteurAC = new int[] {(int) (C.getX()-A.getX()), (int) (C.getY()-A.getY())};
        System.out.println();
        System.out.println(A);
        System.out.println(B);
        System.out.println(C);
        System.out.println();
        boolean appartient;

        if (vecteurAB[0] * vecteurAC[1] == vecteurAB[1] * vecteurAC[0]) {
            int prodScalaireABAC = vecteurAB[0] * vecteurAC[0] + vecteurAB[1] * vecteurAC[1];
            int prodScalaireABAB = vecteurAB[0] * vecteurAB[0] + vecteurAB[1] * vecteurAB[1];

            if (0 < prodScalaireABAC && prodScalaireABAC < prodScalaireABAB) {
                appartient = false;
                switch (cote) {
                    case 0:
                        if (A.getX() <= C.getX() && C.getX() <= B.getX()) {
                            appartient = true;
                        }
                        break;
                    case 1:
                        if (A.getY() <= C.getY() && C.getY() <= B.getY()) {
                            appartient = true;
                        }
                        break;
                    case 2:
                        if (B.getX() <= C.getX() && C.getX() <= A.getX()) {
                            appartient = true;
                        }
                        break;
                    case 3:
                        if (B.getY() <= C.getY() && C.getY() <= A.getY()) {
                            appartient = true;
                        }
                        break;
                }
            } else {
                appartient = false;
            }
        } else {
            appartient = false;
        }
        return appartient;
    }


    private Point pointBordClasse(Classe clsSource, Classe clsTarget) {
        VueClasse source = this.table.get(clsSource);
        VueClasse target = this.table.get(clsTarget);

        Point pointA = new Point((int) Math.round(source.getLayoutX()), (int) Math.round(source.getLayoutY()));
        Point pointB = new Point((int) Math.round(source.getLayoutX()+source.getLargeurClasse()), (int) Math.round(source.getLayoutY()));
        Point pointC = new Point((int) Math.round(source.getLayoutX()+source.getLargeurClasse()), (int) Math.round(source.getLayoutY()+source.getHauteurClasse()));
        Point pointD = new Point((int) Math.round(source.getLayoutX()), (int) Math.round(source.getLayoutY()+source.getHauteurClasse()));

        Point bord = null;

        Point[] pointsSource = new Point[]{pointA, pointB, pointC, pointD};

        Point centreSource = new Point((int) Math.round(source.getLayoutX()+source.getLargeurClasse()/2), (int) Math.round(source.getLayoutY()+source.getHauteurClasse()/2));
        Point centreTarget = new Point((int) Math.round(target.getLayoutX()+target.getLargeurClasse()/2), (int) Math.round(target.getLayoutY()+target.getHauteurClasse()/2));

        // Calcul de la pente et de l'ordonnée à l'origine de la droite reliant les deux classes
        double m1;
        if (centreTarget.getX() - centreSource.getX() == 0) {
            m1 = 1000000000;
        } else {
            m1 = (centreTarget.getY() - centreSource.getY()) / (centreTarget.getX() - centreSource.getX());
        }
        double p1 = centreSource.getY() - m1 * centreSource.getX();
        this.getChildren().add(new Line(centreSource.getX(), centreSource.getY(),centreTarget.getX(),centreTarget.getY()));

        for (int i =0; i<4; i++) {
            Point extremite1, extremite2;
            if (i==3) {
                extremite1 = pointsSource[i];
                extremite2 = pointsSource[0];
            } else {
                extremite1 = pointsSource[i];
                extremite2 = pointsSource[i+1];
            }

            this.getChildren().add(new Line(extremite1.getX(),extremite1.getY(),extremite2.getX(),extremite2.getY()));

            double m2;
            if (extremite2.getX() - extremite1.getX() == 0) {
                m2 = 1000000000;
            } else {
                m2 = (extremite2.getY() - extremite1.getY()) / (extremite2.getX() - extremite1.getX());
            }
            double p2 = extremite1.getY() - m2 * extremite1.getX();

            // Si les pentes des deux droites sont différentes, elles s'intersectent en un point unique
            if (m1 != m2) {
                double x = Math.abs(Math.round((p2 - p1) / (m2 - m1)));
                double y = Math.abs(Math.round(m1 * x + p1));
                Point I = new Point((int) x, (int) y);

                if (pointAppartientSegment(I,extremite1,extremite2,i)) {
                    this.getChildren().add(new Line(I.getX(),I.getY(),I.getX()+10, I.getY()+10));
                    System.out.println(I);
                    bord = I;
                }
            }
        }
        return bord;
    }

    public void flecheAgreg(Classe source, Classe target){
        PauseTransition pauseTransition = new PauseTransition(new Duration(3000.0));
        pauseTransition.setOnFinished(e -> this.pointBordClasse(source,target));//this.getChildren().add(new Line(pointBordClasse(source,target).getX(),pointBordClasse(source,target).getY(),pointBordClasse(target,source).getX(), pointBordClasse(target,source).getY())));
        pauseTransition.play();

//        int[] depart = pointBordClasse(source,target);
//        int[] arrive = pointBordClasse(target, source);
//        this.getChildren().add(new Line(depart[0],depart[1], arrive[0], arrive[1]));


    }

//    public void flecheHeredite(Classe source, Classe target){
//        int[] depart = pointBordClasse(source,target);
//        int[] arrive = pointBordClasse(target, source);
//        this.getChildren().add(new Line(depart[0],depart[1], arrive[0], arrive[1]));
//
//    }
//    public void flecheImplements(Classe source, Classe target){
//        int[] depart = pointBordClasse(source,target);
//        int[] arrive = pointBordClasse(target, source);
//        this.getChildren().add(new Line(depart[0],depart[1], arrive[0], arrive[1]));
//
//    }

    /**
     * methode qui prend un nom en parametre et verifie si une classe existe au nom la
     * @param nom
     * @return
     */
    private Classe lienExistant(String nom){
        Classe retour = null;
        Iterator it = table.keySet().iterator();
        while (it.hasNext() && retour == null){
            Classe current = (Classe) it.next();
            if (current.getNomClasse().trim().equalsIgnoreCase(nom))
                retour=current;
        }
        return retour;
    }

}
