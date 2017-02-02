
package be.esi.devir5.db;

import be.esi.devir5.dto.ScoreDto;
import be.esi.devir5.exception.BankEscapeDbException;
import be.esi.devir5.searching.ScoreSel;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Jules Dupont
 */
public class ScoreDB {
    public static List<ScoreDto> getCollection(ScoreSel sel) throws BankEscapeDbException {
        ArrayList<ScoreDto> al = new ArrayList<ScoreDto>();
        try {
            String query = "Select id, pId, lId, score FROM Score ";
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement stmt;
            String where = "";
            if (sel.getId() != 0) {
                where = where + " id = ? ";
            }  
            if (sel.getpId() != 0) {
                where = where + " pId = ? ";
            }
            if (sel.getlId()!= 0) {
                where = where + " lId = ? ";
            }
            
            if (sel.getScore()!= 0) {
                where = where + " score = ? ";
            }
            

            if (where.length() != 0) {
                where = " where " + where + " order by id";
                query = query + where;
                stmt = connexion.prepareStatement(query);
                int i = 1;
                if (sel.getId()!= 0) {
                    stmt.setInt(i, sel.getlId());
                    i++;
                }
                if (sel.getpId()!= 0) {
                    stmt.setInt(i, sel.getpId());
                    i++;
                }
                if (sel.getlId()!= 0) {
                    stmt.setInt(i, sel.getlId());
                    i++;
                }
                 if (sel.getScore()!= 0) {
                    stmt.setInt(i, sel.getScore());
                    i++;
                }
            } else {
                query = query + " Order by id";
                stmt = connexion.prepareStatement(query);
            }
            //System.out.println(query);
            java.sql.ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                al.add(new ScoreDto(rs.getInt("pId"), rs.getInt("lId"), rs.getInt("score")));
            }
        } catch (java.sql.SQLException eSQL) {
            throw new BankEscapeDbException("Instanciation de Level impossible:\nSQLException: "
                    + eSQL.getMessage());
        }
        return al;
    }
    
        public static int insertDb(ScoreDto sco) throws BankEscapeDbException {
        try {
            int num = SequenceDB.getNextNum(SequenceDB.SCORE);
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement insert;
            insert = connexion.prepareStatement(
                    "Insert into Score(id, pId, lId, score) "
                    + "values(?, ?, ?, ?)");
            insert.setInt(1, num);
            insert.setInt(2, sco.getpId());
            insert.setInt(3, sco.getlId());
            insert.setInt(4, sco.getScore());
            insert.executeUpdate();
            return num;
        } catch (Exception ex) {
            throw new BankEscapeDbException("Score: ajout impossible\n" + ex.getMessage());
        }
    }
    
    
}
