package be.esi.devir5.model;

/**
 * Enumeration des directions de mouvements
 *
 * @author pikirby45
 */
public enum Direction {

    UP("haut", -1, 0),
    DOWN("bas", 1, 0),
    LEFT("gauche", 0, -1),
    RIGHT("droite", 0, 1);

    private String name;
    private int decalX;
    private int decalY;

    /**
     * Constructeur de l'énumération
     *
     * @param s le texte représentant un attribut de l'énumération
     */
    Direction(String s, int dX, int dY) {
        this.name = s;
        this.decalX = dX;
        this.decalY = dY;
    }

    /**
     * Méthode permettant l'affichage d'un attribut de cette énumération
     *
     * @return L'attribut textuellement exprimé
     */
    @Override
    public String toString() {
        return this.name;
    }

    public int getDecalX() {
        return decalX;
    }

    public int getDecalY() {
        return decalY;
    }

}
