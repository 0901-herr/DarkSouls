package game;

import edu.monash.fit2099.engine.*;
import game.enums.Abilities;

import java.util.HashMap;

/**
 * The class that handle the Bonfire in the middle of the game map.
 * @see Ground
 */
public class Bonfire extends Ground {
    String name;
    /**
     * Constructor
     *
     */
    public Bonfire(String name){
        super('B');
        this.name=name;
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
        String [] hotKeyArr ={"a","b","c","d","e","f"};
        int i=1;
        Actions actions=new Actions();
        actions.add(new BonfireRestAction(name));
        HashMap<Location,Bonfire>bonfireHashMap= BonfireManager.getInstance().getBonfires();
        for (Location key: bonfireHashMap.keySet()){
            System.out.println(bonfireHashMap.get(key).toString() + "1");
            if(!key.equals(location)){
                actions.add(new MoveActorAction(key,bonfireHashMap.get(key).toString(),hotKeyArr[i++]));
            }
        }
        return actions;
    }

    @Override
    public String toString() {
        return name;
    }
}
