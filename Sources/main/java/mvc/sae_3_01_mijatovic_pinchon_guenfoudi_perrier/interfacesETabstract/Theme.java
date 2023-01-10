package mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.interfacesETabstract;

import javafx.scene.paint.Color;

public class Theme {

    //-----------Attributs-----------

    private String nom;
    private Color bordureEtBtnImportant; //4.1
    private Color boutonClassiques; //4
    private Color fondDiagEtTextField; //1
    private Color fondClasse; //3
    private Color fondQuandClicke;
    private Color topClasse; //3.1
    private Color fondNavEtArbo; //2
    private Color colorText; //5
    private Color colorTextTitle; //6
    private Color ColorFond2; //7

    //-----------Constructeurs-----------

    public Theme(String nom, Color bordureEtBtnImportant, Color boutonClassiques, Color fondDiagEtTextField,Color fondQuandClicke, Color fondClasse, Color topClasse, Color fondNavEtArbo, Color colorText, Color colorTextTitle, Color colorFond2) {
        this.nom = nom;
        this.bordureEtBtnImportant = bordureEtBtnImportant;
        this.boutonClassiques = boutonClassiques;
        this.fondDiagEtTextField = fondDiagEtTextField;
        this.fondQuandClicke = fondQuandClicke;
        this.fondClasse = fondClasse;
        this.topClasse = topClasse;
        this.fondNavEtArbo = fondNavEtArbo;
        this.colorText = colorText;
        this.colorTextTitle = colorTextTitle;
        this.ColorFond2 = colorFond2;

    }


    //-----------Getters-----------

    public String getNom() {
        return nom;
    }

    public Color getBordureEtBtnImportant() {
        return bordureEtBtnImportant;
    }

    public Color getBoutonClassiques() {
        return boutonClassiques;
    }

    public Color getFondDiagEtTextField() {
        return fondDiagEtTextField;
    }

    public Color getFondClasse() {
        return fondClasse;
    }

    public Color getTopClasse() {
        return topClasse;
    }

    public Color getFondNavEtArbo() {
        return fondNavEtArbo;
    }

    public Color getColorText() {
        return colorText;
    }

    public Color getColorTextTitle() {
        return colorTextTitle;
    }

    public Color getFondQuandClicke() {
        return fondQuandClicke;
    }

    public Color getColorFond2(){return ColorFond2;}

    @Override
    public String toString() {
        return this.nom;
    }
}
