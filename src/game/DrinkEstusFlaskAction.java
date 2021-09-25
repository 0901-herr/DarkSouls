package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * The action class to handle drinking of Estus Flask.
 * @see Action
 */
public class DrinkEstusFlaskAction extends Action {
    EstusFlask ef;
    int healValue;

    /**
     * Constructor
     * @param ef
     * @param healValue
     * Bind the parameter with the class variable.
     */
    public DrinkEstusFlaskAction(EstusFlask ef, int healValue) {
        this.ef=ef;
        this.healValue = healValue;
    }

    /**
     * Execute method that handle the checking of charges of Estus Flask and healing action.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String to indicate the current Action that being executed.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (ef.getCharge()==0){
        return null;
        }
        else{
            actor.heal(this.healValue);
            ef.setCharge(ef.getCharge()-1);
            return actor.toString()+" drank Estus Flask";
        }
    }

    /**
     * Menu Description method
     * @param actor The actor performing the action.
     * @return String that being show in the menu to allow user to choose.
     */
    @Override
    public String menuDescription(Actor actor) {
        return String.format("%s drink Estus Flask (%d/%d)",actor.toString(),ef.getCharge(),ef.getMAX_CHARGE());
    }
}
