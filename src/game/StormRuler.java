package game;

import edu.monash.fit2099.engine.*;
import game.enums.*;
import game.interfaces.Chargeable;

import java.util.List;


public class StormRuler extends MeleeWeapon {

    private int numberOfCharge;
    private final int maxNumberOfCharge = 3;

    // constructor
    public StormRuler() {
        super("Storm Ruler", '7', 70, "strikes", 60);
        this.addCapability(Abilities.CRITICAL_STRIKE);
        this.criticalStrikeRate = 20;
        this.numberOfCharge = 0;
    }

    //TODO: disarmed, dullness
    @Override
    public List<Action> getAllowableActions() {
        Actions allowableActions = new Actions();
        if (this.hasCapability(Status.IS_PICKED_UP)) {
            if (this.hasCapability(Status.CHARGING)){
                if (numberOfCharge == maxNumberOfCharge) {
                    this.removeCapability(Status.CHARGING);
                    this.addCapability(Status.FULLY_CHARGED);
                }
                else {
                    numberOfCharge += 1;
                }
            }
            else if (!this.hasCapability(Status.FULLY_CHARGED)){
                allowableActions.add(new ChargeAction(this));
            }
        }
        return allowableActions.getUnmodifiableActionList();
    }

    /**
     * Get an action or skill from the weapon that will be used against one target.
     * This method allows weapon instance to interact with Actor class.
     * It can be used to have actionable special attack, heal, silence, etc. to a target
     *
     * @see WeaponItem#allowableActions for a self-direction skill instead of using this method (recommendation)
     * @param target the target actor
     * @param direction the direction of target, e.g. "north"
     * @return null by default because a weapon doesn't have any active skill. Otherwise, return a WeaponAction instance.
     */
    @Override
    public WeaponAction getActiveSkill(Actor target, String direction){
        if (target.hasCapability(Status.IS_YHORM) && numberOfCharge == maxNumberOfCharge){
            return new WindSlashAction(this, target);
        }
        return null;
    }

    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        if(actor.hasCapability(Status.IS_PLAYER)) {
            return new SwapWeaponAction(this);
        }
        return null;
    }

    @Override
    public String toString(){
        return name + " (" + numberOfCharge + "/" + maxNumberOfCharge + ") ";
    }

    public void resetCharge(){
        numberOfCharge = 0;
    }
}

