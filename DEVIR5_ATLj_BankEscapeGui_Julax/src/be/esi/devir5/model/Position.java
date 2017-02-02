package be.esi.devir5.model;

/**
 * Classe spécifiant la position d'un Movable dans le jeu.
 *
 * @author jackd
 */
public class Position {

    private int row;
    private int column;

    /**
     * Crée une position, composé d'une ligne et d'une colonne.
     *
     * @param row la ligne.
     * @param column la colonne.
     */
    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * Indique la ligne de la position.
     *
     * @return la valeur de la ligne.
     */
    public int getRow() {
        return row;
    }

    /**
     * Defini la valeur de la ligne d'une position.
     *
     * @param row la ligne de la position.
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * Indique la colonne de la position.
     *
     * @return la valeur de la colonne.
     */
    public int getColumn() {
        return column;
    }

    /**
     * Defini la valeur de la colonne d'une position.
     *
     * @param column la colonne de la position.
     */
    public void setColumn(int column) {
        this.column = column;
    }

}
