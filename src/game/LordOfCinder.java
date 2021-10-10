package game;

import edu.monash.fit2099.engine.*;
import game.enums.Abilities;
import game.enums.Status;
import game.interfaces.Resettable;
import game.interfaces.Soul;

/**
 * The boss of Design o' Souls
 */
public abstract class LordOfCinder extends Enemy {
    private EnrageBehaviour enrageBehaviour;

    /**
     * Constructor.
     */
    public LordOfCinder(String name, char displayChar, int hitPoints, Location initialLocation, int souls) {
        super(name, displayChar, hitPoints, initialLocation, souls);
    }

    /**
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn.
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the current action to be executed
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        return super.playTurn(actions, lastAction, map, display);
    }

    /**
     * Adding Yhorm's enrage behaviour to the behaviours list
     *
     */
    public void addEnrageBehaviour() {
        if (this.getEnrageBehaviour() == null) {
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

    /**
     * Setting Lord Of Cinder enrage behaviour
     *
     *
     */
    public EnrageBehaviour getEnrageBehaviour() {
        return this.enrageBehaviour;
    }
}
