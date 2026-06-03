package hohure.pedalking.artefacts.race.matrice;

public class Cell {

    int numLigne;
    int numColonne;
    boolean empty;
    float x;
    float y;

    public Cell(int numLigne, boolean empty, int numColonne, float x, float y) {
        this.numLigne = numLigne;
        this.empty = empty;
        this.numColonne = numColonne;
        this.x = x;
        this.y = y;
    }

    public int getNumLigne() {
        return numLigne;
    }

    public void setNumLigne(int numLigne) {
        this.numLigne = numLigne;
    }

    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

    public int getNumColonne() {
        return numColonne;
    }

    public void setNumColonne(int numColonne) {
        this.numColonne = numColonne;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
