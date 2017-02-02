
package be.esi.devir5.business;

import be.esi.devir5.db.LevelsDB;
import be.esi.devir5.dto.LevelDto;
import be.esi.devir5.exception.BankEscapeDbException;
import be.esi.devir5.searching.LevelSel;
import java.util.Collection;

/**
 *
 * @author jackd
 */
public class LevelsBl {
    
    static Collection<LevelDto> getSelection(LevelSel sel) throws BankEscapeDbException {
        return LevelsDB.getCollection(sel);
    }
    
    static int add(LevelDto lev) throws BankEscapeDbException {
        return LevelsDB.insertDb(lev);
    }
    
    static void update(LevelDto lev) throws BankEscapeDbException {
        LevelsDB.updateDb(lev);
    }
    
    static void delete(String name) throws BankEscapeDbException {
        LevelsDB.deleteDb(name);
    }
}
