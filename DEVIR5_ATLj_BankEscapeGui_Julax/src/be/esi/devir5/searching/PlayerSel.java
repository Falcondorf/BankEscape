
package be.esi.devir5.searching;

/**
 *
 * @author jackd
 */
public class PlayerSel {

    private int id;

    private String name;
    private int lvId;
    private boolean hasKey;
    private boolean hasDrill;
    private boolean hasMoney;   

    public PlayerSel(int id, String name, int lvId, boolean hasKey, boolean hasDrill, boolean hasMoney) {
        this.id = id;
        this.name = name;
        this.lvId = lvId;
        this.hasDrill = hasDrill;
        this.hasKey = hasKey;
        this.hasMoney = hasMoney;
    }

    public PlayerSel(String name) {
        this.name = name;
    }
    
    public PlayerSel(int lvId){
        this.lvId = lvId;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLvId() {
        return lvId;
    }

    public void setLvId(int lvId) {
        this.lvId = lvId;
    }

    public boolean isHasKey() {
        return hasKey;
    }

    public void setHasKey(boolean hasKey) {
        this.hasKey = hasKey;
    }

    public boolean isHasDrill() {
        return hasDrill;
    }

    public void setHasDrill(boolean hasDrill) {
        this.hasDrill = hasDrill;
    }

    public boolean isHasMoney() {
        return hasMoney;
    }

    public void setHasMoney(boolean hasMoney) {
        this.hasMoney = hasMoney;
    }
    
}
