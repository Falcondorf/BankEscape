package be.esi.devir5.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Classe étendue d'un Movable représentant un vigile (ennemi)
 *
 * @author pikirby45
 */
public class Enemy extends Movable {

    private boolean inAlert = false;

    private List<Direction> possibleDirection;
    private Random random = new Random();

    /**
     * Constructeur d'ennemi
     *
     * @param direction La première direction vers laquelle se dirige le vigile
     * @param pos La position du vigile
     */
    public Enemy(Direction direction, Position pos) {
        super(pos,direction);
        possibleDirection = new ArrayList<>();
    }

    /**
     * Accesseur de l'état d'alerte d'un vigile
     *
     * @return L'état d'alerte du vigile
     */
    public boolean isInAlert() {
        return inAlert;
    }

    /**
     * Mutateur de l'état d'alerte du vigile
     *
     * @param inAlert Si un vigile doit être ou non en état d'alerte
     */
    public void setInAlert(boolean inAlert) {
        this.inAlert = inAlert;
    }


    /**
     * Permet de savoir combien de direction sont possible autour du vigile
     *
     * @return Le nombre de direction autour du vigile
     */
    public int getNumberDirections() {
        return possibleDirection.size();
    }

    /**
     * Permet l'ajout d'une direction dans sa liste de direction possible
     *
     * @param dir La nouvelle direction à ajouter
     */
    public void addPossibleDirection(Direction dir) {
        this.possibleDirection.add(dir);
    }

    /**
     * Nettoie et vide la liste des directions possibles du vigile
     */
    public void resetPossibleDirection() {
        possibleDirection.clear();
    }

    /**
     * Choisit une direction au hasard parmis la liste des directions possible
     *
     * @return Une direction au hasard
     */
    public Direction randDir() {
        //Random rand = new Random();
        int i = random.nextInt(getNumberDirections());
        return possibleDirection.get(i);
    }

}
