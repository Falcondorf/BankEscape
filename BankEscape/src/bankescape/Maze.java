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

    
    public void movePlayer(Direction dir) {
        if (moveAutorised(dir)) {
            this.removePlayer(player.getRow(), player.getColumn());
            switch (dir) {
                case UP:
                    displacePlayer(-1, 0, Direction.UP);
                    pickSomething();
                    break;
                case DOWN:
                    displacePlayer(1, 0, Direction.DOWN);
                    pickSomething();
                    break;
                case LEFT:
                    displacePlayer(0, -1, Direction.LEFT);
                    pickSomething();
                    break;
                case RIGHT:
                    displacePlayer(0, 1, Direction.RIGHT);
                    pickSomething();
                    break;
            }
        }

    }

    private void pickSomething() {
        if(maze[player.getRow()][player.getColumn()].hasKey()){
            player.setHasKey(true);
        }
        if(maze[player.getRow()][player.getColumn()].hasDrill()){
            player.setHasDrill(true);
        }
    }

    public void autoMoveEnemy() {
        for (Enemy e : enemyList) {
            removeEnemy(e.getRow(), e.getColumn());
            try {
                switch (movementAnalysis(e)) {
                    case UP:
                        e.move(Direction.UP);
                        this.putEnemy(e.getRow() - 1, e.getColumn());
                        break;
                    case DOWN:
                        e.move(Direction.DOWN);
                        this.putEnemy(e.getRow() + 1, e.getColumn());
                        break;
                    case LEFT:
                        e.move(Direction.LEFT);
                        this.putEnemy(e.getRow(), e.getColumn() - 1);
                        break;
                    case RIGHT:
                        e.move(Direction.RIGHT);
                        this.putEnemy(e.getRow(), e.getColumn() + 1);
                        break;
                }
            } catch (Exception ex) {
                System.out.println(ex);
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
                return player.getRow() != 0
                        && maze[player.getRow() - 1][player.getColumn()].isReachable(player.hasKey(), player.hasDrill());
            case DOWN:
                return player.getRow() != maze.length - 1
                        && maze[player.getRow() + 1][player.getColumn()].isReachable(player.hasKey(), player.hasDrill());
            case LEFT:
                return player.getColumn() != 0
                        && maze[player.getRow()][player.getColumn() - 1].isReachable(player.hasKey(), player.hasDrill());
            case RIGHT:
                return player.getColumn() != maze[0].length - 1
                        && maze[player.getRow()][player.getColumn() + 1].isReachable(player.hasKey(), player.hasDrill());
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

    public void putDrill(int row, int column) {
        maze[row][column].setHasDrill();
    }

    public void putKey(int row, int column) {
        maze[row][column].setHasKey();
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

    private Direction movementAnalysis(Enemy e) throws Exception {
        //evalue si mur devant la direction suivie .V.

        e.resetPossibleDirection();
        if (maze[e.getRow() - 1][e.getColumn()].isReachable(false,false)) {
            e.addPossibleDirection(Direction.UP);
        }
        if (maze[e.getRow() + 1][e.getColumn()].isReachable(false,false)) {
            e.addPossibleDirection(Direction.DOWN);
        }
        if (maze[e.getRow()][e.getColumn() + 1].isReachable(false,false)) {
            e.addPossibleDirection(Direction.RIGHT);
        }
        if (maze[e.getRow()][e.getColumn() - 1].isReachable(false,false)) {
            e.addPossibleDirection(Direction.LEFT);
        }

        //si + de 2 direction possible random
        if (e.getNumberDirections() > 2) {
            return e.randDir();
        } else { //soit un mur soit couloir
            switch (e.getDirection()) {
                case UP:
                    return movementDecision(e, Direction.UP, -1, 0);
                case DOWN:
                    return movementDecision(e, Direction.DOWN, 1, 0);
                case LEFT:
                    return movementDecision(e, Direction.LEFT, 0, -1);
                case RIGHT:
                    return movementDecision(e, Direction.RIGHT, 0, 1);
                default:
                    throw new Exception();
            }
        }

    }

    private Direction movementDecision(Enemy e, Direction dir, int decalRow, int decalCol) {
        if (!maze[e.getRow() + decalRow][e.getColumn() + decalCol].isReachable(false,false)) {
            //choisir une direction possible au hasard
            return e.randDir();
        } else { //continuer dans meme direction
            return dir;
        }
    }
    
    public boolean playerOnVault(){
        return this.maze[this.player.getRow()][this.player.getColumn()].getType().equals("vault");
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                switch (maze[i][j].getType()) {
                    case "wall":
                        str += "W";
                        break;
                    case "exit":
                        str += "S";
                        break;
                    case "floor":
                        if (maze[i][j].hasDrill()) {
                            str += "D";
                        } else if (maze[i][j].hasEnemy()) {
                            str += "E";
                        } else if (maze[i][j].hasKey()) {
                            str += "K";
                        } else if (maze[i][j].hasPlayer()) {
                            str += "P";
                        } else {
                            str += " ";
                        }
                        break;
                    case "entry":
                        if (maze[i][j].hasPlayer()) {
                            str += "P"; 
                        }else{
                            str += "I";
                        }                       
                        break;
                    case "vault":
                        str += "V";
                        break;

                }

            }
            str += "\n";
        }
        return str;
    }
}
