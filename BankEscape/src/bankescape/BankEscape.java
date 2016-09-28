
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
       System.out.println("ligne joueur : "+maze.getPlayer().getRow());
       maze.movePlayer(Direction.UP);      //ne px pas sortir du plateau
       System.out.println("ligne joueur : "+maze.getPlayer().getRow());
       maze.addWall(1, 0); //ajout d un mur en dessou
       maze.movePlayer(Direction.DOWN);
       System.out.println("ligne joueur : "+maze.getPlayer().getRow());
    }
    
}
