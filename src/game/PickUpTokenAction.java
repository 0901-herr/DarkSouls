package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class PickUpTokenAction extends Action {
    private TokenOfSoul tokenOfSoul;
    public PickUpTokenAction(TokenOfSoul tokenOfSoul) {
        this.tokenOfSoul=tokenOfSoul;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        map.locationOf(actor).removeItem(tokenOfSoul);
        tokenOfSoul.transferSouls(actor.asSoul());
        return actor.toString()+" picked up Token of Soul";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor.toString()+" pick up Token of Soul.";
    }
}
