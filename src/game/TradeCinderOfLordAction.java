package game;

import edu.monash.fit2099.engine.*;
import game.enums.Status;

public class TradeCinderOfLordAction extends Action {
    Actor vendor;
    Item cinderOfLord;
    Item weaponItem;

    public TradeCinderOfLordAction(Actor vendor, Item cinderOfLord) {
        this.vendor = vendor;
        this.cinderOfLord = cinderOfLord;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        String result;

        if (cinderOfLord.hasCapability(Status.IS_YHORM)) {
            this.setWeaponItem(new GreatMachete(actor));
        }
        else if (cinderOfLord.hasCapability(Status.IS_ALDRICH)) {
            this.setWeaponItem(new GreatMachete(actor));
        }

        // removing cinder of lord
        actor.removeItemFromInventory(cinderOfLord);

        // swapping bought weapon with current weapon
        SwapWeaponAction swapWeaponAction = new SwapWeaponAction(this.getWeaponItem());
        swapWeaponAction.execute(actor, map);

        result = vendor + " traded " + this.getWeaponItem() + " to " + actor;

        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " trades " + cinderOfLord;
    }

    public void setWeaponItem(Item weaponItem) {
        this.weaponItem = weaponItem;
    }

    public Item getWeaponItem() {
        return weaponItem;
    }
}
