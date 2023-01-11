package mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.Format;

import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.interfacesETabstract.Format;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model.Modele;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class UML implements Format {


    public void exporter(Modele modele, String chemin) {
        // Création du fichier à l'emplacement spécifié par `chemin`
        File file = new File(chemin);
        try {
            // Création d'un BufferedWriter pour écrire dans le fichier
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            // Ecriture du texte dans le fichier
            writer.write(modele.convertirPlantUml());
            // Fermeture du BufferedWriter
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getNom() {
        return "UML";
    }

}
