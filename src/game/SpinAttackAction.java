package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.ActorLocations;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.WeaponAction;
import edu.monash.fit2099.engine.WeaponItem;

public class SpinAttackAction extends WeaponAction {

    protected ActorLocations actorLocations = new ActorLocations();

    public SpinAttackAction(WeaponItem weaponItem){
        super(weaponItem);
    }

    @Override
    public String execute(Actor actor, GameMap map){
        String result = actor + weapon.verb();
        Location here = actorLocations.locationOf(actor);
        for (Exit exit: here.getExits()){
            Location destination = exit.getDestination();

            if (actorLocations.isAnActorAt(destination)){
                int damage = weapon.damage() / 2;
                Actor target = destination.getActor();
                target.hurt(damage);
                if(!target.isConscious()){
                    map.removeActor(target);
                    result += System.lineSeparator() + target + " is killed.";
                }
            }
        }

        return result;
    }

    @Override
    public String menuDescription(Actor actor){
        return actor + " activates " + this.weapon.toString();
    }

}
