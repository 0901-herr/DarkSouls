package game;

import edu.monash.fit2099.engine.*;

/**
 * Rest Action method to perform resting in Bonfire
 * @see Action
 */
public class BonfireRestAction extends Action {
    String name;
    public BonfireRestAction(String name){
        this.name=name;
    }
    /**
     * Execute method that execute the rest action when the BonfireRestAction is called.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String to indicate what currently execute.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        ResetManager.getInstance().run();
        return actor + " rest at Bonfire";
    }

    /**
     * Menu Description method.
     * @param actor The actor performing the action.
     * @return the string that will print in the console.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " rest at Firelink Shrine's Bonfire ";
    }
}
