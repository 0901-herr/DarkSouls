package game;

import edu.monash.fit2099.engine.*;
import game.enums.Status;

/**
 * The Yhorm boss.
 */
public class Yhorm extends LordOfCinder {
    private EnrageBehaviour enrageBehaviour;

    /**
     * Constructor.
     *
     * @param name name of Yhorm
     * @param displayChar character representing Yhorm
     * @param hitPoints the hitpoints of Yhorm
     * @param initialLocation the initial location of Yhorm
     */
    public Yhorm(String name, char displayChar, int hitPoints, Location initialLocation) {
        super(name, displayChar, hitPoints, initialLocation, 5000);
        this.addCapability(Status.IS_YHORM);
        this.addCapability(Status.WEAK_TO_STORMRULER);
        this.addItemToInventory(new GreatMachete(this));
        this.addItemToInventory(new CinderOfLord());
    }

    /**
     * Figure out what to do next, including invoking enrage behaviour,
     * being stunned etc.
     *
     * @param actions    the available options of actions
     * @param lastAction the last action performed
     * @param map        the map containing Yhorm
     * @param display    the I/O object to which messages may be written
     * @return do nothing action
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        // when Yhorm hp < 50% max hp, add EnrageBehaviour
        if (getHitPoints() < (getMaxHitPoints()*0.5)) {
            this.addCapability(Status.RAGE_MODE);
            addEnrageBehaviour();
            display.println(this + " is in RAGE MODE, hit rate increases");
        }

        // when Yhorm is stunned
        if (this.hasCapability(Status.STUNNED)){
            display.println(this + " is stunned");
            return new DoNothingAction();
        }

        return super.playTurn(actions, lastAction, map, display);
    }

    /**
     * Adding Yhorm's enrage behaviour to the behaviours list
     *
     */
    public void addEnrageBehaviour() {
        if (enrageBehaviour == null) {
            this.setEnrageBehaviour(new EnrageBehaviour());
            this.getBehaviours().add(0, new EnrageBehaviour());
        }
    }

    /**
     * Resetting Yhorm
     *
     */
    @Override
    public void resetInstance() {
        this.getBehaviours().clear();

        // remove FollowBehaviour
        this.setFollowBehaviour(null);

        // remove EnrageBehaviour
        this.setEnrageBehaviour(null);
        this.removeCapability(Status.RAGE_MODE);

        // reset location
        this.getInitialLocation().map().moveActor(this, this.getInitialLocation());
        this.hitPoints = this.getMaxHitPoints();
    }

    /**
     * Setting Yhorm's enrage behaviour
     *
     * @param enrageBehaviour
     */
    public void setEnrageBehaviour(EnrageBehaviour enrageBehaviour) {
        this.enrageBehaviour = enrageBehaviour;
    }
}
