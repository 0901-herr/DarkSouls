package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.WeaponAction;
import edu.monash.fit2099.engine.WeaponItem;
import game.enums.Abilities;
import game.enums.Status;

public class BurnGroundAction extends WeaponAction {

    public BurnGroundAction(WeaponItem weaponItem){
        super(weaponItem);
    }


    @Override
    public String execute(Actor actor, GameMap map){
        String result = actor + " uses Burn Ground.";
        Location here = map.locationOf(actor);
        for (Exit exit: here.getExits()){
            Location destination = exit.getDestination();

            if (destination.getGround().hasCapability(Abilities.BURN)){
                destination.setGround(new BurnedGround(destination.getGround()));
            }
        }
        return result;
    }

    @Override
    public String menuDescription(Actor actor){
        return actor + " activates Burn Ground.";
    }
}
