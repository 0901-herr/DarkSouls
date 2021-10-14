package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.MoveActorAction;

import java.util.HashMap;

public class TeleportAction extends MoveActorAction {
    Bonfire bonfire;
    public TeleportAction(Location moveToLocation, String direction,Bonfire bonfire) {
        super(moveToLocation, direction);
        this.bonfire=bonfire;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        BonfireManager bm = BonfireManager.getInstance();
        bm.updateLastInteract(moveToLocation.map().at(moveToLocation.x(), moveToLocation.y()+1));
        return super.execute(actor, map);
    }
}
