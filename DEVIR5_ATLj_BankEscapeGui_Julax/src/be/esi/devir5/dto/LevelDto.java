package be.esi.devir5.dto;

/**
 *
 * @author jackd
 */
public class LevelDto extends EntityDto<Integer>{

    private String lName;
    private int width;
    private int height;
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

    public LevelDto(int id, String name, int width, int height,String nextLevel) {
        this.id = id;
        this.lName = name;
        this.width = width;
        this.height = height;
        this.nextLevel = nextLevel;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        if (this.lName.equals(lName)) {
            this.lName = lName;
        }
    }

}
