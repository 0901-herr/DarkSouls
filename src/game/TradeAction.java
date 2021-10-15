package game;

import edu.monash.fit2099.engine.*;
import game.enums.Status;

import java.util.HashMap;

/**
 * The action to be performed when trading items with Vendor
 *
 */
public class TradeAction extends Action {
    private Actor vendor;
    private Item item;
    private Item returnItem;

    /**
     * Constructor.
     *
     * @param vendor the Actor to sell, trade items
     * @param item the item that is used for trading
     */
    public TradeAction(Actor vendor, Item item) {
        this.vendor = vendor;
        this.item = item;
    }

    /**
     * Perform the trading action.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return A description of the result of the trading.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String result;

        // check the type of item that is being traded
        if (item.hasCapability(Status.IS_YHORM)) {
            this.setReturnItem(new GreatMachete(actor));
        }
        else if (item.hasCapability(Status.IS_ALDRICH)) {
            this.setReturnItem(new Longbow(actor));
        }

        // removing item that is used to trade
        vendor.addItemToInventory(item);
        actor.removeItemFromInventory(item);

        // swapping traded weapon with current weapon
        SwapWeaponAction swapWeaponAction = new SwapWeaponAction(this.getReturnItem());
        swapWeaponAction.execute(actor, map);

        result = vendor + " traded " + this.getReturnItem() + " to " + actor;

        return result;
    }

    /**
     * Returns a descriptive string
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " trades " + item;
    }

    /**
     * Setting the trading item
     *
     * @param item the item to set
     */
    public void setReturnItem(Item item) {
        this.returnItem = item;
    }

    /**
     * Getting the trading item
     *
     * @return the traded item
     */
    public Item getReturnItem() {
        return returnItem;
    }
}
