
package be.esi.devir5.business;

import be.esi.devir5.db.PlayerDB;
import be.esi.devir5.dto.PlayerDto;
import be.esi.devir5.exception.BankEscapeDbException;
import be.esi.devir5.searching.PlayerSel;
import java.util.Collection;

/**
 *
 * @author jackd
 */
public class PlayerBl {
    static PlayerDto findByName(String name) throws BankEscapeDbException {
        PlayerSel sel = new PlayerSel(name);
        Collection<PlayerDto> col = PlayerDB.getCollection(sel);
        if (col.size() == 1) {
            return col.iterator().next();
        } else {
            return null;
        }
    }
    
     static Collection<PlayerDto> getSelection(PlayerSel sel) throws BankEscapeDbException {
        return PlayerDB.getCollection(sel);
    }
     
     static int add(PlayerDto cli) throws BankEscapeDbException {
        return PlayerDB.insertDb(cli);
    }
     
     static void delete(int id) throws BankEscapeDbException {
        PlayerDB.deleteDb(id);
    }
}
