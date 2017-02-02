
package be.esi.devir5.db;

import be.esi.devir5.dto.PlayerDto;
import be.esi.devir5.exception.BankEscapeDbException;
import be.esi.devir5.searching.PlayerSel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jackd
 */
public class PlayerDB {

    public static List<PlayerDto> getCollection(PlayerSel sel) throws BankEscapeDbException {
        ArrayList<PlayerDto> al = new ArrayList<PlayerDto>();
        try {
            String query = "Select pid, pname, lvId, haskey, hasdrill, hasmoney FROM Player ";
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement stmt;
            String where = "";
            if (sel.getId() != 0) {
                where = where + " pId = ? ";
            }
            if (sel.getName() != null) {
                if (!where.equals("")) {
                    where = where + " AND ";
                }
                where = where + " pName = upper(?) ";
            }
            if(sel.getLvId() != 0){
                 where = where + "lvid = ? ";
            }
            

            if (where.length() != 0) {
                where = " where " + where + " order by pId";
                query = query + where;
                stmt = connexion.prepareStatement(query);
                int i = 1;
                if (sel.getId() != 0) {
                    stmt.setInt(i, sel.getId());
                    i++;

                }
                if (sel.getName() != null && !sel.getName().equals("")) {
                    stmt.setString(i, sel.getName());
                    i++;
                }
                if (sel.getLvId() != 0) {
                    stmt.setInt(i, sel.getLvId());
                    i++;

                }
//                stmt.setBoolean(i, sel.isHasKey());
//                i++;
//                stmt.setBoolean(i, sel.isHasDrill());
//                i++;
//                stmt.setBoolean(i, sel.isHasMoney());
//                i++;

            } else {
                query = query + " Order by pId";
                stmt = connexion.prepareStatement(query);
            }
            //System.out.println(query);
            java.sql.ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                al.add(new PlayerDto(rs.getInt("pid"), rs.getString("pName"),
                        rs.getInt("lvid"), rs.getBoolean("haskey"),
                        rs.getBoolean("hasdrill"), rs.getBoolean("hasmoney")));
            }
        } catch (java.sql.SQLException eSQL) {
            throw new BankEscapeDbException("Instanciation de Player impossible:\nSQLException: "
                    + eSQL.getMessage());
        }
        return al;
    }

    public static int insertDb(PlayerDto play) throws BankEscapeDbException {
        try {
            int num = SequenceDB.getNextNum(SequenceDB.PLAYER);
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement insert;
            insert = connexion.prepareStatement(
                    "Insert into Player(pid, pName, lvid, haskey, hasdrill, hasmoney) "
                    + "values(?, ?, ?, ?, ?, ?)");
            insert.setInt(1, num);
            insert.setString(2, play.getName());
            insert.setInt(3, play.getLvId());
            insert.setBoolean(4, play.isHasKey());
            insert.setBoolean(5, play.isHasDrill());
            insert.setBoolean(6, play.isHasMoney());
            insert.executeUpdate();
            return num;
        } catch (Exception ex) {
            throw new BankEscapeDbException("Player: ajout impossible\n" + ex.getMessage());
        }
    }

    public static void deleteDb(int id) throws BankEscapeDbException {
        try {
            java.sql.Statement stmt = DBManager.getConnection().createStatement();
            stmt.execute("Delete from Player where pId=" + id);
        } catch (Exception ex) {
            throw new BankEscapeDbException("Player: suppression impossible\n" + ex.getMessage());
        }
    }

}
