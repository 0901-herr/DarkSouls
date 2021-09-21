package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.WeaponAction;
import edu.monash.fit2099.engine.WeaponItem;


public class ChargeAction extends WeaponAction{

    public ChargeAction(WeaponItem weaponItem){
        super(weaponItem);
    }

    // charge
    // disarmed (wait)
    @Override
    public String execute(Actor actor, GameMap map){
        String result = actor + "charges" + weapon;
        if (((StormRuler)this.weapon).numberOfCharge <  ((StormRuler)this.weapon).maxNumberOfCharge){
            ((StormRuler) this.weapon).numberOfCharge += 1;
        }
        return result;
    }

    @Override
    public String menuDescription(Actor actor){
        return actor + " activates " + this;
    }

}
