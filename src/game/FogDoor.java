package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FogDoor extends Ground {
    World world;
    GameMap secMap;

    public FogDoor(){
        super('=');
    }
    public FogDoor(GameMap secMap) {
        super('=');
        this.secMap=secMap;
    }

    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        Location location1 = secMap.at(20,0);
        Actions actions=new Actions();
        actions.add(new MoveActorAction(location1,direction,"z"));
        return actions;
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        return true;
    }
}

