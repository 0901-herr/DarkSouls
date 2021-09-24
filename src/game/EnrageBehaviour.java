package game;

import edu.monash.fit2099.engine.*;
import game.enums.Status;
import game.interfaces.Behaviour;


/**
 * A Behaviour that is called when Yhorm HP < 50%
 *
 */
public class EnrageBehaviour implements Behaviour {
    private Actions actions = new Actions();
    private Actor target;
    private String direction;
    private boolean isEnraged;
    private boolean isActivate;

    /**
     * Constructor.
     *
     //   * @param attackAction the AttackAction
     */
    public EnrageBehaviour(boolean isEnraged) {
        this.isEnraged = isEnraged;
        this.isActivate = false;
    }

    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (isEnraged && !isActivate) {
            System.out.println("Yhorm Call EnrageBehaviour");
            Location here = map.locationOf(actor);

//            for (Item item: actor.getInventory()) {
//                if (item.asWeapon() != null) {
//                    return item.getAllowableActions().get(0);
//                }
//            }

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
        else if (!isEnraged) {
            // if Yhorm is reset
            // isEnrage is reset to false
            // isActivate is also reset to false
            isActivate = false;
        }

        return null;
    }
}