package game;

import edu.monash.fit2099.engine.*;
import game.enums.Status;
import game.interfaces.Behaviour;

import java.util.ArrayList;
import java.util.Random;


/**
 * A class that figures out which AttackAction or WeaponAction of to
 * the actor to perform.
 */
public class AttackBehaviour implements Behaviour {
    private Actor target;
    private Actions actions = new Actions();

    Random rand = new Random();

    /**
     * Getting the actions of the actor that can perform attack
     *
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return an Action that actor can perform, or null if actor can't do this.
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location here = map.locationOf(actor);

        if (!actor.hasCapability(Status.IS_ALDRICH)) {
            // search exits of the actor to find the target
            for (Exit exit : here.getExits()) {
                Location destination = exit.getDestination();

                if (destination.containsAnActor() && destination.getActor().hasCapability(Status.IS_PLAYER)) {
                    target = destination.getActor();
                    actions.add(new AttackAction(target, exit.getName()));

                    // add actions from the item of the inventory
                    for (Item item : actor.getInventory()) {
                        actions.add(item.getAllowableActions());
                    }

                    // randomly selects a action to return
                    int randInt = rand.nextInt(actions.size());

                    return actions.get(randInt);
                }
            }
        }
        // actor is Aldrich, add range weapon actions
        else {
            actions = new Actions();

            // search for player in expanded range
            for (Item item : actor.getInventory()) {
                actions.add(item.getAllowableActions());
            }

            // randomly selects a action to return
            if (actions.size() > 0) {
                System.out.println("get attack action");
                return actions.get(0);
            }
        }

        return null;
    }
}