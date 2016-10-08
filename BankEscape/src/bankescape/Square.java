package bankescape;

/**
 *
 * @author jackd
 */
public class Square {

    private String type;
    private boolean hasPlayer;
    private boolean hasEnemy;
    private boolean hasDrill;
    private boolean hasKey;
    
    
    
    public Square() { //Floor, Wall, Vault, Entry, Exit. ==> Un vault 
        this.type = "floor";        //et une exit fermée ne sont pas reachable.
        this.hasPlayer = false;
        this.hasDrill = false;
        this.hasEnemy = false;
        this.hasKey = false;
        
    }
    
    
    public boolean hasPlayer() {
        return hasPlayer;
    }

    public void setHasPlayer() {
        this.hasPlayer = true;
    }
    public void removePlayer() {
        this.hasPlayer = false;
    }

    
    
    
    
    
    public boolean hasEnemy() {
        return hasEnemy;
    }

    public void setHasEnemy() {
        this.hasEnemy = true;
    }
     public void removeEnemy() {
        this.hasEnemy = false;
    }

     
     
     
     
     
    public boolean hasDrill() {
        return hasDrill;
    }

    public void setHasDrill() {
        this.hasDrill = true;
    }
    public void removeDrill() {
        this.hasDrill = false;
    }

    
    
    
    
    
    public boolean hasKey() {
        return hasKey;
    }

    public void setHasKey() {
        this.hasKey = true;
    }
    public void removeHasKey() {
        this.hasKey = false;
    }


    public boolean isReachable(boolean hasKey,boolean hasDrill) { //A compléter avec le vault et l'exit fermé
        return (hasDrill && type.equals("vault")) 
                || (hasKey && type.equals("exit")) 
                || (!type.equals("wall"));
    }

    public String getType() {
        return this.type;
    }

    public void setWall() {
        type = "wall";
    }

    public void setVault() {
        type = "vault";
    }

    public void setFloor() {
        type = "floor";
    }

    public void setEntry() {
        type = "entry";
    }

    public void setExit() {
        type = "exit";
    }

}
