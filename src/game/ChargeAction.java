package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.WeaponAction;
import edu.monash.fit2099.engine.WeaponItem;
import game.enums.Status;
import game.interfaces.Chargeable;


public class ChargeAction extends WeaponAction{

    protected Chargeable weapon;

    public ChargeAction(Chargeable chargeableWeapon){
        super((WeaponItem) chargeableWeapon);
        this.weapon = chargeableWeapon;
    }


    @Override
    public String execute(Actor actor, GameMap map){
        weapon.add(1);
        actor.addCapability(Status.DISARMED);
        String result = actor + " charged " + weapon + ".";
        return result;
    }

    @Override
    public String menuDescription(Actor actor){
        return actor + " charges " + weapon;
    }

}
