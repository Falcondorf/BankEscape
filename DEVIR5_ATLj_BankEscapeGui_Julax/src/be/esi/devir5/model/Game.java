package be.esi.devir5.model;

import be.esi.devir5.exception.BankEscapeDbException;
import be.esi.devir5.exception.BankEscapeException;
import java.util.Observable;
import static java.lang.Thread.sleep;

/**
 * Classe d'un niveau de jeu.
 *
 * @author pikirby45
 */
public class Game extends Observable {

    private Maze maze;

    /**
     * Constructeur de niveau à partir d'un numéro qui sera lu pour construire
     * le niveau
     *
     * @param nameFirstLevel Le nom du niveau
     * @throws be.esi.devir5.exception.BankEscapeException si le maze rencontre 
     * une erreur lors de sa création
     */
    public Game(String nameFirstLevel) throws BankEscapeException {
        maze = new Maze(nameFirstLevel);

    }

    public Game(int sizeRow, int sizeColumn) {
        maze = new Maze(sizeRow, sizeColumn);
    }

    /**
     * Accède au labyrinth
     *
     * @return Le labyrinth
     */
    public Maze getMaze() {
        return maze;
    }
    
    public void startThreadEnnemy(){
        Thread te = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!isLost() && !endLevel()) {
            try {
                autoMoveEnemy();
               
                sleep(1000);
            } catch (InterruptedException ex) {
                System.out.println("error2");
            }
        }
            }
        });
        
        te.setDaemon(true);
        te.start();
    }

    /**
     * Vérifie qu'un niveau s'est terminé ou non
     *
     * @return Vrai lorsqu'un joueur s'est fait voir par un vigile ou s'il a
     * rapporté le butin
     */
    public boolean isLost() {
        return this.maze.isCaught();
    }
    
    public void enGame(){
        maze.catchPlayer();
    }

    public boolean endLevel() {
        if (this.maze.isEscaped()) {
            try {
                return true;
            } catch (Exception ex) {
                System.out.println("ERROR: " + ex);
            }
        }
        return false;

    }

    public void addEnemy(Direction dir, int row, int column) {
        maze.addEnemy(dir, row, column);
    }

    public void addEntry(int row, int column) {
        maze.addEntry(row, column);
        setChanged();
        notifyObservers();
    }

    public void addExit(int row, int column) {
        maze.addExit(row, column);
        setChanged();
        notifyObservers();
    }

    public void addFloor(int row, int column) {
        maze.addFloor(row, column);
        setChanged();
        notifyObservers();
    }

    public void addPlayer(int row, int column) {
        maze.addPlayer(row, column);
    }

    public void addVault(int row, int column) {
        maze.addVault(row, column);
        setChanged();
        notifyObservers();
    }

    public void addWall(int row, int column) {
        maze.addWall(row, column);
        setChanged();
        notifyObservers();
    }

    public void putEnemy(int row, int column) {
        maze.putEnemy(row, column);
        setChanged();
        notifyObservers();
    }

    public void putDrill(int row, int column) {
        maze.putDrill(row, column);
        setChanged();
        notifyObservers();
    }

    public void putKey(int row, int column) {
        maze.putKey(row, column);
        setChanged();
        notifyObservers();
    }

    public void putPlayer(int row, int column) {
        maze.putPlayer(row, column);
        setChanged();
        notifyObservers();

    }

    public boolean isValid() throws BankEscapeException {
        return maze.isValid();
    }

    public void modifSize(int modifWidth, int modifHeight) throws BankEscapeException {
        maze = new Maze(maze, modifWidth, modifHeight);
    }

    public int getWidth() {
        return maze.getWidth();
    }

    public int getHeight() {
        return maze.getHeight();
    }

    public Square[][] getSquares() {
        return maze.getSquares();
    }

    public Square getSquare(int a, int b) {
        return maze.getSquares()[a][b];
    }

    public Direction getEnemyDir(int i, int j) throws BankEscapeException {
        return maze.getEnemyDir(i, j);
    }

    public Direction getPlayerDir(int i, int j) throws BankEscapeException {
        return maze.getPlayerDir(i, j);
    }

    public void movePlayer(Direction dir) {
        maze.movePlayer(dir);
        setChanged();
        notifyObservers();
    }

    public void autoMoveEnemy() {
        maze.autoMoveEnemy();
        setChanged();
        notifyObservers();
    }

    public void goGhost() {
        maze.goGhost();
    }

    public void giveAllToPlayer() {
        maze.giveAllToPlayer();
    }

    public void killAllEnemies() {
        maze.killAllEnemies();
    }

    public String getNextLevelName() {
        return maze.getNextLevelName();
    }
    
    public boolean playerHasMoney(){
        return maze.hasMoney();
    }
    
    public boolean playerHasDrill(){
        return maze.hasDrill();
    }
    
    public boolean playerHasKey(){
        return maze.hasKey();
    }
    
    public void writeDB (String levelName, String nextLevel) throws BankEscapeDbException{
        maze.writeLevelDb(levelName, nextLevel);
    }
}
