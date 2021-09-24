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
    private boolean isEnraged = false;

    /**
     * Constructor.
     * @param name the name of Yhorm
     */
    public Yhorm(String name, char displayChar, int hitPoints, Location initialLocation) {
        super(name, displayChar, hitPoints, initialLocation);
        registerInstance();
    }

    /**
     * Figure out what to do next.
     * @see edu.monash.fit2099.engine.Actor#playTurn(Actions, Action, GameMap, Display)
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        if (getIsReset()) {
            setReset(false);
            return new DoNothingAction();
        }

        // when Yhorm hp < 50% max hp add EnrageBehaviour
        if (getHitPoints() < (getMaxHitPoints()*0.5)) {
            this.addCapability(Status.RAGE_MODE);
            addEnrageBehaviour();
        }

        // loop through all behaviours
        for(Behaviour Behaviour : getBehaviours()) {
            Action action = Behaviour.getAction(this, map);
            if (action != null)
                return action;
        }
        return new DoNothingAction();
    }

    /**
     * At the moment, we only make it can be attacked by enemy that has HOSTILE capability
     * You can do something else with this method.
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return list of actions
     * @see Status#HOSTILE_TO_ENEMY
     */
    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions actions = new Actions();

        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            // add behaviours for Undead
            addBehaviour(otherActor);

            // AttackAction to Player
            actions.add(new AttackAction(this, direction));
        }

        return actions;
    }

    public void addBehaviour(Actor otherActor) {
        if (getFollowBehaviour() == null) {
            // Add AttackBehaviour
            getBehaviours().add(0, new AttackBehaviour());

            // add FollowBehaviour
            setFollowBehaviour(new FollowBehaviour(otherActor));
            getBehaviours().add(1, getFollowBehaviour());
        }
    }

    public void addEnrageBehaviour() {
        if (!isEnraged) {
            isEnraged = true;
            getBehaviours().add(0, new EnrageBehaviour(isEnraged));
        }
    }

    @Override
    public void resetInstance() {
        // remove FollowBehaviour
        setFollowBehaviour(null);

        // reset location
        getInitialLocation().map().moveActor(this, getInitialLocation());
        this.hitPoints = getMaxHitPoints();

        // reset burn action
        isEnraged = false;
    }
}
