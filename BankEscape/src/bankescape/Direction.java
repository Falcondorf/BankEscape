
package bankescape;

/**
 *
 * @author jackd
 */
public enum Direction {
    
    UP("haut"),
    DOWN("bas"),
    LEFT("gauche"),
    RIGHT("droite");
    
    private String name;
    
    Direction(String s){
        this.name = s;
    }
    
    @Override
    public String toString(){
        return this.name;
    }
}
