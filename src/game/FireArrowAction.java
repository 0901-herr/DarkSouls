package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.WeaponAction;
import edu.monash.fit2099.engine.WeaponItem;
import game.enums.Abilities;

public class FireArrowAction extends WeaponAction {

    /**
     * Constructor
     * @param weaponItem the weapon item that has capabilities
     */
    public FireArrowAction(WeaponItem weaponItem){
        super(weaponItem);
    }

    @Override
    public String execute(Actor actor, GameMap map){
        String result = actor + " activates Fire Arrow";
        if (weapon.hasCapability(Abilities.FIRE)){
            weapon.removeCapability(Abilities.FIRE);
            actor.removeCapability(Abilities.FIRE);
            result = actor + " deactivates Fire Arrow";
        }
        else {
            weapon.addCapability(Abilities.FIRE);
            actor.addCapability(Abilities.FIRE);
        }
        return result;
    }

    @Override
    public String menuDescription(Actor actor){
        if (weapon.hasCapability(Abilities.FIRE)){
            return actor + " deactivates Fire Arrow";
        }
        else {
            return actor + " activates Fire Arrow";
        }
    }

}
