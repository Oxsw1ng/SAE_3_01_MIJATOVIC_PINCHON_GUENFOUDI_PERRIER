package mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.controller;

import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.Vue.VueClasse;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.Vue.VueDiagramme;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.main;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model.Classe;

import static mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.main.SIZEX;
import static mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.main.SIZEY;

public class ControllerDeplacerClasse implements EventHandler<MouseEvent> {
    private VueClasse vue;
    private Classe model;

    private double xDuClique;
    private double yDuClique;

    public ControllerDeplacerClasse(Classe model, VueClasse vue) {
        this.model = model;
        this.vue = vue;
    }


    @Override
    public void handle(MouseEvent mouseEvent) {

        double x = mouseEvent.getX();
        double y = mouseEvent.getY();

        double x2 = x + vue.getLayoutX() -xDuClique ;
        double y2 = y + vue.getLayoutY() -yDuClique;

        if (x2>0 && x2<(((VueDiagramme)vue.getParent()).getWidth()-vue.getWidth())){
            model.setCoordonnesX(x2);
            vue.setLayoutX(x2);
        }
        if (y2>0 && y2<(((VueDiagramme)vue.getParent()).getHeight()-vue.getHeight())){
            model.setCoordonnesY(y2);
            vue.setLayoutY(y2);
        }

    }

    public void setxDuClique(double xDuClique) {
        this.xDuClique = xDuClique;
    }

    public void setyDuClique(double yDuClique) {
        this.yDuClique = yDuClique;
    }

}


