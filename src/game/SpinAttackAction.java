package game;

import edu.monash.fit2099.engine.*;
import game.enums.Status;


public class SpinAttackAction extends WeaponAction {

    public SpinAttackAction(WeaponItem weaponItem){
        super(weaponItem);
    }

    @Override
    public String execute(Actor actor, GameMap map){

        int damage = weapon.damage() / 2;
        String result = actor + " uses Spin Attack for " + damage + " damage.";
        Location here = map.locationOf(actor);
        for (Exit exit: here.getExits()){
            Location destination = exit.getDestination();
            if (destination.containsAnActor()) {
                Actor target = destination.getActor();
                target.hurt(damage);
                if (!target.isConscious()) {
                    Actions dropActions = new Actions();
                    // drop all items
                    for (Item item : target.getInventory())
                        dropActions.add(item.getDropAction(actor));
                    for (Action drop : dropActions)
                        drop.execute(target, map);
                    // transfer souls
                    target.asSoul().transferSouls(actor.asSoul());
                    // remove player
                    if (target.hasCapability(Status.HOSTILE_TO_PLAYER)) {
                        map.removeActor(target);
                    }
                    result += System.lineSeparator() + target + " is killed.";
                }
            }
        }

        return result;
    }

    @Override
    public String menuDescription(Actor actor){
        return actor + " activates Spin Attack";
    }

}
