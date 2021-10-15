package game;

import edu.monash.fit2099.engine.*;
import game.enums.Status;

import java.util.Random;

/**
 *
 * A Mimic has 50% chances of appearing when chest is opened
 *
 */
public class Mimic extends Enemy {
    Random rand = new Random();

    /**
     * Constructor.
     *
     * All Mimic are represented by an 'M' and have 100 hit points.
     * @param name the name of this Undead
     */
    public Mimic(String name, Location initialLocation) {
        super(name, 'M', 100, initialLocation, 200);
        this.setInitialLocation(initialLocation);
        this.addRandomTokenOfSouls();
        this.addCapability(Status.IS_MIMIC);
    }

    /**
     * Select and return an action to perform on the current turn of the Mimic.
     *
     * @param actions    Collection of possible Actions for this Mimic
     * @param lastAction The Action this Mimic took last turn.
     * @param map        The map containing the Mimic
     * @param display    The I/O object to which messages may be written
     * @return the Action to be performed by the Mimic
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        if (!isConscious()) {
            DyingAction dyingAction = new DyingAction(map.locationOf(this), this.asSoul().getSouls(), null, this, null, null);
            return dyingAction;
        }

        // loop through all behaviours
        return super.playTurn(actions, lastAction, map, display);
    }

    /**
     * Adding random number of Token of Souls (1 to 3) to the Mimic's inventory
     *
     */
    public void addRandomTokenOfSouls() {
        int randomNumber;
        randomNumber = rand.nextInt(3);

        for (int i = 0; i < randomNumber+1; i++) {
            TokenOfSoul tokenOfSouls = new TokenOfSoul("Token of Soul", '$', false, this);
            tokenOfSouls.setSouls(100);
            this.addItemToInventory(tokenOfSouls);
        }
    }

    /**
     * When this method is called:
     * Behavior of Mimic is cleared.
     * Mimic is removed from map.
     */
    @Override
    public void resetInstance() {
        this.getBehaviours().clear();
        this.setFollowBehaviour(null);
        this.getInitialLocation().map().removeActor(this);
    }

    /**
     * Getter for Intrinsic Weapon
     * @return IntrinsicWeapon object
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(55, "kicks");
    }

    /**
     * toString method
     * @return toString
     */
    @Override
    public String toString() {
        String hitPointsStatus = " (" + getHitPoints() + "/" + getMaxHitPoints() + ")";
        String weaponStatus = "no weapon";
        String weaponStatusMessage = " (" + weaponStatus + ")";
        return (name + hitPointsStatus + weaponStatusMessage);
    }
}
