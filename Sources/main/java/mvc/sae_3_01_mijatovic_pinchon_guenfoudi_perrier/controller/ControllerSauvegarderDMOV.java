package mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.Format.DMOV;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model.Modele;

public class ControllerSauvegarderDMOV implements EventHandler<ActionEvent> {
    private Modele modele;

    public ControllerSauvegarderDMOV(Modele modele) {
        this.modele = modele;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        DMOV dmov = new DMOV();
        System.out.println(this.modele.getCheminDMOV().split("\\.")[this.modele.getCheminDMOV().split("\\.").length-2]);
        dmov.exporter(this.modele, this.modele.getCheminDMOV().split("\\.")[this.modele.getCheminDMOV().split("\\.").length-2]);
    }
}
