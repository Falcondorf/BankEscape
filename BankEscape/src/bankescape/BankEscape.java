
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
    //   Position pos = new Position(0, 0);
      //Movable player = new Movable(new Position(0, 0));
       maze.addPlayer(0, 0);      
       maze.move(Direction.UP);      
       System.out.println(maze.getPlayer().getRow());
       
    }
    
}
