
package bankescape;

/**
 *
 * @author jackd
 */
public class Square {
    private String type;
    private boolean hasPlayer;

    public boolean isHasPlayer() {
        return hasPlayer;
    }

    public void setHasPlayer(boolean hasPlayer) {
        this.hasPlayer = hasPlayer;
    }

    public boolean isHasEnemy() {
        return hasEnemy;
    }

    public void setHasEnemy(boolean hasEnemy) {
        this.hasEnemy = hasEnemy;
    }

    public boolean isHasDrill() {
        return hasDrill;
    }

    public void setHasDrill(boolean hasDrill) {
        this.hasDrill = hasDrill;
    }

    public boolean isHasKey() {
        return hasKey;
    }

    public void setHasKey(boolean hasKey) {
        this.hasKey = hasKey;
    }
    private boolean hasEnemy;
    private boolean hasDrill;
    private boolean hasKey;

    public Square() { //Floor, Wall, Vault, Entry, Exit. ==> Un vault 
        this.type = "Floor";        //et une exit fermée ne sont pas reachable.
        this.hasPlayer = false;
        this.hasDrill = false;
        this.hasEnemy = false;
        this.hasKey = false;
    }
    
    public boolean isPickable(){ //A quoi sert cette méthode???
        return this.hasDrill || this.hasKey;
    }
    
    public boolean isReachable(){ //A compléter avec le vault et l'exit fermé
        return !type.equals("Wall");
    }
    
    public String getType(){
        return this.type;
    }
}
