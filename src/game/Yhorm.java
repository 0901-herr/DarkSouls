package game;

import edu.monash.fit2099.engine.*;
import game.enums.Status;
import game.interfaces.Behaviour;
import game.interfaces.Soul;

import java.util.ArrayList;

/**
 * An undead minion.
 */
public class Yhorm extends LordOfCinder {
    private EnrageBehaviour enrageBehaviour;

    /**
     * Constructor.
     * @param name the name of Yhorm
     */
    public Yhorm(String name, char displayChar, int hitPoints, Location initialLocation) {
        super(name, displayChar, hitPoints, initialLocation, 5000);
        this.addCapability(Status.IS_YHORM);
        this.addCapability(Status.WEAK_TO_STORMRULER);
        this.addItemToInventory(new GreatMachete(this));
    }

    /**
     * Figure out what to do next.
     * @see edu.monash.fit2099.engine.Actor#playTurn(Actions, Action, GameMap, Display)
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {

        // when Yhorm is stunned
        if (this.hasCapability(Status.STUNNED)){
            display.println(this + " is stunned");
            this.removeCapability(Status.STUNNED);
            return new DoNothingAction();
        }

        // when Yhorm hp < 50% max hp add EnrageBehaviour
        if (getHitPoints() < (getMaxHitPoints()*0.5)) {
            this.addCapability(Status.RAGE_MODE);
            display.println(this + " is in RAGE MODE, hit rate increases");
            addEnrageBehaviour();
        }

        // loop through all behaviours
        return super.playTurn(actions, lastAction, map, display);
    }

    public void addEnrageBehaviour() {
        if (enrageBehaviour == null) {
            this.getBehaviours().add(0, new EnrageBehaviour());
        }
    }

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

    public void setEnrageBehaviour(EnrageBehaviour enrageBehaviour) {
        this.enrageBehaviour = enrageBehaviour;
    }
}
