package mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.Vue;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.ChargementRes;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.controller.ControllerChoisirRepertoire;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.interfacesETabstract.Observateur;
import mvc.sae_3_01_mijatovic_pinchon_guenfoudi_perrier.model.Modele;

import java.io.File;
import java.io.FileFilter;
import java.util.HashMap;
import java.util.List;

public class VueRepCourant extends VBox implements Observateur {

    //--------Attributs--------
    private Modele modele;
    private TextField tf;
    private TreeView<File> tv;

    private HashMap<String, TreeItem> listArborescences;

    /* Attribut servant uniquement à vérifier la synchronisation entre le modele et la vue sur le disque à afficher et ainsi éviter de recharger plusieurs fois la même arborescence */
    private String bigParent;

    //--------Constructeur--------

    public VueRepCourant(Modele modele) {
        this.modele = modele;
        this.tf = new TextField();
        this.listArborescences = new HashMap<>();
        this.bigParent = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath() ;
        this.bigParent = this.bigParent.substring(1,this.bigParent.length()-16);
        System.out.println(this.bigParent);
        //this.bigParent = modele.getCheminCourant().split("\\\\")[0] + "\\";
        // creation d un item de substitution
        this.tv = new TreeView<File>(new TreeItem<>(new File("Chargement du répertoire ...")));

        // thread séparé afin de ne pas endommager l'affichage de l interface utilisateur pendant le chargement des arborescences
        Thread thread1 = new Thread(
                () -> {
                    //on commence par générer le repertoire actuel avec un thread
                    TreeItem<File> root = genererTreeItem(new File(bigParent));
                    root.setExpanded(true);
                    //appelle de la methode privé actuExpanded
                    //actuExpanded(root, List.of(modele.getCheminCourant().split("\\\\")));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    //on l ajoute dès qu il existe
                    Platform.runLater(()->{
                        this.tv.setRoot(root);
                    });
                    this.listArborescences.put(root.getValue().getAbsolutePath(),root);

                    //puis on continue le thread en créant préalablement les autres répertoires possibles


                });
        thread1.start();

        //Personnalisation de l'affichage des items
        this.tv.setCellFactory(tv -> new TreeCell<File>() {
            @Override
            protected void updateItem(File item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                    setBackground(new Background(new BackgroundFill(modele.getTheme().getFondNavEtArbo(), null, null)));
                } else {
                    ImageView img;
                    if (item.isDirectory()) {
                        img = new ImageView(ChargementRes.getDossierRes());
                    } else {
                        img = new ImageView(ChargementRes.getFichierRes());
                    }
                    img.setFitWidth(17);
                    img.setPreserveRatio(true);

                    setGraphic(img);
                    String s = item.getName();
                    if (s == "")
                        s = item.getAbsolutePath();
                    setText(s);
                    setTextFill(modele.getTheme().getColorText());
                    setBackground(new Background(new BackgroundFill(modele.getTheme().getFondNavEtArbo(), null, null)));
                }
            }
        });

        //affectation du contrôleur sur le textField
        ControllerChoisirRepertoire control = new ControllerChoisirRepertoire(modele);
        tf.setOnKeyPressed(control);

        //Parametrage de l'affichage de la vue
        this.setBackground(new Background(new BackgroundFill(modele.getTheme().getFondNavEtArbo(), null, null)));
        this.getChildren().addAll(tf, tv);
        this.setBorder(new Border(new BorderStroke(modele.getTheme().getBordureEtBtnImportant(), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0, 2, 2, 2))));
    }

    //--------Méthodes--------

    /*
     * méthode d'actualisation nécessaire au patron mvc
     */
    @Override
    public void actualiser() {

        //parametage de l affichage du textField
        tf.setBackground(new Background(new BackgroundFill(modele.getTheme().getFondDiagEtTextField(), null, null)));
        tf.setBorder(new Border(new BorderStroke(modele.getTheme().getBordureEtBtnImportant(), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0, 0, 1, 0))));
        tf.setStyle("-fx-text-fill: " + modele.couleurHexa(modele.getTheme().getColorText()));
        tv.setBackground(new Background(new BackgroundFill(modele.getTheme().getFondNavEtArbo(), null, null)));
        tv.setPrefHeight(1000);

        tf.setText(modele.getCheminCourant());
        String bigOpposant = modele.getCheminCourant().split("/")[0].split("\\\\")[0] + "\\";
        TreeItem<File> ti;
        // on ne génère une nouvelle arborescence que si on a changé de disque de lecture
        if (!bigOpposant.equals(bigParent)) {
            bigParent = bigOpposant;
            ti = genererTreeItem(new File(bigParent));
            ti.setExpanded(true);
        } else {
            //sinon on récupère l'arborescence actuelle et on ne change que l'ouverture des repertoires
            ti = this.tv.getRoot();
        }
        // on ouvre les repertoires selon le chemin présent dans le textField
        actuExpanded(ti, List.of(modele.getCheminCourant().split("\\\\")));
        this.tv.setRoot(ti);
    }


    /*
     * méthode récursive qui génère une arborescence selon un fichier parent donné
     */
    private TreeItem<File> genererTreeItem(File f) {
        //on crée un nouvel item
        TreeItem<File> treeIt = new TreeItem<File>(f);
        //si c'est un dossier et qu'il n est pas vide
        if (f.isDirectory() && f.listFiles() != null) {
            // évènement d'étendue d'un fichier
            treeIt.addEventHandler(TreeItem.branchExpandedEvent(), new EventHandler<TreeItem.TreeModificationEvent<Object>>() {
                @Override
                public void handle(TreeItem.TreeModificationEvent<Object> objectTreeModificationEvent) {
                    File file = ((File) objectTreeModificationEvent.getTreeItem().getValue());
                    if (modele.getCheminCourant().split("\\\\").length <= file.getAbsolutePath().split("\\\\").length) {
                        modele.setCheminCourant(file.getAbsolutePath());
                        tf.setText(file.getAbsolutePath());
                    }
                }
            });
            // évènement de rétrécissement d'un fichier
            treeIt.addEventHandler(TreeItem.branchCollapsedEvent(), new EventHandler<TreeItem.TreeModificationEvent<Object>>() {
                @Override
                public void handle(TreeItem.TreeModificationEvent<Object> objectTreeModificationEvent) {
                    List<String> list = List.of(modele.getCheminCourant().split("\\\\"));
                    TreeItem treeItem = objectTreeModificationEvent.getTreeItem();
                    if (list.contains(((File) treeItem.getValue()).getName()) && !treeItem.isExpanded()) {
                        modele.setCheminCourant(((File) treeItem.getValue()).getParent());
                        tf.setText(((File) treeItem.getValue()).getParent());
                    }
                }
            });

            //creation d un filtre pour la selection des fichiers
            FileFilter NameFilter = new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    return (pathname.isDirectory() || pathname.getName().endsWith(".java")) && !pathname.isHidden();
                }
            };
            // ajout des sous fichiers à l'item courant
            for (File file : f.listFiles(NameFilter)) {
                treeIt.getChildren().add(genererTreeItem(file));
            }
        }
        //on retourne l'item courant
        return treeIt;
    }

    /*
     * méthode récursive qui étend les répertoires sélectionnés
     */
    private void actuExpanded(TreeItem<File> ti, List<String> list) {
        // etendre si le dossier est dans la liste du textField
        if (!ti.getChildren().isEmpty()) {
            //on vérifie si c est un dossier alors on vérifie les items en dessous
            for (TreeItem treeIt : ti.getChildren()) {
                actuExpanded(treeIt, list);
            }
        }
        // on change l'étendu
        ti.setExpanded(list.contains(ti.getValue().getName()) || modele.getCheminCourant().contains(ti.getValue().getAbsolutePath()));
    }

}
