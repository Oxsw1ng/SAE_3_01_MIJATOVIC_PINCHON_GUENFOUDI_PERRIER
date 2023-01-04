import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Grep {
    /** Nom du fichier a ouvrir. */
    private String nomFichier;

    /** Mot recherche. */
    private String motCherche;

    /** BufferedReader correspondant a nomfich. */
    private BufferedReader src;


    /**
     * Constructeur de la classe Grep. Initialise certains attributs,
     * ouvre le fichier et crée le BufferedReader correspondant
     *
     * @param nom nom du fichier a lire
     * @param mot mot recherche dans le fichier
     * @exception FileNotFoundException fichier non trouvé
     *
     **/
    public Grep(String nom, String mot) throws FileNotFoundException {
        this.nomFichier = nom;
        this.motCherche = mot;
        FileReader fr = new FileReader(this.nomFichier);
        this.src = new BufferedReader(fr);
    }

    public String[][] methodeBidon(String[][] str) {
        System.out.println("oui oui");
        String[][] a = {{"er","er"},{"er","er"}};
        return a;
    }


    /**
     * methode traiterFichier qui traite le fichier
     * - lit les lignes une par une et les numerote
     * - si le mot est présent dans la ligne en cours
     *      - affiche le fichier traité
     *      - affiche le numero de ligne
     *      - affiche le caractère ou se trouve le mot
     *      - affiche la ligne elle-meme
     *  @exception IOException erreur de lecture
     */
    public void traiterFichier() throws IOException {
        String l = this.src.readLine();
        int index;
        int ligne = 0;

        while (true) {
            index = l.indexOf(this.motCherche);
            if (index != -1) {
                System.out.println(this.nomFichier+" : ("+ligne+","+index+") "+l);
                break;
            }
            ligne++;
            l = this.src.readLine();
        }
    }

    /**
     * la methode close ferme le BufferedReader correspondant
     * au fichier.
     **/
    public void close() {
        try {
            this.src.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /*****************************************************
     * Gestion du lancement / partie Main
     * Methodes statiques
     *****************************************************/

    /**
     * la methode statique lireFichier
     * - cree un objet Grep à partir d'un nom de fichier et du mot recherché
     * - utilise cet objet Grep pour parcourir le fichier et réaliser les impressions demandées
     * - ne pas oublier de fermer le flux
     * Cette méthode doit signaler les noms de fichier inexistants
     *
     * @param nom nom du fichier à analyser
     * @param mot mot recherche
     * @exception IOException exception liée aux entrées/sorties
     */
    public static void lireFichier(String nom, String mot) {
        try {
            Grep grep = new Grep(nom, mot);
            grep.traiterFichier();
            grep.close();
        } catch (FileNotFoundException e) {
            System.out.println("**ERREUR** fichier '"+nom+"' inexistant");
        } catch (IOException e) {
            System.out.println("**ERREUR** mot '"+mot+"' inexistant dans le fichier : "+nom);
        }
    }



    /**
     * Methode statique main.
     * - analyse la ligne de commande (variable args)
     * - appelle la methode lireFichier sur chacun des noms de fichiers fournis en argument.
     *
     * @param args ligne de commandes
     */
    public static void main(String[] args) {
        try {
            for (int i = 1; i < args.length; i++) {
                lireFichier(args[i], args[0]);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("pas le bon nombre d'arguments");
        }
    }
}
