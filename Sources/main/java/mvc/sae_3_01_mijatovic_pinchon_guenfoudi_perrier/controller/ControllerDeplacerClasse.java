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

        if (redimensionnementActif) {
            // Calculer les différences de position entre le clic de souris et le mouvement de souris
            double diffX = mouseEventX - xDuClique;
            double diffY = mouseEventY - yDuClique;

            // Appliquer les différences de position aux dimensions de la vue
            if(vue.estSurBordInferieurEtDroit(mouseEvent)) {

                System.out.println(diffX+"::");
                vue.setPrefWidth(vue.getWidth() + diffX);
                vue.setPrefHeight(vue.getHeight() + diffY);
            }
            else if(vue.estSurBordGaucheEtHaut(mouseEvent)){

                model.setCoordonnesX(cooClickDansVueX);
                vue.setLayoutX(cooClickDansVueX);
                vue.setPrefWidth(vue.getWidth()-diffX);
                model.setCoordonnesY(cooClickDansVueY);
                vue.setLayoutY(cooClickDansVueY);
                vue.setPrefHeight( vue.getHeight()-diffY);

            }
            //todo: prendre en compte quand c sur un coin, pour faire ça tu met vue.setPrefWidth(vue.getWidth() + diffX);
            //                vue.setPrefHeight(vue.Width() + diffY);

            // Mettre à jour les positions x et y pour la prochaine itération
            xDuClique = mouseEventX;
            yDuClique = mouseEventY;
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


