package game;

import edu.monash.fit2099.engine.*;
import game.enums.Status;
import game.interfaces.Soul;

public class Vendor extends Actor implements Soul {
    int souls = 0;

    public Vendor() {
        super("Fire Keeper", 'F', 0);
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
        if (otherActor.hasCapability(Status.ABLE_TO_BUY)) {
            actions.add(new BuyBroadSwordAction(this));
            actions.add(new BuyGiantAxeAction(this));
            actions.add(new BuyIncreaseMaxHPAction(this));
        }
        return actions;
    }

    @Override
    public void transferSouls(Soul soulObject) {}

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

    @Override
    public void setSouls(int souls) {
        this.souls = souls;
    }

    @Override
    public int getSouls() {
        return souls;
    }
}
