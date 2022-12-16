package mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.Themes;

import javafx.scene.paint.Color;

public class Theme {

    //-----------Attributs-----------

    private String nom;
    private Color colorRepCourant;
    private Color colorDiag;
    private Color colorNav;

    //-----------Constructeurs-----------

    public Theme(String nom, Color colorRepCourant, Color colorDiag, Color colorNav) {
        this.nom = nom;
        this.colorRepCourant = colorRepCourant;
        this.colorDiag = colorDiag;
        this.colorNav = colorNav;
    }

    //-----------Getters-----------

    public String getNom() {
        return nom;
    }

    public Color getColorRepCourant() {
        return colorRepCourant;
    }

    public Color getColorDiag() {
        return colorDiag;
    }

    public Color getColorNav() {
        return colorNav;
    }
}
