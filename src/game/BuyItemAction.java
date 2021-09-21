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

/**
 * Special Action for Buying items from Vendor.
 */
public class BuyItemAction extends Action {

    /**
     * The Actor that is to be attacked
     */
    protected Actor vendor;
    private WeaponItem weapon;
//    private boolean increaseMaxHp = false;
    private int requiredSouls;

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

    @Override
    public String execute(Actor actor, GameMap map) {
        String result;

        // check if buyer has enough souls
        // TODO: Should be using transferSouls()
        if (actor.asSoul().getSouls() < getRequiredSouls()) {
            result = actor + " does not have enough souls";
            return result;
        }
        else {
            actor.asSoul().setRequiredSouls(getRequiredSouls());
            actor.asSoul().transferSouls(vendor.asSoul());
        }

        // get current equip weapon and remove it
        for (Item item: actor.getInventory()) {
            Weapon weapon = actor.getWeapon();
            if (weapon == item) {
                actor.removeItemFromInventory(item);
            }
        }

        // add bought weapon to inventory and equip it
        actor.addItemToInventory(this.weapon);

        result = vendor + " sold " + getWeapon() + " to " + actor;

        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " buys " + getWeapon() + " (" + getRequiredSouls() + " souls)";
    }

    public void setWeapon(WeaponItem weapon) {
        this.weapon = weapon;
    }

    public void setRequiredSouls(int souls) {
        this.requiredSouls = souls;
    }

    public WeaponItem getWeapon() {
        return weapon;
    }

    public int getRequiredSouls() {
        return requiredSouls;
    }
}
