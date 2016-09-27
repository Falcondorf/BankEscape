
package bankescape;

import java.util.ArrayList;

/**
 *
 * @author jackd
 */
public class Maze {
    Square[][] maze;
    Player player;
    ArrayList<Enemy> enemyList; 

    public Maze(int sizeRow, int sizeColumn) {
        this.enemyList = new ArrayList<>();
        maze = new Square[sizeRow][sizeColumn];
        for (int i = 0; i < sizeRow; i++) {
            for (int j = 0; j < sizeColumn; j++) {
                Square s = new Square();
                maze[i][j] = s;
            }
        }
    }
    
    public void move (Direction dir){
        switch (dir){
            case UP:
                player.move(Direction.UP);
                break;
            case DOWN: 
                player.move(Direction.DOWN);
                break;
            case LEFT:
                player.move(Direction.LEFT);
                break;
            case RIGHT: 
                player.move(Direction.RIGHT);
                break;
        }
    }
    
    public boolean isValid(){
        return false; //todo
    }
}
