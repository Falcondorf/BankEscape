
package be.esi.devir5.dto;

/**
 *
 * @author jackd
 */
public class LevelElementDto extends EntityDto<Integer>{
    private int levId;
    private String nomElem;
    private int posX, posY;

    public LevelElementDto(int leId, int levId, String nomElem, int posX, int posY) {
        this.id = leId;
        this.levId = levId;
        this.nomElem = nomElem;
        this.posX = posX;
        this.posY = posY;
    }

    public int getLeId() {
        return id;
    }

    public void setLeId(int leId) {
        this.id = leId;
    }

    public int getLevId() {
        return levId;
    }

    public void setLevId(int levId) {
        this.levId = levId;
    }

    public String getNomElem() {
        return nomElem;
    }

    public void setNomElem(String nomElem) {
        this.nomElem = nomElem;
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
    
    
}
