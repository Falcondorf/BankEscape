
package bankescape;

/**
 *
 * @author jackd
 */
public class BankEscape {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {  
       Maze maze = new Maze(5,5);
       Position pos = new Position(0, 0);
       Movable player = new Movable(pos);
       player.move(Direction.DOWN);
        System.out.println(player.getRow());
       
    }
    
}
