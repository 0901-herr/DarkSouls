package game;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Weapon;
import game.enums.Abilities;
import edu.monash.fit2099.engine.WeaponItem;
import edu.monash.fit2099.engine.Ground;
import game.enums.Status;

/**
 * Special Action for Buying items from Vendor.
 */
public class BuyItemAction extends Action {

    /**
     * The Actor that is to be attacked
     */
    protected Actor vendor;
    private WeaponItem weaponItem;
    private int requiredSouls;
    private SwapWeaponAction swapWeaponAction;

    /**
     * Constructor.
     *
     * @param vendor the Actor to attack
     */
    public BuyItemAction(Actor vendor, WeaponItem weapon, int souls) {
        this.vendor = vendor;
        setWeapon(weapon);
        setRequiredSouls(souls);
    }

    public BuyItemAction(Actor vendor, int souls) {
        this.vendor = vendor;
        setRequiredSouls(souls);
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        String result;

        // check if buyer has enough souls
        if (actor.asSoul().getSouls() < getRequiredSouls()) {
            result = actor + " does not have enough souls to buy " + weaponItem;
            return result;
        }
        else {
            actor.asSoul().setRequiredSouls(getRequiredSouls());
            actor.asSoul().transferSouls(vendor.asSoul());
        }

        // get current equip weapon and remove it

        swapWeaponAction = new SwapWeaponAction(weaponItem);
        swapWeaponAction.execute(actor, map);

        result = vendor + " sold " + getWeapon() + " to " + actor;

        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " buys " + getWeapon() + " (" + getRequiredSouls() + " souls)";
    }

    public void setWeapon(WeaponItem weapon) {
        this.weaponItem = weapon;
    }

    public void setRequiredSouls(int souls) {
        this.requiredSouls = souls;
    }

    public WeaponItem getWeapon() {
        return weaponItem;
    }

    public int getRequiredSouls() {
        return requiredSouls;
    }
}
