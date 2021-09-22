package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

public class DyingAction extends Action {
    Actor actor;
    Location location;
    int soul;
    public DyingAction( Actor actor,Location location,int soul){
        this.actor=actor;
        this.location=location;
        this.soul=soul;
    }
    @Override
    public String execute(Actor actor, GameMap map) {
        System.out.println("I'm Dead");
        map.moveActor(actor,map.at(38, 12));
        TokenOfSoul ts=new TokenOfSoul("Token Of Soul",'$',false,actor);
        ts.setSouls(soul);
        location.addItem(ts);
        ResetManager.getInstance().run();
        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }


}
