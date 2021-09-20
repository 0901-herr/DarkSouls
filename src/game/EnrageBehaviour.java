package game;

import edu.monash.fit2099.engine.*;
import game.interfaces.Behaviour;


/**
 * A Behaviour that is called when Yhorm HP < 50%
 *
 */
public class EnrageBehaviour implements Behaviour {
    /**
     * Constructor.
     *
//     * @param attackAction the AttackAction
     */
    public EnrageBehaviour() {}

    @Override
    public Action getAction(Actor actor, GameMap map) {
        System.out.println("Yhorm Call EnrageBehaviour");

        // Call EmberForm action (Weapon)

        // Call burn ground action
        // Change surroundings to fire "v"
        // "v" is only called in Dirt
        // "v" should call AttackAction on the Player

        // Return EmberFormAction
        return new DoNothingAction();
    }
}