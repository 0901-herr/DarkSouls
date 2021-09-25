package game;

import edu.monash.fit2099.engine.*;
import game.enums.*;

import java.util.List;


public class StormRuler extends MeleeWeapon {

    private int numberOfCharge;
    private int MAX_NUMBER_OF_CHARGE = 3;
    private Actor actor;

    // constructor
    public StormRuler(Actor actor) {
        super("Storm Ruler", '7', 70, "strikes", 60);
        this.addCapability(Abilities.CRITICAL_STRIKE);
        this.criticalStrikeRate = 20;
        this.numberOfCharge = 0;
        this.actor = actor;
        if (!actor.hasCapability(Status.IS_PLAYER)){
            display.println(actor + " cannot equip " + this);
        }
    }

    @Override
    public int damage(){
        int damage = super.damage();
        if (this.hasCapability(Status.DULLNESS)){
            damage /= 2;
        }
        return damage;
    }

    @Override
    public List<Action> getAllowableActions() {
        Actions allowableActions = new Actions();
        if (this.hasCapability(Status.IS_PICKED_UP)) {
            if (!this.hasCapability(Status.FULLY_CHARGED) && !this.hasCapability(Status.CHARGING)){
                allowableActions.add(new ChargeAction(this));
            }
        }
        return allowableActions.getUnmodifiableActionList();
    }

    @Override
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
    public WeaponAction getActiveSkill(Actor target, String direction){

        // return WindSlashAction by Yhorm
        if (target.hasCapability(Status.IS_YHORM) && numberOfCharge == MAX_NUMBER_OF_CHARGE){
            return new WindSlashAction(this, target);
        }

        if (target.hasCapability(Status.WEAK_TO_STORMRULER)){
            this.removeCapability(Status.DULLNESS);
        }
        else{
            this.addCapability(Status.DULLNESS);
            display.println(this + "in DULLNESS STATE");
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
    public void tick(Location currentLocation, Actor actor) {
        if (this.hasCapability(Status.CHARGING)){
            numberOfCharge += 1;
            if (numberOfCharge == MAX_NUMBER_OF_CHARGE) {
                this.removeCapability(Status.CHARGING);
                this.addCapability(Status.FULLY_CHARGED);
                actor.removeCapability(Status.DISARMED);
            }
        }
    }

    @Override
    public String toString(){
        if (this.hasCapability(Status.CHARGING))
            return name + " (" + numberOfCharge + "/" + MAX_NUMBER_OF_CHARGE + ") " + " (CHARGING) ";
        else if (this.hasCapability(Status.FULLY_CHARGED))
            return name + " (" + numberOfCharge + "/" + MAX_NUMBER_OF_CHARGE + ") " + " (FULLY_CHARGED) ";
        else
            return name + " (" + numberOfCharge + "/" + MAX_NUMBER_OF_CHARGE + ") ";
    }

    public void resetCharge(){
        numberOfCharge = 0;
    }
}

