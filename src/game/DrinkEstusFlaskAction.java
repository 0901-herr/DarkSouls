package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.enums.Abilities;

/**
 * The action class to handle drinking of Estus Flask.
 * @see Action
 */
public class DrinkEstusFlaskAction extends Action {

    private EstusFlask estusFlask;

    /**
     * Constructor
     * @param estusFlask
     * Bind the parameter with the class variable.
     */
    public DrinkEstusFlaskAction(EstusFlask estusFlask) {
        this.estusFlask = estusFlask;
    }

    /**
     * Execute method that handle the checking of charges of Estus Flask and healing action.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String to indicate the current Action that being executed.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String result = actor + " cannot drink " + estusFlask;
        if (actor.hasCapability(Abilities.DRINK)) {
            if (estusFlask.getCharge() == 0) {
                result = estusFlask + " has no charge";
            }
            else {
                actor.heal((int)(estusFlask.getMaxHitPoints()*0.4));
                estusFlask.subtractCharge(1);
                result = actor + " drank " + estusFlask;
            }
        }
        return result;
    }

    /**
     * Estus Flask Menu Description method
     * @param actor The actor performing the action.
     * @return String that being show in the menu to allow user to choose.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " drinks " + estusFlask;
    }
}
