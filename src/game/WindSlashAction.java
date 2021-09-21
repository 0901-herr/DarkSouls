package game;

import edu.monash.fit2099.engine.*;

public class WindSlashAction extends WeaponAction {

    protected Actor target;
    protected String direction;

    public WindSlashAction(WeaponItem weaponItem, Actor target, String direction){
        super(weaponItem);
        this.target = target;
        this.direction = direction;
    }

    // x2 damage
    // 100% hit rate (wait)
    // reset charge (wait)
    // stun (wait)
    @Override
    public String execute(Actor actor, GameMap map){

        int damage = weapon.damage() * 2;
        String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
        target.hurt(damage);
        if (!target.isConscious()) {
            Actions dropActions = new Actions();
            // drop all items
            for (Item item : target.getInventory())
                dropActions.add(item.getDropAction(actor));
            for (Action drop : dropActions)
                drop.execute(target, map);
            // remove actor
            //TODO: In A1 scenario, you must not remove a Player from the game yet. What to do, then?
            map.removeActor(target);
            result += System.lineSeparator() + target + " is killed.";
        }

        return result;
    }

    @Override
    public String menuDescription(Actor actor){
        return actor + " activates " + this;
    }

}

