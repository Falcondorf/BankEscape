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

    public void move(Direction dir) {
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

    private void displacePlayer(int decRow, int decColumn, Direction dir) {
        player.move(dir);     //deplace objet joueur
        this.putPlayer(player.getRow() + decRow, player.getColumn() + decColumn);  //place booléen joueur
    }

    public boolean moveAutorised(Direction dir) { //condition de sortie + est ce qu il y a un mur ?
        switch (dir) {
            case UP:
                return player.getRow() != 0 && maze[player.getRow() - 1][player.getColumn()].isReachable();
            case DOWN:
                return player.getRow() != maze.length && maze[player.getRow() + 1][player.getColumn()].isReachable();
            case LEFT:
                return player.getColumn() != 0 && maze[player.getRow()][player.getColumn() - 1].isReachable();
            case RIGHT:
                return player.getColumn() != maze[0].length && maze[player.getRow()][player.getColumn() + 1].isReachable();
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

    public void removeElement(int row, int column) {
        maze[row][column].setFloor();
    }

    public boolean isValid() {
        return false; //todo
    }

    public Player getPlayer() {
        return player;
    }

}
