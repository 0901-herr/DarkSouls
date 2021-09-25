package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;


/**
 * Special Action for Buying items from Vendor.
 */
public class BuyItemAction extends Action {

    /**
     * Action for actor to buy items from vendor
     */
    protected Actor vendor;
    private Item item;
    private int requiredSouls;

    /**
     * Constructor.
     *
     * @param vendor the Actor to attack
     */
    public BuyItemAction(Actor vendor, Item item, int requiredSouls) {
        this.setVendor(vendor);
        this.setItem(item);
        this.setRequiredSouls(requiredSouls);
    }

    public BuyItemAction(Actor vendor, int requiredSouls) {
        this.setVendor(vendor);
        this.setRequiredSouls(requiredSouls);
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        String result;

        // check if buyer has enough souls
        if (actor.asSoul().getSouls() < this.getRequiredSouls()) {
            result = actor + " does not have enough souls to buy " + item;
            return result;
        }
        else {
            // set required souls to transfer
            actor.asSoul().transferSouls(this.getVendor().asSoul(), requiredSouls);
        }

        // swapping bought weapon with current weapon
        SwapWeaponAction swapWeaponAction = new SwapWeaponAction(item);
        swapWeaponAction.execute(actor, map);

        result = vendor + " sold " + getItem() + " to " + actor;

        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " buys " + this.getItem() + " (" + this.getRequiredSouls() + " souls)";
    }

    public void setVendor(Actor vendor) {
        this.vendor = vendor;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void setRequiredSouls(int souls) {
        this.requiredSouls = souls;
    }

    public Actor getVendor() {
        return vendor;
    }

    public Item getItem() {
        return item;
    }

    public int getRequiredSouls() {
        return requiredSouls;
    }
}




