
package be.esi.devir5.db;

import be.esi.devir5.dto.LevelElementDto;
import be.esi.devir5.exception.BankEscapeDbException;
import be.esi.devir5.searching.LevelElementSel;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jackd
 */
public class LevelElementDB {
    public static List<LevelElementDto> getCollection(LevelElementSel sel) throws BankEscapeDbException {
        ArrayList<LevelElementDto> al = new ArrayList<LevelElementDto>();
        try {
            String query = "Select leId, levId, nomElem, posx, posy FROM LevelElement ";
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement stmt;
            String where = "";
            if (sel.getLeId()!= 0) {
                where = where + " leId = ? ";
            }
            if (sel.getLevId() != 0) {
                where = where + " levId = ? ";
            }
            if (sel.getPosX()!= 0) {
                where = where + " posx = ? ";
            }
            if (sel.getPosY()!= 0) {
                where = where + " posy = ? ";
            }
            if (sel.getlName()!= null) {
                if (!where.equals("")) {
                    where = where + " AND ";
                }
                where = where + " nomelem=upper(?)";
            }

            if (where.length() != 0) {
                where = " where " + where + " order by leId";
                query = query + where;
                stmt = connexion.prepareStatement(query);
                int i = 1;
                if (sel.getLeId() != 0) {
                    stmt.setInt(i, sel.getLeId());
                    i++;
                }
                if (sel.getLevId() != 0) {
                    stmt.setInt(i, sel.getLevId());
                    i++;
                }
                if (sel.getPosX() != 0) {
                    stmt.setInt(i, sel.getPosX());
                    i++;
                }
                if (sel.getPosY() != 0) {
                    stmt.setInt(i, sel.getPosY());
                    i++;
                }
                if (sel.getlName() != null && !sel.getlName().equals("")) {
                    stmt.setString(i, sel.getlName());
                    i++;
                }
            } else {
                query = query + " Order by leId";
                stmt = connexion.prepareStatement(query);
            }
            //System.out.println(query);
            java.sql.ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                al.add(new LevelElementDto(rs.getInt("leId"), rs.getInt("levId"), rs.getString("nomelem"), rs.getInt("posx"), rs.getInt("posy")));
            }
        } catch (java.sql.SQLException eSQL) {
            throw new BankEscapeDbException("Instanciation de LevelElement impossible:\nSQLException: "
                    + eSQL.getMessage());
        }
        return al;
    }
    
    public static int insertDb(LevelElementDto levEl) throws BankEscapeDbException {
        try {
            int num = SequenceDB.getNextNum(SequenceDB.LEVELEMENT);
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement insert;
            insert = connexion.prepareStatement(
                    "Insert into LevelElement(leid, levid, nomelem, posx, posy) "
                    + "values(?, ?, upper(?), ?, ?)");
            insert.setInt(1, num);
            insert.setInt(2, levEl.getLevId());
            insert.setString(3, levEl.getNomElem());
            insert.setInt(4, levEl.getPosX());
            insert.setInt(5, levEl.getPosY());
            insert.executeUpdate();
            return num;
        } catch (Exception ex) {
            throw new BankEscapeDbException("LevelElement: ajout impossible\n" + ex.getMessage());
        }
    }
    
    public static void updateDb(LevelElementDto lev) throws BankEscapeDbException {
        try {
            java.sql.Connection connexion = DBManager.getConnection();

            java.sql.PreparedStatement update;
            String sql = "Update LevelElement set "
                    + " leId=?, "
                    + "levId=?, "                    
                    + "lName=upper('?'), "
                    + "posx=?, "
                    + "posy=?, ";
            System.out.println(sql);
            update = connexion.prepareStatement(sql);
            if (lev.getNomElem() == null) {
                update.setNull(3, Types.VARCHAR);
            } else {
                update.setString(3, lev.getNomElem());
            }
            update.setString(1, Integer.toString(lev.getId()));
            update.setString(2, Integer.toString(lev.getLevId()));
            update.setString(4, Integer.toString(lev.getPosX()));
            update.setString(5, Integer.toString(lev.getPosY()));
           
            update.executeUpdate();
        } catch (Exception ex) {
            throw new BankEscapeDbException("Levelelement, modification impossible:\n" + ex.getMessage());
        }
    }
    
    public static void deleteDb(int id) throws BankEscapeDbException {
        try {
            java.sql.Statement stmt = DBManager.getConnection().createStatement();
            stmt.execute("Delete from LevelElement where leId=" + id);
        } catch (Exception ex) {
            throw new BankEscapeDbException("LevelElement: suppression impossible\n" + ex.getMessage());
        }
    }
}
