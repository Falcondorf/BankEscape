package threads;

import bankescape.*;
import java.util.Scanner;

/**
 *
 * @author pikirby45
 */
public class ThreadPlayer extends Thread {

    private Level l;
    private Scanner clavier;

    public ThreadPlayer(Level niveau) {
        this.l = niveau;
        clavier = new Scanner(System.in);
    }

    @Override
    public void run() {
        while (!l.isOver()) {
            try {
                String str = clavier.next();
                switch (str) {
                    case "z":
                        l.getMaze().movePlayer(Direction.UP);
                        break;
                    case "s":
                        l.getMaze().movePlayer(Direction.DOWN);
                        break;
                    case "d":
                        l.getMaze().movePlayer(Direction.RIGHT);
                        break;
                    case "q":
                        l.getMaze().movePlayer(Direction.LEFT);
                        break;
                }
                System.out.println(l.getMaze());
                sleep(200);
            } catch (InterruptedException ex) {
                System.out.println("ERROR");
            }
        }
    }
}
