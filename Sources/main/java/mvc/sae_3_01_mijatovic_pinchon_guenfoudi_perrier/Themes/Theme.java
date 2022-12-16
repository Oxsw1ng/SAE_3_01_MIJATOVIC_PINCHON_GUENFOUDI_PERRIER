package mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.Themes;

import javafx.scene.paint.Color;

public class Theme {

    //-----------Attributs-----------

    private String nom;
    private Color bordureEtBtnImportant; //4.1
    private Color boutonClassiques; //4
    private Color fondDiagEtTextField; //1
    private Color fondClasse; //3
    private Color topClasse; //3.1
    private Color fondNavEtArbo; //2

    //-----------Constructeurs-----------



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
}
