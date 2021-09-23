package game;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.enums.Status;

public class BurnedGround extends Ground {

    private int round = 0;
    private Ground previousGround;

    public BurnedGround(Ground previousGround){
        super('v');
        this.previousGround = previousGround;
    }

    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        if (location.containsAnActor()){
            if (!actor.hasCapability(Status.IMMUNE_TO_BURN)){
                actor.hurt(25);
            }
        }
        return new Actions();
    }

    @Override
    public void tick(Location location){
        super.tick(location);

        round++;
        if (round == 4){
            location.setGround(previousGround);
        }
    }

    public void resetRound(){
        round = 0;
    }
}
