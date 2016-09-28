
package bankescape;

import java.util.ArrayList;

/**
 *
 * @author jackd
 */
public class Enemy extends Movable{
    
    private boolean inAlert =false;
    private Direction direction;
    private ArrayList<Direction> possibleDirection;

    public Enemy(Direction direction,Position pos) {
        super(pos);
        possibleDirection= new ArrayList<>();
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

    public ArrayList<Direction> getPossibleDirection() {
        return possibleDirection;
    }

    public void addPossibleDirection(Direction dir) {
        this.possibleDirection.add(dir);
    }
    
    public void resetPossibleDirection(){
        possibleDirection.clear();
    }
}
