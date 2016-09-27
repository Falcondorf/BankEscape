
package bankescape;

/**
 *
 * @author jackd
 */
public class Enemy extends Movable{
    
    private boolean inAlert =false;
    private Direction direction;

    public Enemy(Direction direction,Position pos) {
        super(pos);
        this.direction = direction;
    }

    public boolean isInAlert() {
        return inAlert;
    }

    public void setInAlert(boolean inAlert) {
        this.inAlert = inAlert;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    
}
