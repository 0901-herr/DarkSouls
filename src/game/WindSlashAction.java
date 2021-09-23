package game;

import edu.monash.fit2099.engine.*;
import game.enums.Status;

public class WindSlashAction extends WeaponAction {

    protected StormRuler weapon;

    public WindSlashAction(WeaponItem weaponItem){
        super(weaponItem);
        this.weapon = (StormRuler) weaponItem;
    }

    // x2 damage
    // 100% hit rate
    // reset charge
    // stun
    @Override
    public String execute(Actor actor, GameMap map){
        String result = "No Yhorm nearby.";
        int damage = weapon.damage() * 2;
        Location here = map.locationOf(actor);
        for (Exit exit: here.getExits()){
            Location destination = exit.getDestination();
            if (destination.containsAnActor()) {
                if (destination.getActor().hasCapability(Status.IS_YHORM)) {
                    Actor target = destination.getActor();
                    target.hurt(damage);
                    result = actor + " uses Wind Slash on " + target + " for " + damage + " damage.";
                    if (!target.isConscious()) {
                        DyingAction dyingAction = new DyingAction(map.locationOf(actor),actor.asSoul().getSouls(),null,target);
                        dyingAction.execute(actor,map);
                        result += System.lineSeparator() + target + " is killed.";
                    }
                    // stun
                    target.addCapability(Status.STUNNED);
                    break;
                }
            }
            // reset charge
            weapon.resetCharge();
        }

        return result;
    }

    @Override
    public String menuDescription(Actor actor){
        return actor + " activates Wind Slash";
    }

}

