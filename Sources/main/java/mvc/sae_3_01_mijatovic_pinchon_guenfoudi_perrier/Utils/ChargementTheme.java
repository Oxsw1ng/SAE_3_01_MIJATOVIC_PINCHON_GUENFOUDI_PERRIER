package mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.Utils;

import javafx.scene.paint.Color;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.Themes.Theme;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model.Modele;

import java.io.*;
import java.util.ArrayList;

public class ChargementTheme {

    /*
     * methode pour recuperer les themes creer par l'utilisateur
     */
    public static ArrayList<Theme> chargerTheme(){
        ArrayList<Theme> arThemes = new ArrayList<Theme>();
        try {
            BufferedReader br = lectureDuFichier();
            String line;
            while ((line = br.readLine()) != null) {
                String[] t = line.split("_");
                boolean pasPresent = true;
                for (Theme t1 :arThemes) {
                    if (t1.getNom().trim().equalsIgnoreCase(t[0].trim()))
                        pasPresent = false;
                }
                if (t.length==12 && pasPresent){
                    Theme theme = new Theme(t[0],Color.web(t[1]),Color.web(t[2]),Color.web(t[3]),Color.web(t[4]),Color.web(t[5]),Color.web(t[6]),Color.web(t[7]),Color.web(t[8]),Color.web(t[9]),Color.web(t[10]),Color.web(t[11]));
                    arThemes.add(theme);
                }
            }
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("Le fichier de chargement des ressources n'a pas ete trouve");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return arThemes;
    }

    /*
     * methode qui rend l'indice du theme selectionne par l utilisateur comme theme courant
     */
    public static int chargerNumeroDuTheme(Modele m){
        int i = 0;
        try {
            BufferedReader br = lectureDuFichier();
            i = Integer.parseInt(br.readLine());
            br.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NumberFormatException e){
            changerNumTheme(m,0);
        }
        return i;
    }

    /*
     * methode qui rend un bufferedReader afin de parcourir le fichier de themes
     */
    private static BufferedReader lectureDuFichier() throws FileNotFoundException {
        return new BufferedReader(new FileReader("Sources/main/resources/mvc/sae_3_01_mijatovic_pinchon_guenfoudi_perrier/style.Sdmov"));
    }
    /*
     * methode qui rend un bufferedReader afin de parcourir le fichier de themes
     */
    private static BufferedWriter ecritureDuFichier() throws IOException {
        return new BufferedWriter(new FileWriter("Sources/main/resources/mvc/sae_3_01_mijatovic_pinchon_guenfoudi_perrier/style.Sdmov"));
    }

    /*
     * methode d ajout d'un theme à ecrire dans le fichier
     */
    public static void ecrireTheme(Theme t, Modele modele, int numeroDuTheme) {
        ArrayList<Theme> arTheme = chargerTheme();
        arTheme.add(t);
        try {
            BufferedWriter bw = ecritureDuFichier();
            bw.write(numeroDuTheme);
            bw.newLine();
            for (Theme theme:arTheme) {
                String[] s = {theme.getNom(), modele.couleurHexa(theme.getBordureEtBtnImportant()),modele.couleurHexa(theme.getBoutonClassiques()),modele.couleurHexa(theme.getFondDiagEtTextField()),modele.couleurHexa(theme.getFondQuandClicke()),modele.couleurHexa(theme.getFondClasse()),modele.couleurHexa(theme.getTopClasse()),modele.couleurHexa(theme.getFondNavEtArbo()), modele.couleurHexa(theme.getColorText()), modele.couleurHexa(theme.getColorTextTitle()),modele.couleurHexa(theme.getColorFond2()), modele.couleurHexa(theme.getCouleurTxtCls())};
                bw.write(String.join("_",s));
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /*
     * methode de modification du theme courant voulu
     */
    public static void changerNumTheme(Modele modele, int numeroDuTheme) {
        ArrayList<Theme> arTheme = new ArrayList<>(modele.getListThemes());
        arTheme.remove(0);
        arTheme.remove(0);
        try {
            BufferedWriter bw = ecritureDuFichier();
            bw.write(numeroDuTheme+"");
            bw.newLine();
            for (Theme theme:arTheme) {
                String[] s = {theme.getNom(), modele.couleurHexa(theme.getBordureEtBtnImportant()),modele.couleurHexa(theme.getBoutonClassiques()),modele.couleurHexa(theme.getFondDiagEtTextField()),modele.couleurHexa(theme.getFondQuandClicke()),modele.couleurHexa(theme.getFondClasse()),modele.couleurHexa(theme.getTopClasse()),modele.couleurHexa(theme.getFondNavEtArbo()), modele.couleurHexa(theme.getColorText()), modele.couleurHexa(theme.getColorTextTitle()),modele.couleurHexa(theme.getColorFond2()), modele.couleurHexa(theme.getCouleurTxtCls())};
                bw.write(String.join("_",s));
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
