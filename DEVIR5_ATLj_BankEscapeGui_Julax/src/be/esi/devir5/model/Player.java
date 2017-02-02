package be.esi.devir5.model;

/**
 * Classe Player, represente le joueur avec ses caractéristiques.
 *
 * @author pikirby45
 */
public class Player extends Movable {

    private boolean hasMoney = false;
    private boolean hasDrill = false;
    private boolean hasKey = false;
    private boolean isCaught = false;

    /**
     * Constructeur de player, construit le joueur à une position donnée.
     *
     * @param pos la Position du joueur dans le niveau.
     * @param dir la direction du joueur, par ou il se déplace
     */
    public Player(Position pos,Direction dir) {
        super(pos,dir);

    }

    /**
     * Indique si le joueur possède la clé de la sortie
     *
     * @return true si il possède la clé
     */
    public boolean hasKey() {
        return hasKey;
    }

    /**
     * Donne la clé au joueur ou lui la retire
     *
     * @param hasKey est à true si on donne la clé au joueur, false si on la lui
     * retire
     */
    public void setHasKey(boolean hasKey) {
        this.hasKey = hasKey;
    }

    /**
     * Indique si le joueur à volé l'argent dans la voute.
     *
     * @return true si il a pris l'argent de la voute
     */
    public boolean hasMoney() {
        return hasMoney;
    }

    /**
     * Donne l'argent de la voute au joueur.
     *
     * @param hasMoney est à true si on donne l'argent au joueur.
     */
    public void setHasMoney(boolean hasMoney) {
        this.hasMoney = hasMoney;
    }

    /**
     * Indique si le joueur possède la perseuse
     *
     * @return true s'il possède la perseuse
     */
    public boolean hasDrill() {
        return hasDrill;
    }

    /**
     * Donne la foreuse au joueur.
     *
     * @param hasDrill true si on lui donne la foreuse.
     */
    public void setHasDrill(boolean hasDrill) {
        this.hasDrill = hasDrill;
    }

    /**
     * Indique si le joueur à été attrapé par les gardes.
     *
     * @return true si le joueur à été attrapé.
     */
    public boolean isCaught() {
        return isCaught;
    }

    /**
     * Attrape le joueur.
     *
     */
    public void setIsCaught() {
        this.isCaught = true;
    }

}
