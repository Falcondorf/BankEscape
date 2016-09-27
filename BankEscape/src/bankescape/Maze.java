
package bankescape;

import java.util.ArrayList;

/**
 *
 * @author jackd
 */
public class Maze {
    Square[][] maze;
    Player player;
    ArrayList enemyList= new ArrayList<Enemy>(); 

    public Maze(int sizeRow, int sizeColumn) {
        maze = new Square[sizeRow][sizeColumn];
        for (int i = 0; i < sizeRow; i++) {
            for (int j = 0; j < sizeColumn; j++) {
                Square s = new Square("floor");
                maze[i][j] = s;
            }
        }
    }
    
    
    
    public boolean isValid(){
        return false; //todo
    }
}
