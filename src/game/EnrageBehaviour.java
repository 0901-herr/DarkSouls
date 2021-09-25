package game;

import edu.monash.fit2099.engine.*;
import game.enums.Status;
import game.interfaces.Behaviour;


/**
 * A Behaviour that is called when Yhorm HP < 50%
 *
 */
public class EnrageBehaviour implements Behaviour {

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
        Actions actions = new Actions();
        if (!isActivate) {

            // add WeaponAction
            for (Item item : actor.getInventory()) {
                if (item.asWeapon() != null) {
                    actions.add(item.getAllowableActions());
                }
            }

            isActivate = true;
            return actions.get(0);
        }
        return null;
    }
}