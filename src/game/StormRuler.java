package game;

import edu.monash.fit2099.engine.*;
import game.enums.*;
import game.interfaces.Chargeable;

import java.util.List;


public class StormRuler extends MeleeWeapon implements Chargeable {

    private int numberOfCharge;
    private final int maxNumberOfCharge = 3;
    public boolean isPickedUp;

    // constructor
    public StormRuler() {
        super("Storm Ruler", '7', 70, "strikes", 60);
        this.addCapability(Abilities.CRITICAL_STRIKE);
        this.criticalStrikeRate = 20;
        this.numberOfCharge = 0;
        this.isPickedUp = false;
    }

    @Override
    public List<Action> getAllowableActions() {
        Actions allowableActions = new Actions();
        if (isPickedUp) {
            if (numberOfCharge < maxNumberOfCharge) {
                allowableActions.add(new ChargeAction(this));
            } else if (numberOfCharge == maxNumberOfCharge) {
                allowableActions.add(new WindSlashAction(this));
            }
        }
        return allowableActions.getUnmodifiableActionList();
    }

    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        if(actor.hasCapability(Status.IS_PLAYER)) {
            return new SwapWeaponAction(this);
        }
        return null;
    }

    @Override
    public int numberOfCharge() {
        return numberOfCharge;
    }

    @Override
    public int maxNumberOfCharge() {
        return maxNumberOfCharge;
    }

    @Override
    public void add(int number) {
        numberOfCharge += number;
    }

    @Override
    public void subtract(int number) {
        numberOfCharge += number;
    }

    @Override
    public void resetCharge(){
        numberOfCharge = 0;
    }

    @Override
    public String toString(){
        return name + " (" + numberOfCharge + "/" + maxNumberOfCharge + ") ";
    }
}

