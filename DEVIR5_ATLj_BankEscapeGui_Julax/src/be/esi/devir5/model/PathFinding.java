package be.esi.devir5.model;

import be.esi.devir5.exception.BankEscapeException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jules Dupont
 */
public class PathFinding {

    private static Square[][] maze;
    private static Position currPos;
    private static Position objPos;
    private static Direction nose;
    private static int cpt;
    private static List<Pair<Position, Integer>> save;
    private static boolean isBlocked;
    private static Pair<Position, Integer> search;

    private static boolean reachEnd() {
        if (currPos.getRow() == objPos.getRow() && currPos.getColumn() == objPos.getColumn()) {
            return true;
        }
        return false;
    }

    private static boolean verifWall() throws BankEscapeException {
        switch (nose) {
            case UP:
                return (currPos.getRow() - 1 > 0) && maze[currPos.getRow() - 1][currPos.getColumn()].getType().equals("floor");
            case DOWN:
                return (currPos.getRow() + 1 < maze[0].length - 1) && maze[currPos.getRow() + 1][currPos.getColumn()].getType().equals("floor");
            case LEFT:
                return (currPos.getColumn() - 1 > 0) && maze[currPos.getRow()][currPos.getColumn() - 1].getType().equals("floor");
            case RIGHT:
                return (currPos.getColumn() + 1 < maze.length - 1) && maze[currPos.getRow()][currPos.getColumn() + 1].getType().equals("floor");
            default:
                throw new BankEscapeException("wrong direction");

        }
    }

    private static void displace() {
        currPos.setRow(currPos.getRow() + nose.getDecalX());
        currPos.setColumn(currPos.getColumn() + nose.getDecalY());
    }

    private static void turn(boolean left) {
        switch (nose) {
            case UP:
                if (left) {
                    nose = Direction.LEFT;
                    cpt -= 1;
                } else {
                    nose = Direction.RIGHT;
                    cpt += 1;
                }
                break;
            case DOWN:
                if (left) {
                    nose = Direction.RIGHT;
                    cpt -= 1;
                } else {
                    nose = Direction.LEFT;
                    cpt += 1;
                }
                break;
            case RIGHT:
                if (left) {
                    nose = Direction.UP;
                    cpt -= 1;
                } else {
                    nose = Direction.DOWN;
                    cpt += 1;
                }
                break;

            case LEFT:
                if (left) {
                    nose = Direction.DOWN;
                    cpt -= 1;
                } else {
                    nose = Direction.UP;
                    cpt += 1;
                }
                break;
        }
    }

    private static boolean verifLeftArm(boolean lookExit, boolean lookVault) throws BankEscapeException {
        switch (nose) {
            case UP:
                return (currPos.getColumn() - 1 > 0) && maze[currPos.getRow()][currPos.getColumn() - 1].isReachable(lookExit, lookVault, false);
            case DOWN:
                return (currPos.getColumn() + 1 < maze[0].length - 1) && maze[currPos.getRow()][currPos.getColumn() + 1].isReachable(lookExit, lookVault, false);
            case LEFT:
                return (currPos.getRow() + 1 > 0) && maze[currPos.getRow() + 1][currPos.getColumn()].isReachable(lookExit, lookVault, false);
            case RIGHT:
                return (currPos.getRow() - 1 < maze.length - 1) && maze[currPos.getRow() - 1][currPos.getColumn()].isReachable(lookExit, lookVault, false);
            default:
                throw new BankEscapeException("wrong direction");

        }
    }

    public static boolean findPath(Position p1, Position p2,
            Square[][] maze, boolean searchVault, boolean searchExit)
            throws BankEscapeException{
        currPos = p1;
        objPos = p2;
        save = new ArrayList<>();
        cpt = 0;
        nose = Direction.UP;
        isBlocked = false;
        PathFinding.maze = new Square[maze.length][maze[0].length];
        
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                PathFinding.maze[i][j] = maze[i][j];
            }            
        }
        
        while (!reachEnd() && !isBlocked && cpt < 15) {
            if (cpt == 0) {
                if (verifWall()) {
                    save.add(new Pair(new Position(currPos.getRow(), currPos.getColumn()), cpt));
                    displace();
                } else {
                    turn(false);
                }
            } else if (verifLeftArm(searchExit, searchVault)) {
                turn(true);
                save.add(new Pair(new Position(currPos.getRow(), currPos.getColumn()), cpt));
                displace();
            } else if (verifWall()) {
                save.add(new Pair(new Position(currPos.getRow(), currPos.getColumn()), cpt));
                displace();
            } else {
                turn(false);
            }
            isBlocked = stateExisting();
            if (cpt >= 15) {
                isBlocked = true;
            }

        }
        return !isBlocked;
    }

    private static boolean stateExisting() {
        search = new Pair(new Position(currPos.getRow(), currPos.getColumn()), cpt);
        for (int i = 0; i < save.size() - 1; i++) {
            if (save.get(i).getFirst().getRow() == search.getFirst().getRow()
                    && save.get(i).getFirst().getColumn() == search.getFirst().getColumn()
                    && save.get(i).getSecond().equals(search.getSecond())) {
                isBlocked = true;
            }
        }
        return isBlocked;
    }
}
