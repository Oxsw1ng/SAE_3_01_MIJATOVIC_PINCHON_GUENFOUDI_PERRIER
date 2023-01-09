package mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.Vue;

import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.controller.ControllerDragExterieur;
import javafx.scene.shape.Polygon;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.interfacesETabstract.Observateur;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model.Classe;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model.Modele;


import java.awt.*;
import java.util.*;

public class VueDiagramme extends Pane implements Observateur {
    public static final int FLECHE_HEREDITE = 1;
    public static final int FLECHE_IMPLEMENTATION = 2;
    public static final int FLECHE_AGREGATION = 3;

    private Modele modele;

    private HashMap<Classe,VueClasse> table;

    public VueDiagramme(Modele modele){
        this.modele = modele;
        table = new HashMap<Classe,VueClasse>();
        this.setOnDragOver(this::handleDragOverEvent);
        this.setOnDragDropped(new ControllerDragExterieur(modele));
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

    public static boolean pointAppartientSegment(Point C, Point A, Point B) {
        int[] vecteurAB = new int[] {(int) (B.getX()-A.getX()), (int) (B.getY()-A.getY())};
        int[] vecteurAC = new int[] {(int) (C.getX()-A.getX()), (int) (C.getY()-A.getY())};
        boolean appartient = false;

        // on vérifie que les vecteurs sont colinéaires
        if (vecteurAB[0] * vecteurAC[1] == vecteurAB[1] * vecteurAC[0]) {
            int prodScalaireABAC = vecteurAB[0] * vecteurAC[0] + vecteurAB[1] * vecteurAC[1];
            int prodScalaireABAB = vecteurAB[0] * vecteurAB[0] + vecteurAB[1] * vecteurAB[1];

            if (0 <= prodScalaireABAC && prodScalaireABAC <= prodScalaireABAB) {
                appartient = true;
            }
        }
        return appartient;
    }


    public Point pointBordClasse(Classe clsSource, Classe clsTarget) {
        VueClasse source = this.table.get(clsSource);
        VueClasse target = this.table.get(clsTarget);

        Point pointA = new Point((int) Math.round(source.getLayoutX()), (int) Math.round(source.getLayoutY()));
        Point pointB = new Point((int) Math.round(source.getLayoutX()+source.getLargeurClasse()), (int) Math.round(source.getLayoutY()));
        Point pointC = new Point((int) Math.round(source.getLayoutX()+source.getLargeurClasse()), (int) Math.round(source.getLayoutY()+source.getHauteurClasse()));
        Point pointD = new Point((int) Math.round(source.getLayoutX()), (int) Math.round(source.getLayoutY()+source.getHauteurClasse()));

        Point[] pointsSource = new Point[]{pointA, pointB, pointC, pointD};

        Point centreSource = new Point((int) Math.round(source.getLayoutX()+source.getLargeurClasse()/2), (int) Math.round(source.getLayoutY()+source.getHauteurClasse()/2));
        Point centreTarget = new Point((int) Math.round(target.getLayoutX()+target.getLargeurClasse()/2), (int) Math.round(target.getLayoutY()+target.getHauteurClasse()/2));

        // Calcul de la pente et de l'ordonnée à l'origine de la droite reliant les deux classes
        double m1;
        if (centreTarget.getX() - centreSource.getX() == 0) {
            m1 = 10000000000L;
        } else {
            m1 = (centreTarget.getY() - centreSource.getY()) / (centreTarget.getX() - centreSource.getX());
        }
        double p1 = centreSource.getY() - m1 * centreSource.getX();

        ArrayList<Point> pointsAppartenantAuxSegments = new ArrayList<>();

        for (int i =0; i<4; i++) {
            Point extremite1, extremite2;
            if (i==3) {
                extremite1 = pointsSource[i];
                extremite2 = pointsSource[0];
            } else {
                extremite1 = pointsSource[i];
                extremite2 = pointsSource[i+1];
            }

            // Calcul de la pente et de l'ordonnée à l'origine de la droite reliant deux sommets du rectangle qui modélise la classe
            double m2;
            if (extremite2.getX() - extremite1.getX() == 0) {
                m2 = 10000000000L;
            } else {
                m2 = (extremite2.getY() - extremite1.getY()) / (extremite2.getX() - extremite1.getX());
            }
            double p2 = extremite1.getY() - m2 * extremite1.getX();

            // Si les pentes des deux droites sont différentes, elles s'intersectent en un point unique
            if (m1 != m2) {
                int x = (int) Math.abs(Math.round((p2 - p1) / (m2 - m1)));
                // on cherche des valeurs à plus ou moins 2 à cause des arrondis, qui peuvent générer des erreurs
                if ((extremite1.getX() == extremite2.getX()) && x-2 <= extremite1.getX() && extremite1.getX() >= x+2) {
                    x = (int) extremite1.getX();
                }
                int y;
                if (centreSource.getX() == centreTarget.getX()) {
                    y = (int) Math.abs(Math.round(m1 * x - p1));
                } else {
                    y = (int) Math.abs(Math.round(m1 * x + p1));
                }
                //pareil ici
                if ((extremite1.getY() == extremite2.getY()) && y-2 <= extremite1.getY() && extremite1.getY() >= y+2) {
                    y = (int) extremite1.getY();
                }
                Point I = new Point(x, y);

//                // ligne servant à des tests uniquement afin de visualiser les points d'intersections
//                this.getChildren().add(new Line(I.getX(), I.getY(), I.getX()+10, I.getY()+10));

                if (pointAppartientSegment(I,extremite1,extremite2)) {
                    pointsAppartenantAuxSegments.add(I);
                }
            }
        }

        // on cherche le point le plus proche du point central de la classe source :
        double distanceMin = Double.MAX_VALUE;
        Point bord = null;
        for (Point p : pointsAppartenantAuxSegments) {
            if (p.distance(centreTarget) < distanceMin) {
                distanceMin = p.distance(centreTarget);
                bord = p;
            }
        }
        return bord;
    }

    public void fleche(Point source, Point target, int type, String texteMilieuFleche){
        if (source != null || target != null) {
            // Création de la ligne reliant les deux points
            Line ligne = new Line(source.getX(), source.getY(), target.getX(), target.getY());
            ligne.setStroke(this.modele.getTheme().getBordureEtBtnImportant());
            ligne.setStrokeWidth(2);

            this.getChildren().add(ligne);

            // Calcul de l'angle de la ligne
            double angle = Math.atan2(target.getY() - source.getY(), target.getX() - source.getX());

            // Calcul des coordonnées des points de la flèche
            double x3 = target.getX() - 25 * Math.cos(angle - Math.PI / 6);
            double y3 = target.getY() - 25 * Math.sin(angle - Math.PI / 6);
            double x4 = target.getX() - 25 * Math.cos(angle + Math.PI / 6);
            double y4 = target.getY() - 25 * Math.sin(angle + Math.PI / 6);

            switch (type) {
                case FLECHE_IMPLEMENTATION:
                    ArrayList dash = new ArrayList<>();
                    dash.add(20.0);
                    dash.add(20.0);
                    ligne.getStrokeDashArray().addAll(dash);
                case FLECHE_HEREDITE:
                    // Création de la flèche
                    Polygon fleche = new Polygon();
                    fleche.getPoints().addAll(
                            target.getX(), target.getY(),
                            x3, y3,
                            x4, y4
                    );
                    fleche.setFill(this.modele.getTheme().getFondNavEtArbo());
                    fleche.setStroke(this.modele.getTheme().getBordureEtBtnImportant());
                    fleche.setStrokeWidth(2);
                    this.getChildren().add(fleche);
                    break;
                case FLECHE_AGREGATION:

                    Line ligne2 = new Line(x3, y3, target.getX(), target.getY());
                    ligne2.setStroke(this.modele.getTheme().getBordureEtBtnImportant());
                    ligne2.setStrokeWidth(2);

                    Line ligne3 = new Line(x4, y4, target.getX(), target.getY());
                    ligne3.setStroke(this.modele.getTheme().getBordureEtBtnImportant());
                    ligne3.setStrokeWidth(2);

                    javafx.scene.control.Label labelAgregation = new javafx.scene.control.Label(texteMilieuFleche);
                    double x = (ligne.getStartX() + ligne.getEndX()) / 2;
                    double y = (ligne.getStartY() + ligne.getEndY()) / 2;
                    labelAgregation.setLayoutX(x - labelAgregation.getWidth() / 2);
                    labelAgregation.setLayoutY(y - labelAgregation.getHeight() / 2);

                    this.getChildren().addAll(ligne2, ligne3, labelAgregation);
                    break;
            }
        }
    }

    public void flecheImplements(Point source, Point target){
        // Création de la ligne reliant les deux points
        Line ligne = new Line(source.getX(), source.getY(), target.getX(), target.getY());
        ligne.setStroke(this.modele.getTheme().getBordureEtBtnImportant());
        ligne.setStrokeWidth(2);


        // Calcul de l'angle de la ligne
        double angle = Math.atan2(target.getY() - source.getY(), target.getX() - source.getX());

        // Calcul des coordonnées des points de la flèche
        double x3 = target.getX() - 20 * Math.cos(angle - Math.PI / 6);
        double y3 = target.getY() - 20 * Math.sin(angle - Math.PI / 6);
        double x4 = target.getX() - 20 * Math.cos(angle + Math.PI / 6);
        double y4 = target.getY() - 20 * Math.sin(angle + Math.PI / 6);

        // Création de la flèche
        Polygon fleche = new Polygon();
        fleche.getPoints().addAll(
                target.getX(), target.getY(),
                x3, y3,
                x4, y4
        );
        fleche.setFill(this.modele.getTheme().getFondNavEtArbo());
        fleche.setStroke(this.modele.getTheme().getBordureEtBtnImportant());
        fleche.setStrokeWidth(2);
        this.getChildren().addAll(ligne,fleche);
    }

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
    private void handleDragOverEvent(DragEvent event) {
        Dragboard db = event.getDragboard();
        if (db.hasFiles()) {
            event.acceptTransferModes(TransferMode.COPY);
        } else {
            event.consume();
        }
    }

}
