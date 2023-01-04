package mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ByteArrayClassLoader extends ClassLoader {

    public Class findClass(String name, String pathClass) {
        File file = new File(pathClass);
        byte[] ba;
        try {
            ba = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return defineClass(name,ba,0,ba.length);
    }
}
