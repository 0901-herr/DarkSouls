package game;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.enums.Abilities;

public class BurnedGround extends Ground {

    public BurnedGround(){
        super('v');
    }

    @Override
    public void tick(Location location){
        if (location.containsAnActor()){
            if (location.getActor().hasCapability(Abilities.BURN)) {
                location.getActor().hurt(25);
            }
        }
    }
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        if (location.containsAnActor()){
            if (actor.hasCapability(Abilities.BURN)){
                actor.hurt(25);
            }
        }
        return new Actions();
    }
}
