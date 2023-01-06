package mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.Format;

import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.interfacesETabstract.Format;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model.Modele;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Locale;

public abstract class FormatImage implements Format {

    @Override
    public void exporter(Modele m, String chemin) {
        String nomExtension = this.getNom().toLowerCase(Locale.ROOT);
        File file = new File(chemin + "." + nomExtension);

        // Prendre une capture d'écran du pane
        SnapshotParameters params = new SnapshotParameters();
                    Image image = m.getGrapheCourant().snapshot(params, null);

        // Écrire l'image dans un fichier
        try {
            BufferedImage bImage = new BufferedImage((int) image.getWidth(), (int) image.getHeight(), BufferedImage.TYPE_INT_RGB);
            PixelReader pixelReader = image.getPixelReader();
            for (int y = 0; y < image.getHeight(); y++) {
                for (int x = 0; x < image.getWidth(); x++) {
                    bImage.setRGB(x, y, pixelReader.getArgb(x, y));
                }
            }
            ImageIO.write(bImage, nomExtension, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public abstract String getNom();


}
