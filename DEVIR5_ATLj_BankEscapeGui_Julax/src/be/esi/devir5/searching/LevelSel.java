
package be.esi.devir5.searching;

/**
 *
 * @author jackd
 */
public class LevelSel {
    private int lId;
    private String lName;
    private int width, height;
    private String nextLevel;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getNextLevel() {
        return nextLevel;
    }

    public void setNextLevel(String nextLevel) {
        this.nextLevel = nextLevel;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    
    public LevelSel(int id, String name, int width, int height,String nextLevel){
        this.lId = id;
        this.lName = name;
        this.width = width;
        this.height = height;
        this.nextLevel = nextLevel;
        
    }
    
    public LevelSel(String name){
        this.lId=0;
        this.lName = name;
    }

    public int getlId() {
        return lId;
    }

    public void setlId(int lId) {
        this.lId = lId;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }
    
    
}
