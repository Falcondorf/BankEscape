
package bankescape;

/**
 *
 * @author jackd
 */
public class Placeble extends Square{
    private String type;

    public Placeble(String type) {
        super(type);
    }

   

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
}
