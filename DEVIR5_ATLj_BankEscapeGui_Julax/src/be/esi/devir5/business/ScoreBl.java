
package be.esi.devir5.business;

import be.esi.devir5.db.ScoreDB;
import be.esi.devir5.dto.ScoreDto;
import be.esi.devir5.exception.BankEscapeDbException;
import be.esi.devir5.searching.ScoreSel;
import java.util.Collection;

/**
 *
 * @author Jules Dupont
 */
public class ScoreBl {
    
    
    
     static Collection<ScoreDto> getSelection(ScoreSel sel) throws BankEscapeDbException {
        return ScoreDB.getCollection(sel);
    }
     
     static int add(ScoreDto sco) throws BankEscapeDbException {
        return ScoreDB.insertDb(sco);
    }
//     
//     static void delete(int id) throws BankEscapeDbException {
//        ScoreDB.deleteDb(id);
//    }
}
