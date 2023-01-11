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



public class ControllerDeplacerClasse implements EventHandler<MouseEvent> {
    private VueClasse vue;
    private Classe model;

    private double xDuClique;
    private double yDuClique;
    private boolean redimensionnementActif = false;

    public ControllerDeplacerClasse(Classe model, VueClasse vue) {
        this.model = model;
        this.vue = vue;
    }


    @Override
    public void handle(MouseEvent mouseEvent) {

        double mouseEventX = mouseEvent.getX();
        double mouseEventY = mouseEvent.getY();
        double cooClickDansVueX = mouseEventX + vue.getLayoutX() -xDuClique ;
        double cooClickDansVueY = mouseEventY + vue.getLayoutY()  -yDuClique;
        double deltaX = mouseEventX - xDuClique;
        double deltaY = mouseEventY - yDuClique;

        if (redimensionnementActif) {
            double initialWidth = vue.getWidth();

            double initialHeight = vue.getHeight();
            double width = initialWidth, height = initialHeight;

            if (vue.estSurBordInferieurEtDroit(mouseEvent)) {
                width=vue.getWidth() + deltaX;
                height=vue.getHeight() + deltaY;
                xDuClique=mouseEventX;
                yDuClique=mouseEventY;
            } else if (vue.estSurBordGaucheEtHaut(mouseEvent)) {

                width = Math.max(initialWidth - deltaX, 0);
                height = Math.max(initialHeight - deltaY, 0);
                vue.setLayoutX(vue.getLayoutX() + deltaX);
                vue.setLayoutY(vue.getLayoutY() + deltaY);
            }
            vue.setPrefWidth(width);
            vue.setPrefHeight(height);

        }

        if(!redimensionnementActif) {
            if (cooClickDansVueX > 0 && cooClickDansVueX < (((VueDiagramme) vue.getParent()).getWidth() - vue.getWidth())) {


                model.setCoordonnesX(cooClickDansVueX);
                vue.setLayoutX(cooClickDansVueX);

            }
            if (cooClickDansVueY > 0 && cooClickDansVueY < (((VueDiagramme) vue.getParent()).getHeight() - vue.getHeight())) {

                model.setCoordonnesY(cooClickDansVueY);
                vue.setLayoutY(cooClickDansVueY);
            }
        }

        ((VueDiagramme)vue.getParent()).deplacerFleche();
    }











    public void setxDuClique(double xDuClique) {
        this.xDuClique = xDuClique;
    }

    public void setyDuClique(double yDuClique) {
        this.yDuClique = yDuClique;
    }

    public void RedimensionnementEstActif() {
        redimensionnementActif=true;
    }

    public void redimensionnementEstInnactif() {
        redimensionnementActif=false;
    }
}


