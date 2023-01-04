import java.io.Serializable;

public class Date implements Serializable {
    /** attribut de date **/
    private int jour, mois, an;

    /** constructeur de date */
    public Date(int j, int m, int a){
        this.jour = j;
        this.mois = m;
        this.an = a;
    }

    /** constructeur de date par copie*/
    public Date(Date d){
        this.jour = d.jour;
        this.mois = d.mois;
        this.an = d.an;
    }

    public int getJour() {
        return jour;
    }

    public int getMois() {
        return mois;
    }

    public int getAn() {
        return an;
    }

    public String toString() {
        return this.jour+"/"+this.mois+"/"+this.an;
    }
}
