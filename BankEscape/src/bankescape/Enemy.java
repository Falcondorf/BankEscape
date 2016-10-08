
package bankescape;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author jackd
 */
public class Enemy extends Movable{
    
    private boolean inAlert =false;
    private Direction direction;
    private List<Direction> possibleDirection;

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

    public int getNumberDirections() {
        return possibleDirection.size();
    }

    public void addPossibleDirection(Direction dir) {
        this.possibleDirection.add(dir);
    }
    
    public void resetPossibleDirection(){
        possibleDirection.clear();
    }
    
    public Direction randDir(){
        Random rand = new Random();
        int i = rand.nextInt(getNumberDirections());
        return possibleDirection.get(i);
    }
}
