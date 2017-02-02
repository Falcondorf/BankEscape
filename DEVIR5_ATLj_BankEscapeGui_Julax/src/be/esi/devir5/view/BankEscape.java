package be.esi.devir5.view;

import be.esi.devir5.business.*;
import be.esi.devir5.dto.LevelDto;
import be.esi.devir5.dto.LevelElementDto;
import be.esi.devir5.dto.PlayerDto;
import be.esi.devir5.dto.ScoreDto;
import be.esi.devir5.exception.BankEscapeDbException;
import java.util.Collection;


/**
 *
 * @author jackd
 */
public class BankEscape {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            AdminFacade.addLevel(new LevelDto(11, "NIVEAU11",10, 10,"niveautest"));
            System.out.println("Level added");
            AdminFacade.addLevelElement(new LevelElementDto(11, AdminFacade.findLevelByName("NIVEAU11").getId(), "wall", 1, 1));
            System.out.println("Elem1 added");
            AdminFacade.addLevelElement(new LevelElementDto(11, AdminFacade.findLevelByName("NIVEAU11").getId(), "vault", 1, 2));
            System.out.println("Elem2 added");
            
            AdminFacade.addPlayer(new PlayerDto(0, "gary",AdminFacade.findLevelByName("NIVEAU11").getId(), false, false, false));
            System.out.println("Gary added");
            AdminFacade.addScore(new ScoreDto(1,AdminFacade.findLevelByName("NIVEAU11").getId() , 3000));
            System.out.println("score added");   
            
            AdminFacade.addScore(new ScoreDto(1,AdminFacade.findLevelByName("NIVEAU11").getId() , 4000));
            
            
            Collection<ScoreDto> sl = AdminFacade.getAllScore();
            
            for(ScoreDto sdto : sl){
                System.out.println(sdto.getScore());
            }
            System.out.println("Everything done");
        } catch (BankEscapeDbException ex) {
            System.out.println(ex.getMessage());
        }

    }

}
