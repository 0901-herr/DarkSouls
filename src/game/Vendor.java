package game;

import edu.monash.fit2099.engine.*;
import game.enums.Status;
import game.interfaces.Soul;

public class Vendor extends Actor implements Soul {
    public Vendor(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
    }

    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        for (Action action: actions) {
            if (action instanceof SellItemAction) {
                return action;
            }
        }

        return new DoNothingAction();
    }

    /**
     * At the moment, we only make it can be attacked by enemy that has HOSTILE capability
     * You can do something else with this method.
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return list of actions
     * @see Status#HOSTILE_TO_ENEMY
     */
    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions actions = new Actions();
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this,direction));
        }
        return actions;
    }

    @Override
    public void transferSouls(Soul soulObject) {
        //TODO: transfer Player's souls to another Soul's instance.
    }
}
