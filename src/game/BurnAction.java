package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.ActorLocations;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.WeaponAction;
import edu.monash.fit2099.engine.WeaponItem;
import game.enums.Abilities;

public class BurnAction extends WeaponAction {

    ActorLocations actorLocations = new ActorLocations();

    public BurnAction(WeaponItem weaponItem){
        super(weaponItem);
    }

    // burn surrounding
    // 3 rounds (wait)
    // dirt
    @Override
    public String execute(Actor actor, GameMap map){
        String result = actor + weapon.verb();
        Location here = actorLocations.locationOf(actor);
        for (Exit exit: here.getExits()){
            Location destination = exit.getDestination();

            if (destination.getGround().hasCapability(Abilities.BURN)){
                destination.setGround(new BurnedGround());
            }
        }

        return result;
    }

    @Override
    public String menuDescription(Actor actor){
        return actor + " activates " + this;
    }
}
