package game;

import edu.monash.fit2099.engine.*;
import game.enums.Status;
import game.interfaces.Behaviour;

import java.util.Random;


/**
 * A class that figures out a MoveAction that will move the actor one step
 * closer to a target Actor.
 */
public class AttackBehaviour implements Behaviour {
    private Actor target;
    private Actions actions = new Actions();
    Random rand = new Random();

    /**
     * Constructor.
     *
     */
    public AttackBehaviour() {}

    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location here = map.locationOf(actor);

        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();

            if (destination.containsAnActor() && destination.getActor().hasCapability(Status.IS_PLAYER)) {
                target = destination.getActor();
                actions.add(new AttackAction(target, exit.getName()));

                // get inventory of enemy
                for (Item item : actor.getInventory()) {
                    actions.add(item.getAllowableActions());
                }

                // randomly selects actions of the enemy
                int randInt = rand.nextInt(actions.size());

                return actions.get(randInt);
            }
        }

        return null;
    }
}