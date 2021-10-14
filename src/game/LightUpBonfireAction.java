package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;


import java.util.HashMap;

public class LightUpBonfireAction extends Action {
    Bonfire bonfire;
    public LightUpBonfireAction(Bonfire bonfire) {
        this.bonfire=bonfire;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        BonfireManager bm = BonfireManager.getInstance();
        HashMap<Location,Bonfire> bonfires= bm.getBonfires();
        for (Location key: bonfires.keySet()){
            if (bonfires.get(key)==bonfire){
                bm.updateLastInteract(key.map().at(key.x(),key.y()+1));
            }
        }
        bonfire.activateBonfire();
        return "Bonfire lighten";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Enlight Bonfire";
    }
}
