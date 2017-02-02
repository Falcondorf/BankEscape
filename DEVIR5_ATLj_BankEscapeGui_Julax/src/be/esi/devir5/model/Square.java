package be.esi.devir5.model;

/**
 * Classe représentant une case du plateau de jeu.
 *
 * @author jackd
 */
public class Square {

    private String type;
    private boolean isLighted;
    private boolean hasPlayer;
    private boolean hasEnemy;
    private boolean hasDrill;
    private boolean hasKey;

    /**
     * construit la case.
     */
    public Square() { //Floor, Wall, Vault, Entry, Exit. ==> Un vault 
        this.type = "floor";        //et une exit fermée ne sont pas reachable.
        resetContent();
    }

    private void resetContent() {
        this.hasPlayer = false;
        this.hasDrill = false;
        this.hasEnemy = false;
        this.hasKey = false;
    }

    public boolean isLighted() {
        return isLighted;
    }

    public void setIsLighted(boolean isLighted) {
        this.isLighted = isLighted;
    }

    /**
     * Indique si la case contient.
     *
     * @return true si la case contient un joueur.
     */
    public boolean hasPlayer() {
        return hasPlayer;
    }

    /**
     * Met un joueur dans la case
     */
    public void setHasPlayer() {
        if (type.equals("floor")) {
            this.hasPlayer = true;
        }
    }

    /**
     * Enleve le joueur de la case.
     */
    public void removePlayer() {
        this.hasPlayer = false;
    }

    /**
     * Indique si la case possede un ennemi.
     *
     * @return true si c'est le cas.
     */
    public boolean hasEnemy() {
        return hasEnemy;
    }

    /**
     * Met un ennemi dans la case.
     */
    public void setHasEnemy() {
        if (type.equals("floor")) {
            this.hasEnemy = true;
        }
    }

    /**
     * Enleve l'ennemi de la case
     */
    public void removeEnemy() {
        this.hasEnemy = false;
    }

    /**
     * Indique si la case possede une foreuse.
     *
     * @return true si c'est le cas.
     */
    public boolean hasDrill() {
        return hasDrill;
    }

    public boolean hasVault() {
        return type.equals("vault");
    }

    public boolean hasEntry() {
        return type.equals("entry");
    }

    /**
     * Met une foreuse dans la case.
     */
    public void setHasDrill() {
        if (type.equals("floor")) {
            resetContent();
            this.hasDrill = true;
        }
    }

    /**
     * Enleve la foreuse de la case.
     */
    public void removeDrill() {
        this.hasDrill = false;
    }

    /**
     * Indique si la case possede une clé.
     *
     * @return true si c'est le cas.
     */
    public boolean hasKey() {
        return hasKey;
    }

    /**
     * Met une clé dans la case.
     */
    public void setHasKey() {
        if (type.equals("floor")) {
            resetContent();
            this.hasKey = true;
        }
    }

    /**
     * Enleve la clé de la case.
     */
    public void removeHasKey() {
        this.hasKey = false;
    }

    /**
     * Indique si la case est atteignable.
     *
     * @param hasKey true si le joueur possede la clé de la sortie.
     * @param hasDrill true si le joueur possede la foreuse.
     * @param ghost indique si le joueur triche et peut passer à travers les murs
     * @return true si la case est atteignable.
     */
    public boolean isReachable(boolean hasKey, boolean hasDrill, boolean ghost) {
        return (hasDrill && type.equals("vault"))
                || (hasKey && type.equals("exit"))
                || (type.equals("floor"))
                || (type.equals("entry"))
                || ghost;
    }

    /**
     * Renvoi le type de la case.
     *
     * @return la sorte de case dont il s'agit.
     */
    public String getType() {
        return this.type;
    }

    /**
     * Met le type de la case à : mur.
     */
    public void setWall() {
        type = "wall";
        resetContent();
    }

    /**
     * Met le type de la case à : voute.
     */
    public void setVault() {
        type = "vault";
        resetContent();
    }

    /**
     * Met le type de la case à : sol.
     */
    public void setFloor() {
        type = "floor";
        resetContent();
    }

    /**
     * Met le type de la case à : entrée.
     */
    public void setEntry() {
        type = "entry";
        resetContent();
    }

    /**
     * Met le type de la case à : sortie.
     */
    public void setExit() {
        type = "exit";
        resetContent();
    }

}
