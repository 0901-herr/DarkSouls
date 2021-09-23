package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.enums.Abilities;
import game.enums.Status;

public class DyingAction extends Action {
//    Actor actor;
    private Location location;
    private int soul;
    private Location previousLocation;
    public DyingAction(Location location,int soul,Location previousLocation){
//        this.actor=actor;
        this.previousLocation=previousLocation;
        this.location=location;
        this.soul=soul;
    }
    @Override
    public String execute(Actor actor, GameMap map) {
        System.out.println("I'm Dead");
        map.moveActor(actor,map.at(38, 12));
        TokenOfSoul ts=new TokenOfSoul("Token Of Soul",'$',false,actor);
        ts.setSouls(soul);
        if (location.getGround().hasCapability(Status.IS_VALLEY)){
            previousLocation.addItem(ts);
        }
        else{
            location.addItem(ts);
        }
        ResetManager.getInstance().run();
        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }


}
