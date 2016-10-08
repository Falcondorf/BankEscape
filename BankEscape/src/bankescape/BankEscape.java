
package bankescape;
import java.io.IOException;
import threads.*;

/**
 *
 * @author jackd
 */
public class BankEscape {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {  
        try {
            //       Maze maze = new Maze(5,5);
            Level l = new Level(1);
            ThreadPlayer tp = new ThreadPlayer(l);
        } catch (IOException ex) {
            System.out.println(ex.getCause());
        }
       
//    //   Position pos = new Position(0, 0);
//      //Movable player = new Movable(new Position(0, 0));
//       maze.addPlayer(0, 0);   
//       System.out.println("ligne joueur : "+maze.getPlayer().getRow());
//       maze.movePlayer(Direction.UP);      //ne px pas sortir du plateau
//       System.out.println("ligne joueur : "+maze.getPlayer().getRow());
//       maze.putDrill(1, 0);
//       //maze.player.setHasDrill(true);
//       maze.addVault(2, 0); //ajout d un mur en dessou
//       maze.movePlayer(Direction.DOWN);
//       maze.movePlayer(Direction.DOWN);
//       System.out.println("ligne joueur : "+maze.getPlayer().getRow());
//        try {
//            Level leLevel = new Level(1);
//            System.out.println(leLevel.getMaze().toString());
//      //      leLevel.getMaze().autoMoveEnemy();
//       //     leLevel.getMaze().movePlayer(Direction.DOWN);
//            
//            System.out.println(leLevel.getMaze().toString());
//        } catch (IOException ex) {
//            System.out.println("wrong file");
//        }
        

           
    }
    
}
