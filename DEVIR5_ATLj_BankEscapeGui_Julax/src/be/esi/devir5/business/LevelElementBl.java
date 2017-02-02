
package be.esi.devir5.business;

import be.esi.devir5.db.LevelElementDB;
import be.esi.devir5.dto.LevelElementDto;
import be.esi.devir5.exception.BankEscapeDbException;
import be.esi.devir5.searching.LevelElementSel;
import java.util.Collection;

/**
 *
 * @author jackd
 */
public class LevelElementBl {
    static LevelElementDto findInLevel(int levId) throws BankEscapeDbException {
        LevelElementSel sel = new LevelElementSel(levId);
        Collection<LevelElementDto> col = LevelElementDB.getCollection(sel);
        if (col.size() == 1) {
            return col.iterator().next();
        } else {
            return null;
        }
    }
    
     static Collection<LevelElementDto> getSelection(LevelElementSel sel) throws BankEscapeDbException {
        return LevelElementDB.getCollection(sel);
    }
     
     static int add(LevelElementDto levEl) throws BankEscapeDbException {
        return LevelElementDB.insertDb(levEl);
    }
     
     static void update(LevelElementDto lev) throws BankEscapeDbException {
        LevelElementDB.updateDb(lev);
    }
     
     static void delete(int id) throws BankEscapeDbException {
        LevelElementDB.deleteDb(id);
    }
}
