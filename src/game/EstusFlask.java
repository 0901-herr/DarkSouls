package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Item;
import game.interfaces.Resettable;

import java.util.List;

public class EstusFlask extends Item implements Resettable {

    private int MAX_CHARGE=3;
    private int charge;
    private int maxHitPoints;

    /**
     * Constructor
     * @param maxHitPoints the maximum hit points of actor
     */
    public EstusFlask(int maxHitPoints) {
        super("Estus Flask", 'e', false);
        this.charge = MAX_CHARGE;
        this.maxHitPoints = maxHitPoints;
        registerInstance();
    }

    /**
     * Getter of allowable actions of Estus Flask
     *
     * Returns an unmodifiable copy of the actions list so that calling methods won't
     * be able to change what this Item can do without the Item checking.
     * @return an unmodifiable list of Actions
     */
    @Override
    public List<Action> getAllowableActions() {
        Actions allowableActions = new Actions();
        allowableActions.add(new DrinkEstusFlaskAction(this));
        return allowableActions.getUnmodifiableActionList();
    }

    /**
     * Getter
     * @return an integer represents the number of charge of Estus Flask
     */
    public int getCharge() {
        return charge;
    }

    /**
     * Getter
     * @return an integer represents the maximum number of charge of Estus Flask
     */
    public int getMaxHitPoints(){
        return maxHitPoints;
    }

    /**
     * To substract to number of charge of Estus Flask
     * @param number an integer represents the number of charge that will be subtracted
     */
    public void subtractCharge(int number) {
        this.charge -= number;
    }

    /**
     * To reset the number of charge of Estus Flask
     */
    @Override
    public void resetInstance() {
        this.charge= MAX_CHARGE;
    }

    /**
     * A useful method to clean up the list of instances in the ResetManager class
     * @return the existence of the instance in the game.
     * for example, true to keep it permanent, or false if instance needs to be removed from the reset list.
     */
    @Override
    public boolean isExist() {
        return true;
    }

    @Override
    public String toString(){
        return name + " (" + charge + "/" + MAX_CHARGE + ") ";
    }
}
