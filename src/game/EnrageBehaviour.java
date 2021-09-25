package game;

import edu.monash.fit2099.engine.*;
import game.enums.Status;
import game.interfaces.Behaviour;


/**
 * A Behaviour that is called when Yhorm HP < 50%
 *
 */
public class EnrageBehaviour implements Behaviour {
    private Actor target;
    private String direction;
    private boolean isActivate;

    /**
     * Constructor.
     *
     //   * @param attackAction the AttackAction
     */
    public EnrageBehaviour() {
        this.isActivate = false;
    }

    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (!isActivate) {
            Location here = map.locationOf(actor);

            for (Exit exit : here.getExits()) {
                Location destination = exit.getDestination();
                if (destination.containsAnActor() && destination.getActor().hasCapability(Status.HOSTILE_TO_ENEMY)) {
                    target = destination.getActor();
                    direction = exit.getName();
                }
            }
            isActivate = true;
            return actor.getWeapon().getActiveSkill(target, direction);
        }

        return null;
    }
}