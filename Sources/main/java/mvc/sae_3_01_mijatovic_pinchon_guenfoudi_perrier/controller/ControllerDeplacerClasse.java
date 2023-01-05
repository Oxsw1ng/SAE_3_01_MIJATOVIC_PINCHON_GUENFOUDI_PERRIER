package mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.controller;

import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.Vue.VueClasse;
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
        System.out.println(bounds.getMaxX());
        System.out.println(x + ":" + y);


        model.setCoordinates(x + vue.getLayoutX(), y + vue.getLayoutY());
        clampNodeToParentBounds(vue);
    }
    void clampNodeToParentBounds(Node node) {
        Bounds parentBounds = node.getParent().getLayoutBounds();
        Bounds nodeBounds = node.getLayoutBounds();

        double newLayoutX = clamp(node.getLayoutX(), 0, parentBounds.getWidth() - nodeBounds.getWidth());
        double newLayoutY = clamp(node.getLayoutY(), 0, parentBounds.getHeight() - nodeBounds.getHeight());

        node.setLayoutX(newLayoutX);
        node.setLayoutY(newLayoutY);
    }

    double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(max, value));
    }
}


