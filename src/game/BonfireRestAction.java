package game;

import edu.monash.fit2099.engine.*;

public class BonfireRestAction extends Action {
    public BonfireRestAction() {
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        ResetManager.getInstance().run();
        return actor+ " rest in Bonfire";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor.toString()+ " rest";
    }
}
