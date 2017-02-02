
package be.esi.devir5.db;

import be.esi.devir5.dto.LevelDto;
import be.esi.devir5.exception.BankEscapeDbException;
import be.esi.devir5.searching.LevelSel;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jackd
 */
public class LevelsDB {
    
    
    public static List<LevelDto> getCollection(LevelSel sel) throws BankEscapeDbException {
        ArrayList<LevelDto> al = new ArrayList<LevelDto>();
        try {
            String query = "Select lId, lName, width, height, nextLevel FROM Levels ";
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement stmt;
            String where = "";
            if (sel.getlId() != 0) {
                where = where + " lId = ? ";
            }
            if (sel.getlName() != null) {
                if (!where.equals("")) {
                    where = where + " AND ";
                }
                where = where + " lName=upper(?) ";
            }
            if (sel.getWidth() != 0) {
                where = where + " width = ? ";
            }
            if (sel.getHeight() != 0) {
                where = where + " height = ? ";
            }
            if (sel.getNextLevel()!= null) {
                if (!where.equals("")) {
                    where = where + " AND ";
                }
                where = where + " nextLevel=upper(?) ";
            }

            if (where.length() != 0) {
                where = " where " + where + " order by lId";
                query = query + where;
                stmt = connexion.prepareStatement(query);
                int i = 1;
                if (sel.getlId() != 0) {
                    stmt.setInt(i, sel.getlId());
                    i++;

                }
                if (sel.getlName() != null && !sel.getlName().equals("")) {
                    stmt.setString(i, sel.getlName());
                    i++;
                }
                if (sel.getWidth() != 0) {
                    stmt.setInt(i, sel.getWidth());
                    i++;

                }
                if (sel.getHeight() != 0) {
                    stmt.setInt(i, sel.getHeight());
                    i++;

                }
                if (sel.getNextLevel()!= null && !sel.getNextLevel().equals("")) {
                    stmt.setString(i, sel.getNextLevel());
                    i++;
                }
            } else {
                query = query + " Order by lId";
                stmt = connexion.prepareStatement(query);
            }
            //System.out.println(query);
            java.sql.ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                al.add(new LevelDto(rs.getInt("lId"), rs.getString("lName"), rs.getInt("width"), rs.getInt("height"), rs.getString("nextLevel")));
            }
        } catch (java.sql.SQLException eSQL) {
            throw new BankEscapeDbException("Instanciation de Level impossible:\nSQLException: "
                    + eSQL.getMessage());
        }
        return al;
    }
    
    public static int insertDb(LevelDto lev) throws BankEscapeDbException {
        try {
            int num = SequenceDB.getNextNum(SequenceDB.LEVEL);
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement insert;
            insert = connexion.prepareStatement(
                    "Insert into Levels(lid, lName, width, height, nextLevel) "
                    + "values(?, upper(?), ?, ?, upper(?))");
            insert.setInt(1, num);
            insert.setString(2, lev.getlName());
            insert.setInt(3, lev.getWidth());
            insert.setInt(4, lev.getHeight());
            insert.setString(5, lev.getNextLevel());
            insert.executeUpdate();
            return num;
        } catch (Exception ex) {
            throw new BankEscapeDbException("Levels: ajout impossible\n" + ex.getMessage());
        }
    }
    
    public static void updateDb(LevelDto lev) throws BankEscapeDbException {
        try {
            java.sql.Connection connexion = DBManager.getConnection();

            java.sql.PreparedStatement update;
            String sql = "Update Levels set "
                    + " lId=?,"
                    + "lName=upper(?), "
                    + "width=?"
                    + "height=?"
                    + "nextLevel=?";
            System.out.println(sql);
            update = connexion.prepareStatement(sql);
            if (lev.getlName() == null) {
                update.setNull(2, Types.VARCHAR);
            } else {
                update.setString(2, lev.getlName());
            }
            update.setInt(1, lev.getId());
            update.setInt(3, lev.getWidth());
            update.setInt(4, lev.getHeight());
            if (lev.getNextLevel()== null) {
                update.setNull(2, Types.VARCHAR);
            } else {
               update.setString(5, lev.getNextLevel());
            }            
           
            update.executeUpdate();
        } catch (Exception ex) {
            throw new BankEscapeDbException("Levels, modification impossible:\n" + ex.getMessage());
        }
    }
    
    public static void deleteDb(String name) throws BankEscapeDbException {
        try {
            java.sql.Statement stmt = DBManager.getConnection().createStatement();
            stmt.execute("Delete from Levels where lName=upper('" + name + "')");
        } catch (Exception ex) {
            throw new BankEscapeDbException("Levels: suppression impossible\n" + ex.getMessage());
        }
    }
}
