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

    public ControllerDeplacerClasse(Classe model, VueClasse vue) {
        this.model = model;
        this.vue = vue;
    }


    @Override
    public void handle(MouseEvent mouseEvent) {

        Bounds bounds = vue.getBoundsInLocal();
        double x = mouseEvent.getX();
        double y = mouseEvent.getY();


        double x2 = x + vue.getLayoutX();
        double y2 = y + vue.getLayoutY();
        if(mouseEvent.isDragDetect()){
            modifierCursor(Cursor.CLOSED_HAND);
        }
        else {
            modifierCursor(Cursor.OPEN_HAND);
        }


        if (x2>0 && x2<(((VueDiagramme)vue.getParent()).getWidth()-vue.getWidth())){
            model.setCoordonnesX(x2);
            vue.setLayoutX(x2);
        }
        if (y2>0 && y2<(((VueDiagramme)vue.getParent()).getHeight()-vue.getHeight())){
            model.setCoordonnesY(y2);
            vue.setLayoutY(y2);
        }
        //double[] pos =clampNodeToParentBounds();


    }
private void modifierCursor(Cursor hand){
        vue.setCursor(hand);

        }
    /*
    double[] clampNodeToParentBounds() {
        Bounds parentBounds = vue.getParent().getLayoutBounds();
        Bounds nodeBounds = vue.getLayoutBounds();

        double newLayoutX = clamp(vue.getLayoutX(), 0, parentBounds.getWidth() - nodeBounds.getWidth());
        double newLayoutY = clamp(vue.getLayoutY(), 0, parentBounds.getHeight() - nodeBounds.getHeight());

        return new double[]{newLayoutX,newLayoutY};
    }

    double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(max, value));
    }

     */
}


