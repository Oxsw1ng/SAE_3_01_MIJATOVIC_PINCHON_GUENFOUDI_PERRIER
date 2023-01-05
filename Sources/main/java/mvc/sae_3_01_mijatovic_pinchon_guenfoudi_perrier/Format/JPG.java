package mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.Format;

import javafx.scene.image.WritableImage;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.interfacesETabstract.Format;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model.Modele;

import java.awt.image.RenderedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;

public class JPG implements Format {

    @Override
    public void exporter(Modele m, String chemin) {
        WritableImage wim = new WritableImage(500, 500);
        m.getGrapheCourant().snapshot(null, wim);
        File file = new File(chemin);
        try {
            ImageIO.write((RenderedImage) wim, "jpg", file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getNom() {
        return "JPG";
    }

}
