package game;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.enums.Abilities;

public class Bonfire extends Ground {
    public Bonfire(){
        super('B');
        this.addCapability(Abilities.REST);
    }

    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        Actions actions=new Actions();
        actions.add(new BonfireRestAction());
        return actions;
    }
}
