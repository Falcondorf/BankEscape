
package be.esi.devir5.db;

import be.esi.devir5.exception.BankEscapeDbException;

/**
 *
 * @author jackd
 */
public class SequenceDB {
    static final String PLAYER = "PLAYER";
    static final String LEVEL = "LEVELS";
    static final String LEVELEMENT = "LEVELELEMENT";
    static final String SCORE = "SCORE";
    
    static synchronized int getNextNum(String sequence) throws BankEscapeDbException {
        try {
            java.sql.Connection connexion = DBManager.getConnection();
            String query = "Update SEQUENCES set sValue = sValue+1 where id='" + sequence + "'";
            java.sql.PreparedStatement update = connexion.prepareStatement(query);
            update.execute();
            java.sql.Statement stmt = connexion.createStatement();
            query = "Select sValue FROM Sequences where id='" + sequence + "'";
            java.sql.ResultSet rs = stmt.executeQuery(query);
            int nvId;
            if (rs.next()) {
                nvId = rs.getInt("sValue");
                return nvId;
            } else {
                throw new BankEscapeDbException("Nouveau n° de séquence inaccessible!");
            }
        } catch (java.sql.SQLException eSQL) {
            throw new BankEscapeDbException("Nouveau n° de séquence inaccessible!\n" + eSQL.getMessage());
        }
    }
}
