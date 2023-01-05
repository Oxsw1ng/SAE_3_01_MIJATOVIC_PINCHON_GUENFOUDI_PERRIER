package mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.controller;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.Vue.VueClasse;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model.Classe;

public class ControllerDeplacerClasse implements EventHandler<MouseEvent> {
    private VueClasse vue;
    private Classe model;

    public ControllerDeplacerClasse(Classe model, VueClasse vue) {
        this.model = model;
        this.vue = vue;
    }


    @Override
    public void handle(MouseEvent mouseEvent) {

        double x = mouseEvent.getX();
        double y = mouseEvent.getY();

        model.setCoordinates(x + vue.getLayoutX(), y + vue.getLayoutY());
        clampNodeToParentBounds(vue);
        if(mouseEvent.isDragDetect()){
            modifierCursor(Cursor.CLOSED_HAND);
        }
        else {
            modifierCursor(Cursor.OPEN_HAND);
        }

    }
    private void modifierCursor(Cursor hand){
        vue.setCursor(hand);

    }
    private void clampNodeToParentBounds(Node node) {
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


