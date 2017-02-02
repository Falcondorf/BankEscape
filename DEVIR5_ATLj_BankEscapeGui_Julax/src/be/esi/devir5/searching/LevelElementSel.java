package be.esi.devir5.searching;

/**
 *
 * @author jackd
 */
public class LevelElementSel {
    private int leId, levId, posX, posY;
    private String lName;

    public LevelElementSel(int leId, int levId, int posX, int posY, String lName) {
        this.leId = leId;
        this.levId = levId;
        this.posX = posX;
        this.posY = posY;
        this.lName = lName;
    }
    
    public LevelElementSel (int levId){
        this.levId = levId;
    }

    public int getLeId() {
        return leId;
    }

    public void setLeId(int leId) {
        this.leId = leId;
    }

    public int getLevId() {
        return levId;
    }

    public void setLevId(int levId) {
        this.levId = levId;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }
    
    
}
