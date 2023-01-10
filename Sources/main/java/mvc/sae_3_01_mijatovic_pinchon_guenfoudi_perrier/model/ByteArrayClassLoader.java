package mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model;

import javafx.scene.control.Alert;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ByteArrayClassLoader extends ClassLoader {

    public Class<?> findClass(String name, String pathClass) {
        System.out.println(pathClass);
        File file = new File(pathClass);
        byte[] ba = new byte[0];
        try {
            ba = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erreur");
            alert.setContentText("Vous venez d'essayer de charger un fichier .class dans le projet qui utilise une classe non primitive ("+name+"). "+name+" n'a pas été encore ajouté au projet ou n'est pas dans le même dossier que le fichier que vous vouliez essayer de charger.");
            alert.setWidth(300);
            // Afficher la boîte de dialogue
            alert.showAndWait();
        }
        return defineClass(name,ba,0,ba.length);
    }
}
