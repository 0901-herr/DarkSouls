package game;

import edu.monash.fit2099.engine.*;
import game.enums.Status;

public class WindSlashAction extends WeaponAction {

    protected StormRuler weapon;
    protected Actor target;

    public WindSlashAction(StormRuler stormRuler, Actor target){
        super(stormRuler);
        this.weapon = stormRuler;
        this.target = target;
    }

    @Override
    public String execute(Actor actor, GameMap map){
        int damage = weapon.damage() * 2;
        target.hurt(damage);
        String result = actor + " uses Wind Slash on " + target + " for " + damage + " damage and stuns " + target;
        if (!target.isConscious()) {
            DyingAction dyingAction = new DyingAction(map.locationOf(actor),actor.asSoul().getSouls(),null,target,null,null);
            dyingAction.execute(actor,map);
            result += System.lineSeparator() + target + " is killed.";
        }
        // stun
        target.addCapability(Status.STUNNED);

        // reset charge
        weapon.resetCharge();
        weapon.removeCapability(Status.FULLY_CHARGED);

        return result;
    }

    @Override
    public String menuDescription(Actor actor){
        return actor + " activates Wind Slash";
    }

}

