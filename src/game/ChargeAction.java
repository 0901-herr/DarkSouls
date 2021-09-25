package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.WeaponAction;
import edu.monash.fit2099.engine.WeaponItem;
import game.enums.Status;

public class ChargeAction extends WeaponAction{

    public ChargeAction(WeaponItem weaponItem){
        super(weaponItem);
    }

    @Override
    public String execute(Actor actor, GameMap map){
        weapon.addCapability(Status.CHARGING);
        actor.addCapability(Status.DISARMED);

        String result = actor + " charging " + weapon + ".";
        return result;
    }

    @Override
    public String menuDescription(Actor actor){
        return actor + " charges " + weapon;
    }
}
