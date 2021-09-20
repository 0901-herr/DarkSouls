package game;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Weapon;
import game.enums.Abilities;

/**
 * Special Action for attacking other Actors.
 */
public class SellItemAction extends Action {

    /**
     * The Actor that is to be attacked
     */
    protected Actor target;

    /**
     * Constructor.
     *
     * @param target the Actor to attack
     */
    public SellItemAction(Actor target) {
        this.target = target;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        String result;

        Actions dropActions = new Actions();
        // drop all items
        for (Item item : target.getInventory())
            dropActions.add(item.getDropAction(actor));
        for (Action drop : dropActions)
            drop.execute(target, map);

        // TODO: Transfer souls
        System.out.println(actor + " transferring Souls to " + target);
        actor.asSoul().transferSouls(target.asSoul());

        result = actor + "sold ";

        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " sold " + " to " + target;
    }
}
