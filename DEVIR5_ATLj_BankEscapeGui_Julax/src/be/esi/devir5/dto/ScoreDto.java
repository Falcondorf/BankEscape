
package be.esi.devir5.dto;

/**
 *
 * @author Jules Dupont
 */
public class ScoreDto extends EntityDto<Integer>{
    private int pId;
    private int lId;
    private int score;

    public ScoreDto(int pId, int lId, int score) {
        this.pId = pId;
        this.lId = lId;
        this.score = score;        
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public int getlId() {
        return lId;
    }

    public void setlId(int lId) {
        this.lId = lId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }


    
    
    
}
