package game;

import edu.monash.fit2099.engine.*;
import game.enums.Status;
import game.interfaces.Soul;

import java.util.HashMap;

public class Vendor extends Actor implements Soul {
    private HashMap<Item, Integer> items = new HashMap<>();

    public Vendor() {
        super("Fire Keeper", 'F', 0);
        items.put(new BroadSword(), 500);
        items.put(new GiantAxe(), 1000);
    }

    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
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

        // check if actor can buy items
        if (otherActor.hasCapability(Status.ABLE_TO_BUY)) {

            // get available weapons to buy
            for (HashMap.Entry<Item, Integer> item: this.getItems().entrySet()){
                actions.add(new BuyItemAction(this, item.getKey(), item.getValue()));
            }

            // add action tp buy increase maximum hp
            actions.add(new BuyIncreaseMaxHPAction(this));
        }

        return actions;
    }

    public HashMap<Item, Integer> getItems() {
        return items;
    }

    @Override
    public boolean addSouls(int souls) {
        boolean isSuccess = false;
        if (souls > 0) {
            setSouls(getSouls()+souls);
            isSuccess = true;
        }
        else {
            isSuccess = false;
        }
        return isSuccess;
    }
}
