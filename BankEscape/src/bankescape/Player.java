
package bankescape;

/**
 *
 * @author jackd
 */
public class Player extends Movable {
    private String name;
    private boolean hasMoney =false;
    private boolean hasDrill = false;
    private boolean isCaught = false;

    public Player(String name, Position pos) {
        super(pos);
        this.name = name;
       
    }
        
    
}
