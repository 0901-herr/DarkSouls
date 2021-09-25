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
    }

    /**
     * Figure out what to do next.
     * @see edu.monash.fit2099.engine.Actor#playTurn(Actions, Action, GameMap, Display)
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {

        // when Yhorm hp < 50% max hp add EnrageBehaviour
        if (getHitPoints() < (getMaxHitPoints()*0.5)) {
            this.addCapability(Status.RAGE_MODE);
            addEnrageBehaviour();
        }

        // when Yhorm is stunned
        if (this.hasCapability(Status.STUNNED)){
            display.println(this + " is stunned");
            this.removeCapability(Status.STUNNED);
            return new DoNothingAction();
        }

        // loop through all behaviours
        for(Behaviour Behaviour : getBehaviours()) {
            Action action = Behaviour.getAction(this, map);
            if (action != null)
                return action;
        }

        return new DoNothingAction();
    }

    public void addEnrageBehaviour() {
        if (enrageBehaviour == null) {
            this.setEnrageBehaviour(new EnrageBehaviour());
            this.getBehaviours().add(0, enrageBehaviour);
        }
    }

    @Override
    public void resetInstance() {
        this.getBehaviours().clear();

        // remove FollowBehaviour
        this.setFollowBehaviour(null);

        // remove EnrageBehaviour
        this.setEnrageBehaviour(null);

        // reset location
        this.getInitialLocation().map().moveActor(this, this.getInitialLocation());
        this.hitPoints = this.getMaxHitPoints();
    }

    public void setEnrageBehaviour(EnrageBehaviour enrageBehaviour) {
        this.enrageBehaviour = enrageBehaviour;
    }
}
