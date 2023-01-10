package mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.Format;

import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.interfacesETabstract.Format;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model.Modele;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class DMOV implements Format, Serializable {
    static final long serialVersionUID = 648175960228010030L;

    @Override
    public void exporter(Modele m, String chemin) {
        try {
            // Ouvrir un flux d'écriture vers un fichier
            FileOutputStream fos = new FileOutputStream(chemin + ".dmov");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            // Écrire l'objet dans le fichier
            oos.writeObject(m);

            // Fermer les flux
            oos.close();
            fos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String getNom() {
        return "DMOV";
    }

}
