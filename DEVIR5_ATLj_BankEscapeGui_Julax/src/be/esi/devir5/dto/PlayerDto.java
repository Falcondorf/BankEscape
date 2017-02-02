package be.esi.devir5.dto;

import be.esi.devir5.exception.BankEscapeDbException;

/**
 *
 * @author jackd
 */
public class PlayerDto extends EntityDto<Integer>{

    private String pName;
    private int lvId;
    private boolean hasKey;
    private boolean hasDrill;
    private boolean hasMoney;    

    public PlayerDto(int pId, String pName, int lvId, boolean hasKey, boolean hasDril, boolean hasMoney ) throws BankEscapeDbException {
        if (pName == null || pId <0) {
            throw new BankEscapeDbException("attributs Id et name doivent etre valides");
        }
        this.pName = pName;
        this.id = pId;
        this.lvId = lvId;
        this.hasKey = hasKey;
        this.hasDrill = hasDril;
        this.hasMoney = hasMoney;
    }

    public String getName() {
        return pName;
    }

    public void setId(int pId) {
        this.id = pId;
    }

    public void setName(String pName) {
        this.pName = pName;
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
