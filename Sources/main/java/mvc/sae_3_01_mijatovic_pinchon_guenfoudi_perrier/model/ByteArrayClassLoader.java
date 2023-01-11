package mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model;

import javafx.scene.control.Alert;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.util.ArrayList;

public class ByteArrayClassLoader extends ClassLoader {

    private String classpath;

    public ByteArrayClassLoader() {
        super(ByteArrayClassLoader.class.getClassLoader());
    }

    public ByteArrayClassLoader(String classPath) {
        super(ByteArrayClassLoader.class.getClassLoader());
        this.classpath = classPath;
    }

    public Class<?> findClass(String name, String pathClass) {
//        File file = new File(pathClass);
//        byte[] ba = new byte[0];
//        try {
//            ba = Files.readAllBytes(file.toPath());
//        } catch (IOException e) {
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Erreur");
//            alert.setContentText("Vous venez d'essayer de charger un fichier .class dans le projet qui utilise une classe non primitive ("+name+"). "+name+" n'a pas été encore ajouté au projet ou n'est pas dans le même dossier que le fichier que vous vouliez essayer de charger.");
//            alert.setWidth(300);
//            // Afficher la boîte de dialogue
//            alert.showAndWait();
//        }

        File file = new File(pathClass);
        byte[] bytes = new byte[(int) file.length()];
        try {
            FileInputStream fis = new FileInputStream(file);
            fis.read(bytes);
            fis.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return defineClass(null, bytes, 0, bytes.length);
    }

    public Class<?> defineClassFromFile(String fileName) throws Exception {
        File file = new File(fileName);
        byte[] bytes = new byte[(int) file.length()];
        FileInputStream fis = new FileInputStream(file);
        fis.read(bytes);
        fis.close();
        return defineClass(null, bytes, 0, bytes.length);
    }
    @Override
    public Class<?> loadClass(String className) throws ClassNotFoundException {
        try {
            ArrayList<URL> urlList = new ArrayList<URL>();
            File libDir = new File(classpath);
            for (String fileName : libDir.list()) {
                if (fileName.endsWith(".jar")) {
                    urlList.add(new File(libDir + "/" + fileName).toURI().toURL());
                }
            }
            URLClassLoader urlClassLoader = new URLClassLoader(urlList.toArray(new URL[0]), this.getParent());
            return urlClassLoader.loadClass(className);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.loadClass(className);
    }

}
