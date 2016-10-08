
package bankescape;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author G40963
 */
public class Level {
    Maze maze;
    
    public Level(int numLevel) throws IOException {
        // trouver moyen de construire le maze avant la lecture de fichier 
        readLevel(numLevel);        
    }

    public Maze getMaze() {
        return maze;
    }
    
    private void readLevel(int levelNum) throws IOException {
        //Scanner lect = new Scanner(new File("/src/LoadRunner/Niveaux/Level"
        //       + levelNum + ".txt"));
        //lect.useDelimiter("\n");
        int row = 0, col = 0;
        int r;
        String nb1 = "",nb2 = "";
        FileReader in = new FileReader(new File("Niveau"
                + levelNum + ".txt"));
        BufferedReader br = new BufferedReader(in);
        while ((r = br.read()) != '/') {
            nb1+=(char)r;          
        }
         while ((r = br.read()) != '\r') {
            nb2+=(char)r;
        }

        this.maze = new Maze(Integer.parseInt(nb1),Integer.parseInt(nb2));
        
        
        while ((r = br.read()) != -1) {
            char ch = (char) r;
            switch (ch) {
                case ' ':
                    
                    break;
                case 'P':
                   // new Player(new Position(row, col))
                    this.maze.addPlayer(row,col);
                    this.maze.putPlayer(row, col);
                    break;
                case 'W':
                    this.maze.addWall(row, col);
                    break;
                case 'I':
                    this.maze.addEntry(row, col);
                    break;
                case 'E':
                    this.maze.addEnemy(Direction.UP, row, col);
                    this.maze.putEnemy(row, col);
                    break;
                case 'V':
                    this.maze.addVault(row, col);
                    break;
                case 'D':
                    this.maze.putDrill(row, col);
                    break;
                case 'K':
                    this.maze.putKey(row, col);
                    break;
                case 'S':
                    this.maze.addExit(row, col);
                    break;
                case '\n':
                    row++;
                    col = 0;
                    break;
            }
            col++;
        }
    }
    
    
    
}
