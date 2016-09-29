package bankescape;

import java.util.ArrayList;
import java.util.Random;

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

    public void movePlayer(Direction dir) {
        if (moveAutorised(dir)) {
            this.removePlayer(player.getRow(), player.getColumn());
            switch (dir) {
                case UP:
                    displacePlayer(-1, 0, Direction.UP);
                    break;
                case DOWN:
                    displacePlayer(1, 0, Direction.DOWN);
                    break;
                case LEFT:
                    displacePlayer(0, -1, Direction.LEFT);
                    break;
                case RIGHT:
                    displacePlayer(0, 1, Direction.RIGHT);
                    break;
            }
        }

    }

    public void autoMoveEnemy(Direction dir) {
        for (Enemy e : enemyList) {
            removeEnemy(e.getRow(), e.getColumn());
            switch (movementAnalysis(e)) {

            }
        }
    }

    private void displacePlayer(int decRow, int decColumn, Direction dir) {
        player.move(dir);     //deplace objet joueur
        this.putPlayer(player.getRow() + decRow, player.getColumn() + decColumn);  //place booléen joueur
    }

    private boolean moveAutorised(Direction dir) { //condition de sortie + est ce qu il y a un mur ?
        switch (dir) {
            case UP:
                return player.getRow() != 0 && maze[player.getRow() - 1][player.getColumn()].isReachable();
            case DOWN:
                return player.getRow() != maze.length - 1 && maze[player.getRow() + 1][player.getColumn()].isReachable();
            case LEFT:
                return player.getColumn() != 0 && maze[player.getRow()][player.getColumn() - 1].isReachable();
            case RIGHT:
                return player.getColumn() != maze[0].length - 1 && maze[player.getRow()][player.getColumn() + 1].isReachable();
            default:
                return false;
        }
    }

    public void addWall(int row, int column) {
        maze[row][column].setWall();
    }

    public void addVault(int row, int column) {
        maze[row][column].setVault();
    }

    public void addEntry(int row, int column) {
        maze[row][column].setEntry();
    }

    public void addExit(int row, int column) {
        maze[row][column].setExit();
    }

    public void addPlayer(int row, int column) {   //1 seule fois a l'initialisation du jeu
        Player play = new Player(new Position(row, column));
        maze[row][column].setHasPlayer();
        player = play;
    }

    public void putPlayer(int row, int column) {  //a chaque fois qu on bouge
        maze[row][column].setHasPlayer();
    }

    public void removePlayer(int row, int column) {
        maze[row][column].removePlayer();
    }

    public void addEnemy(Direction dir, int row, int column) {   //x fois a l'initialisation du jeu
        maze[row][column].setHasEnemy();
        enemyList.add(new Enemy(dir, new Position(row, column)));
    }

    public void putEnemy(int row, int column) {
        maze[row][column].setHasEnemy();
    }

    public void removeEnemy(int row, int column) {
        maze[row][column].removeEnemy();
    }

    public void removeElement(int row, int column) {
        maze[row][column].setFloor();
    }

    public boolean isValid() {
        return false; //todo
    }

    public Player getPlayer() {
        return player;
    }

    private Direction movementAnalysis(Enemy e) {
        //evalue si mur devant la direction suivie .V.

        e.resetPossibleDirection();
        if (maze[e.getRow() - 1][e.getColumn()].isReachable()) {
            e.addPossibleDirection(Direction.UP);
        }
        if (maze[e.getRow() + 1][e.getColumn()].isReachable()) {
            e.addPossibleDirection(Direction.DOWN);
        }
        if (maze[e.getRow()][e.getColumn() + 1].isReachable()) {
            e.addPossibleDirection(Direction.RIGHT);
        }
        if (maze[e.getRow()][e.getColumn() - 1].isReachable()) {
            e.addPossibleDirection(Direction.LEFT);
        }

        //si + de 2 direction possible random
        if (e.getPossibleDirection().size() > 2) {
            Random rand = new Random();
            int iRand = rand.nextInt();
        } else { //soit un mur soit couloir
            switch (e.getDirection()) {
                case UP:
                    if (!maze[e.getRow() - 1][e.getColumn()].isReachable()) {
                        //choisir une direction possible au hasard
                    } else { //continuer dans meme direction
                        return Direction.UP;
                    }
                    break;
                case DOWN:
                     if (!maze[e.getRow() + 1][e.getColumn()].isReachable()) {
                        //choisir une direction possible au hasard
                    } else { //continuer dans meme direction
                        return Direction.DOWN;
                    }
                    break;
                case LEFT:
                     if (!maze[e.getRow()][e.getColumn()-1].isReachable()) {
                        //choisir une direction possible au hasard
                    } else { //continuer dans meme direction
                        return Direction.LEFT;
                    }
                    break;
                case RIGHT:
                     if (!maze[e.getRow()][e.getColumn()+1].isReachable()) {
                        //choisir une direction possible au hasard
                    } else { //continuer dans meme direction
                        return Direction.RIGHT;
                    }
                    break;

            }
        }

    }

}
