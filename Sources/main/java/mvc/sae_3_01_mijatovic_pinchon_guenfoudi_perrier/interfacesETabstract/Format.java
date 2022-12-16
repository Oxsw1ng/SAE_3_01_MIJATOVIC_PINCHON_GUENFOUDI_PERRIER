package mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.interfacesETabstract;

import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model.Modele;

public interface Format {

    //méthode d'export
    public void exporter(Modele m, String chemin);

}
