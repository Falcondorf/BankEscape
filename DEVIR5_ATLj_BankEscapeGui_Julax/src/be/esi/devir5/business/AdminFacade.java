package be.esi.devir5.business;

import be.esi.devir5.exception.BankEscapeException;
import be.esi.devir5.exception.BankEscapeDbException;
import be.esi.devir5.dto.*;
import be.esi.devir5.db.*;
import be.esi.devir5.searching.*;

import java.util.Collection;

/**
 * Classe reprenant les méthodes destinées aux administrateurs
 */
public class AdminFacade {

    public static Collection<PlayerDto> getAllPlayers() throws BankEscapeDbException {
        PlayerSel playSel = new PlayerSel(0, "", 0, false, false, false);
        try {
            DBManager.startTransaction();
            Collection<PlayerDto> col = PlayerDB.getCollection(playSel);
            DBManager.validateTransaction();
            return col;
        } catch (BankEscapeDbException eDB) {
            String msg = eDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (BankEscapeDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new BankEscapeDbException("Liste des joueurs inaccessible! \n" + msg);
            }
        }
    }

    /**
     * retourne la collection des produits , repris dans le gestionnaire de
     * persistance, correspondants au critère de sélection de produit
     *
     * @param plySel le joueur sélectionné
     * @return la liste des produits
     * @throws be.esi.devir5.exception.BankEscapeDbException si la liste des
     * joueur n'est pas accessible
     */
    public static Collection<PlayerDto> getSelectedPlayers(PlayerSel plySel) throws BankEscapeDbException {
        try {
            DBManager.startTransaction();
            Collection<PlayerDto> col = PlayerDB.getCollection(plySel);
            DBManager.validateTransaction();
            return col;
        } catch (BankEscapeDbException eDB) {
            String msg = eDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (BankEscapeDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new BankEscapeDbException("Liste des joueurs inaccessible! \n" + msg);
            }
        }
    }

    /**
     * retourne le produit de clé id sous forme de <code>ProductDto</code>
     *
     * @param name le nom du player
     * @return ProductDto persistant de clé id ou null si non trouvé
     * @throws be.esi.devir5.exception.BankEscapeDbException le joueur n'est pas
     * accessible
     */
    public static PlayerDto findPlayerByName(String name) throws BankEscapeDbException {
        try {
            DBManager.startTransaction();
            PlayerSel sel = new PlayerSel(name);
            Collection<PlayerDto> col = PlayerBl.getSelection(sel);
            PlayerDto ply = null;
            if (col.size() == 1) {
                ply = col.iterator().next();
            }
            DBManager.validateTransaction();
            return ply;
        } catch (BankEscapeDbException eDB) {
            String msg = eDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (BankEscapeDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new BankEscapeDbException("joueur inaccessible! \n" + msg);
            }
        }
    }

    /**
     * ajoute au gestionnaire de persistance la marque reprise en paramètre
     *
     * @param player représentation de l objet joueur que l'on veut rajouter
     * @return id de la marque ajoutée
     * @throws be.esi.devir5.exception.BankEscapeDbException si l'ajout du
     * joueur est impossible
     */
    public static int addPlayer(PlayerDto player) throws BankEscapeDbException {
        try {
            DBManager.startTransaction();
            int i = PlayerBl.add(player);
            DBManager.validateTransaction();
            return i;
        } catch (BankEscapeDbException eDB) {
            String msg = eDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (BankEscapeDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new BankEscapeDbException("Ajout de joueur impossible! \n" + msg);
            }
        }
    }

    public static Collection<LevelDto> getAllLevels() throws BankEscapeDbException {
        LevelSel levSel = new LevelSel(0, "", 0, 0, "");
        try {
            DBManager.startTransaction();
            Collection<LevelDto> col = LevelsDB.getCollection(levSel);
            DBManager.validateTransaction();
            return col;
        } catch (BankEscapeDbException eDB) {
            String msg = eDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (BankEscapeDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new BankEscapeDbException("Liste des niveaux inaccessible! \n" + msg);
            }
        }
    }

    public static Collection<LevelDto> getSelectedLevels(LevelSel levSel) throws BankEscapeDbException {
        try {
            DBManager.startTransaction();
            Collection<LevelDto> col = LevelsDB.getCollection(levSel);
            DBManager.validateTransaction();
            return col;
        } catch (BankEscapeDbException eDB) {
            String msg = eDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (BankEscapeDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new BankEscapeDbException("Liste des niveaux inaccessible! \n" + msg);
            }
        }
    }

    public static LevelDto findLevelByName(String name) throws BankEscapeDbException {
        try {
            DBManager.startTransaction();
            LevelSel sel = new LevelSel(name);
            Collection<LevelDto> col = LevelsBl.getSelection(sel);
            LevelDto lev = null;
            if (col.size() == 1) {
                lev = col.iterator().next();
            }
            DBManager.validateTransaction();
            return lev;
        } catch (BankEscapeDbException eDB) {
            String msg = eDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (BankEscapeDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new BankEscapeDbException("Niveau inaccessible! \n" + msg);
            }
        }
    }

    public static int addLevel(LevelDto level) throws BankEscapeDbException {
        try {
            DBManager.startTransaction();
            int i = LevelsBl.add(level);
            DBManager.validateTransaction();
            return i;
        } catch (BankEscapeDbException eDB) {
            String msg = eDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (BankEscapeDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new BankEscapeDbException("Ajout de niveau impossible! \n" + msg);
            }
        }
    }

    /**
     * met à jour au niveau du gestionnaire de persistance le client passé en
     * paramètre
     *
     * @param lev Client à mettre à jour
     * @throws be.esi.devir5.exception.BankEscapeException la mise a jour de
     * niveau n'est pas possible
     */
    public static void updateLevels(LevelDto lev) throws BankEscapeException {
        try {
            System.out.println("maj");
            DBManager.startTransaction();
            LevelsBl.update(lev);
            DBManager.validateTransaction();
        } catch (BankEscapeDbException eDB) {
            String msg = eDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (BankEscapeDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new BankEscapeException("Mise à jour de niveaux impossible! \n" + msg);
            }
        }
    }

    public static Collection<LevelElementDto> getAllLevelElements() throws BankEscapeDbException {
        LevelElementSel levelSel = new LevelElementSel(0, 0, 0, 0, "");
        try {
            DBManager.startTransaction();
            Collection<LevelElementDto> col = LevelElementDB.getCollection(levelSel);
            DBManager.validateTransaction();
            return col;
        } catch (BankEscapeDbException eDB) {
            String msg = eDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (BankEscapeDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new BankEscapeDbException("Liste des niveaux inaccessible! \n" + msg);
            }
        }
    }

    public static Collection<LevelElementDto> getSelectedLevelElem(LevelElementSel levElSel) throws BankEscapeDbException {
        try {
            DBManager.startTransaction();
            Collection<LevelElementDto> col = LevelElementDB.getCollection(levElSel);
            DBManager.validateTransaction();
            return col;
        } catch (BankEscapeDbException eDB) {
            String msg = eDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (BankEscapeDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new BankEscapeDbException("Liste des LevelElements inaccessible! \n" + msg);
            }
        }
    }

    public static Collection<LevelElementDto> findLevelElementsInLevel(int levId) throws BankEscapeDbException {
        try {
            DBManager.startTransaction();
            LevelElementSel sel = new LevelElementSel(levId);
            Collection<LevelElementDto> col = LevelElementBl.getSelection(sel);
            return col;
//            LevelElementDto lev = null;
//            if (col.size() == 1) {
//                lev = col.iterator().next();
//            }
//            DBManager.validateTransaction();
//            return lev;
        } catch (BankEscapeDbException eDB) {
            String msg = eDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (BankEscapeDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new BankEscapeDbException("Element du Niveau inaccessible! \n" + msg);
            }
        }
    }

    public static int addLevelElement(LevelElementDto levelElem) throws BankEscapeDbException {
        try {
            DBManager.startTransaction();
            int i = LevelElementBl.add(levelElem);
            DBManager.validateTransaction();
            return i;
        } catch (BankEscapeDbException eDB) {
            String msg = eDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (BankEscapeDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new BankEscapeDbException("Ajout de l'élément du niveau impossible! \n" + msg);
            }
        }
    }

    public static void updateLevelElem(LevelElementDto lev) throws BankEscapeException {
        try {
            System.out.println("maj");
            DBManager.startTransaction();
            LevelElementBl.update(lev);
            DBManager.validateTransaction();
        } catch (BankEscapeDbException eDB) {
            String msg = eDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (BankEscapeDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new BankEscapeException("Mise à jour des éléments de niveaux impossible! \n" + msg);
            }
        }
    }

    /**
     * supprime du gestionnaire de persistance le client dont cli reprend l'id
     *
     * @param pla la representation de l'objet joueur que l'on veut supprimer
     * @throws be.esi.devir5.exception.BankEscapeDbException le joueur ne peut
     * pas etre supprimé
     */
    public static void deletePlayer(PlayerDto pla) throws BankEscapeDbException {
        try {
            if (pla.isPersistant()) {
                DBManager.startTransaction();
                PlayerBl.delete(pla.getId());
                DBManager.validateTransaction();
            } else {
                throw new BankEscapeDbException("Player: impossible de supprimer un client inexistant!");
            }
        } catch (BankEscapeDbException eDB) {
            String msg = eDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (BankEscapeDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new BankEscapeDbException("Suppression de joueur impossible! \n" + msg);
            }
        }
    }

    public static void deleteLevel(LevelDto lev) throws BankEscapeDbException {
        try {
            if (lev.isPersistant()) {
                DBManager.startTransaction();
                LevelsBl.delete(lev.getlName());
                DBManager.validateTransaction();
            } else {
                throw new BankEscapeDbException("Level: impossible de supprimer un niveau inexistant!");
            }
        } catch (BankEscapeDbException eDB) {
            String msg = eDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (BankEscapeDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new BankEscapeDbException("Suppression de Level impossible! \n" + msg);
            }
        }
    }

    public static void deleteLevelElement(LevelElementDto levEl) throws BankEscapeDbException {
        try {
            if (levEl.isPersistant()) {
                DBManager.startTransaction();
                LevelElementBl.delete(levEl.getId());
                DBManager.validateTransaction();
            } else {
                throw new BankEscapeDbException("LevelElement: impossible de supprimer un niveau inexistant!");
            }
        } catch (BankEscapeDbException eDB) {
            String msg = eDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (BankEscapeDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new BankEscapeDbException("Suppression de LevelElement impossible! \n" + msg);
            }
        }
    }

    public static int addScore(ScoreDto score) throws BankEscapeDbException {
        try {
            DBManager.startTransaction();
            int i = ScoreBl.add(score);
            DBManager.validateTransaction();
            return i;
        } catch (BankEscapeDbException eDB) {
            String msg = eDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (BankEscapeDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new BankEscapeDbException("Ajout de score impossible! \n" + msg);
            }
        }
    }

    public static Collection<ScoreDto> getAllScore() throws BankEscapeDbException {
        ScoreSel scoreSel = new ScoreSel(0, 0, 0, 0);
        try {
            DBManager.startTransaction();
            Collection<ScoreDto> col = ScoreDB.getCollection(scoreSel);
            DBManager.validateTransaction();
            return col;
        } catch (BankEscapeDbException eDB) {
            String msg = eDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (BankEscapeDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new BankEscapeDbException("Liste des scores inaccessible! \n" + msg);
            }
        }
    }

}
