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
                if (!target.isConscious()&&!target.hasCapability(Status.IS_PLAYER)) {
                    DyingAction dyingAction = new DyingAction(map.locationOf(actor),actor.asSoul().getSouls(),null,target,null,null);
                    dyingAction.execute(actor,map);
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
