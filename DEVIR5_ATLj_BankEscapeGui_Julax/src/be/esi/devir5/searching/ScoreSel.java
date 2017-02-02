package be.esi.devir5.searching;

/**
 *
 * @author Jules Dupont
 */
public class ScoreSel {

    private int pId;
    private int lId;
    private int score;
    private int id;

    public ScoreSel(int pId, int lId, int score, int id) {
        this.pId = pId;
        this.lId = lId;
        this.score = score;
        this.id = id;
    }

    public ScoreSel(int score) {
        this.score = score;
    }

     public void setpId(int pId) {
        this.pId = pId;
    }
     
     
     
    
    public int getpId() {
        return pId;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
