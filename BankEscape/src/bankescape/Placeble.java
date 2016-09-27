
package bankescape;

/**
 *
 * @author jackd
 */
public class Placeble extends Square{
    private boolean isWall;
    private boolean isVault;
    private boolean isFloor;
    private boolean isEntry;
    private boolean isExit;
    
    public boolean getIsWall (){
        return this.isWall;
    }
    
    public boolean getIsVault (){
        return this.isVault;
    }
    
    public boolean getIsFloor(){
        return this.isFloor;
    }
    
    public boolean getIsEntry(){
        return this.isEntry;
    }
    
    public boolean getIsExit(){
        return this.isExit;
    }
}
