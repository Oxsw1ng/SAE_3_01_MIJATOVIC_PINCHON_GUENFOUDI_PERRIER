package mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.Format;

import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.interfacesETabstract.Format;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model.Modele;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class PNG extends FormatImage {

    @Override
    public String getNom() {
        return "PNG";
    }

}
