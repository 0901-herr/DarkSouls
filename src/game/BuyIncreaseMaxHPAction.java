package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class BuyIncreaseMaxHPAction extends BuyItemAction {
    private int increaseValue;

    public BuyIncreaseMaxHPAction(Actor vendor) {
        super(vendor, 200);
        setIncreaseValue(25);
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        String result;

        // check if buyer has enough souls
        if (actor.asSoul().getSouls() < getRequiredSouls()) {
            result = actor + " does not have enough souls to increase maximum HP";
            return result;
        }
        else {
            actor.asSoul().transferSouls(vendor.asSoul(), this.getRequiredSouls());
        }

        actor.increaseMaxHp(25);

        result = actor + " maximum HP increase by 25";

        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " buys Max HP modifier (+" + getIncreaseValue() + "HP): "+ getRequiredSouls() + " souls";
    }

    public int getIncreaseValue() {
        return increaseValue;
    }

    public void setIncreaseValue(int increaseValue) {
        this.increaseValue = increaseValue;
    }
}