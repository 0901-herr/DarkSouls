package game;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.enums.Abilities;

/**
 * The class that handle the Bonfire in the middle of the game map.
 * @see Ground
 */
public class Bonfire extends Ground {
    /**
     * Constructor
     *
     */
    public Bonfire(){
        super('B');
    }

    /**
     * This method return the allowable action that player can select from menu to perform Rest action
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return BonfireRestAction
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        Actions actions=new Actions();
        actions.add(new BonfireRestAction());
        return actions;
    }
}
