package game;

import edu.monash.fit2099.engine.*;
import game.interfaces.Behaviour;


/**
 * A class that figures out a MoveAction that will move the actor one step
 * closer to a target Actor.
 */
public class AttackBehaviour implements Behaviour {
    Action attackAction;

    /**
     * Constructor.
     *
     * @param attackAction the AttackAction
     */
    public AttackBehaviour(Action attackAction) {
        this.attackAction = attackAction;
    }

    @Override
    public Action getAction(Actor actor, GameMap map) {
        System.out.println(actor + " Attack Player");
        return attackAction;
    }
}