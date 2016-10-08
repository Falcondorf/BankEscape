
package bankescape;

/**
 *
 * @author jackd
 */
public class Player extends Movable {
    private boolean hasMoney =false;
    private boolean hasDrill = false;
    private boolean hasKey = false;
    private boolean isCaught = false;

    public Player( Position pos) {
        super(pos);
            
    }

    public boolean hasKey() {
        return hasKey;
    }

    public void setHasKey(boolean hasKey) {
        this.hasKey = hasKey;
    }

  
    public boolean hasMoney() {
        return hasMoney;
    }

   
    public void setHasMoney(boolean hasMoney) {
        this.hasMoney = hasMoney;
    }

    public boolean hasDrill() {
        return hasDrill;
    }

    public void setHasDrill(boolean hasDrill) {
        this.hasDrill = hasDrill;
    }

    public boolean isCaught() {
        return isCaught;
    }

    public void setIsCaught(boolean isCaught) {
        this.isCaught = isCaught;
    }
        
    
}
