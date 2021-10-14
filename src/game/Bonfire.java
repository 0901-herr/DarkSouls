package game;

import edu.monash.fit2099.engine.*;

import java.util.HashMap;

/**
 * The class that handle the Bonfire in the middle of the game map.
 * @see Ground
 */
public class Bonfire extends Ground {
    String name;
    Boolean isActivated;
    /**
     * Constructor
     *
     */
    public Bonfire(String name,Boolean isActivated){
        super('B');
        this.isActivated = isActivated;
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
        if (isActivated) {
            String[] hotKeyArr = {"a", "b", "c", "d", "e", "f"};
            int i = 1;
            Actions actions = new Actions();
            actions.add(new BonfireRestAction(name,this));
            HashMap<Location, Bonfire> bonfireHashMap = BonfireManager.getInstance().getBonfires();
            for (Location key : bonfireHashMap.keySet()) {
                if (!key.equals(location)) {
                    actions.add(new TeleportAction(key, bonfireHashMap.get(key).toString(),this));
                }
            }
            return actions;
        }
        else{
            Actions lightup = new Actions();
            lightup.add(new LightUpBonfireAction(this));
            return lightup;
        }
    }

    @Override
    public String toString() {
        return name;
    }

    public void activateBonfire(){
        isActivated=true;
    }
}
