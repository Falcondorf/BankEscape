package be.esi.devir5.model;

/**
 * Classe mère représentant toute entité déplacable
 *
 * @author pikirby45
 */
public class Movable {

    private Position pos;
    private Direction direction;

    /**
     * Constructeur de la classe. Lui donne une position géographique
     *
     * @param position La position du déplacable
     * @param dir la direction de l'objet déplaçable
     */
    public Movable(Position position, Direction dir) {
        this.pos = position;
        this.direction = dir;
    }

    /**
     * Modifie la position du déplacable en fonction d'une direction
     *
     * @param direction La direction du mouvement
     */
    public void move(Direction direction) {
        this.pos.setRow(this.pos.getRow() + direction.getDecalX());
        this.pos.setColumn(this.pos.getColumn() + direction.getDecalY());
    }

    /**
     * Accède à la ligne où se trouve le déplaçable
     *
     * @return La ligne
     */
    public int getRow() {
        return pos.getRow();
    }

    /**
     * Accède à la colonne où se trouve le déplaçable
     *
     * @return La colonne
     */
    public int getColumn() {
        return pos.getColumn();
    }

        /**
     * Accesseur de la direction vers laquelle regarde et se dirige le vigile
     *
     * @return La direction du vigile
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * Mutateur de la direction du vigile. Modifie ainsi la direction.
     *
     * @param direction La nouvelle direction du vigile
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

}
