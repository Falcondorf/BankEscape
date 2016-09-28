/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import bankescape.Direction;
import bankescape.Maze;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jackd
 */
public class TestModel {
    
 @Test
        public void playerInBound() {
                Maze mazeTest = new Maze(5,5); // MyClass is tested
                mazeTest.addPlayer(0, 0);
                mazeTest.movePlayer(Direction.UP);
                // assert statements
                assertEquals("le joueur ne peut pas sortir du tableau au Nord, ligne reste à 0 : ", 0,mazeTest.getPlayer().getRow() );   
                mazeTest.movePlayer(Direction.LEFT);
                assertEquals("le joueur ne peut pas sortir du tableau à l'Ouest colonne reste à 0 : ", 0,mazeTest.getPlayer().getColumn());   
                mazeTest.removePlayer(0, 0);
                mazeTest.addPlayer(4,4);
                mazeTest.movePlayer(Direction.DOWN);
                assertEquals(4,mazeTest.getPlayer().getRow());   
                System.out.println("le joueur ne peut pas sortir du tableau au Sud, ligne reste à 4 : "+mazeTest.getPlayer().getColumn());
                assertEquals( 4, mazeTest.getPlayer().getRow());
                mazeTest.movePlayer(Direction.RIGHT);
                assertEquals("le joueur ne peut pas sortir du tableau à l'Est, ligne reste à 4 : ", 4, mazeTest.getPlayer().getColumn());
        }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
