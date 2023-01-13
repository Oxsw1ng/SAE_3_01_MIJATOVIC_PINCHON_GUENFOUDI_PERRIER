package mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.controller;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.Vue.VueClasse;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.Vue.VueDiagramme;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model.Classe;


public class ControllerDragClickPourClasse implements EventHandler<MouseEvent> {
    private VueClasse vue;
    private Classe model;

    private double xDuClique;
    private double yDuClique;
    private boolean redimensionnementActif = false;
    private DragController typeController;

    public ControllerDragClickPourClasse(Classe model, VueClasse vue) {
        this.model = model;
        this.vue = vue;
    }


    public void handle(MouseEvent mouseEvent) {

        /*
        if (redimensionnementActif) {
            typeController = new ResizeController();
        } else {
            typeController = new MoveController();
        }
        */
        typeController = new MoveController();
        typeController.handleDrag(mouseEvent);
        ((VueDiagramme) vue.getParent()).deplacerFleche();
    }

    public double getxDuClique() {
        return xDuClique;
    }

    public void setxDuClique(double xDuClique) {
        this.xDuClique = xDuClique;
    }

    public double getyDuClique() {
        return yDuClique;
    }

    public void setyDuClique(double yDuClique) {
        this.yDuClique = yDuClique;
    }

    public boolean estRedimensionnementActif() {
        return redimensionnementActif;
    }

    public void setRedimensionnementActif(boolean redimensionnementActif) {
        this.redimensionnementActif = redimensionnementActif;
    }

    class ResizeController implements DragController {
        public void handleDrag(MouseEvent mouseEvent) {
            double mouseEventX = mouseEvent.getX();
            double mouseEventY = mouseEvent.getY();
            double diffX = mouseEventX - xDuClique;
            double diffY = mouseEventY - yDuClique;
            double initialWidth = vue.getWidth();
            double initialHeight = vue.getHeight();
            double width = initialWidth, height = initialHeight;

            if (vue.estSurBordInferieurEtDroit(mouseEvent)) {
                width = vue.getWidth() + diffX;
                height = vue.getHeight() + diffY;
                xDuClique = mouseEventX;
                yDuClique = mouseEventY;
            } else if (vue.estSurBordGaucheEtHaut(mouseEvent)) {
                width = Math.max(initialWidth - diffX, 0);
                height = Math.max(initialHeight - diffY, 0);
                vue.setLayoutX(vue.getLayoutX() + diffX);
                vue.setLayoutY(vue.getLayoutY() + diffY);
            }
            vue.setPrefWidth(width);
            vue.setPrefHeight(height);


        }
    }

    class MoveController implements DragController {

        @Override
        public void handleDrag(MouseEvent mouseEvent) {
            double mouseEventX = mouseEvent.getX();
            double mouseEventY = mouseEvent.getY();
            double cooClickDansVueX = mouseEventX + vue.getLayoutX() - xDuClique;
            double cooClickDansVueY = mouseEventY + vue.getLayoutY() - yDuClique;
            if (cooClickDansVueX > 0 && cooClickDansVueX < (((VueDiagramme) vue.getParent()).getWidth() - vue.getWidth())) {


                model.setCoordonnesX(cooClickDansVueX);
                vue.setLayoutX(cooClickDansVueX);

            }
            if (cooClickDansVueY > 0 && cooClickDansVueY < (((VueDiagramme) vue.getParent()).getHeight() - vue.getHeight())) {

                model.setCoordonnesY(cooClickDansVueY);
                vue.setLayoutY(cooClickDansVueY);
            }
        }
    }
}





