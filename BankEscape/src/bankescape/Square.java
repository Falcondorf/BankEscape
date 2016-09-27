
package bankescape;

/**
 *
 * @author jackd
 */
public class Square {
    private String type;

    public Square(String type) {
        this.type = type;
    }
    
    public boolean isPickable(){
        if(type == "drill" || type == "key"){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean isReachable(){
        if (type == "wall"){
            return false;
        }else{
            return true;
        }
    }
}
