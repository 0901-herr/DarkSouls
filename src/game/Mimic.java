package game;

import edu.monash.fit2099.engine.*;

import java.util.Random;

public class Mimic extends Enemy {
    Random rand = new Random();

    /**
     * Constructor.
     * All Undeads are represented by an 'u' and have 30 hit points.
     * @param name the name of this Undead
     */
    public Mimic(String name, Location initialLocation) {
        super(name, 'M', 100, initialLocation, 200);
        this.getBehaviours().add(new WanderBehaviour());
        this.setInitialLocation(initialLocation);
    }

    /**
     * Select and return an action to perform on the current turn of the Undead.
     *
     * @param actions    Collection of possible Actions for this Undead
     * @param lastAction The Action this Undead took last turn.
     * @param map        The map containing the Undead
     * @param display    The I/O object to which messages may be written
     * @return the Action to be performed by the Undead
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        if (!isConscious()) {
            this.addRandomTokenOfSouls();
            DyingAction dyingAction = new DyingAction(map.locationOf(this), this.asSoul().getSouls(),null, this,null,null, false);
            return dyingAction;
        }

        // loop through all behaviours
        return super.playTurn(actions, lastAction, map, display);
    }

    public void addRandomTokenOfSouls() {
        int randomNumber;
        randomNumber = rand.nextInt(4);

        for (int i = 0; i < randomNumber; i++) {
            this.addItemToInventory(new TokenOfSoul("Token of Soul", '$', false, this));
        }
    }

    /**
     * When this method is called:
     * Behavior of Undead is cleared.
     * Undead is removed from map.
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
        String hitPointsStatus = "(" + getHitPoints() + "/" + getMaxHitPoints() + ")";
        String weaponStatus = "no weapon";
        String weaponStatusMessage = " (" + weaponStatus + ")";
        return (name + hitPointsStatus + weaponStatusMessage);
    }
}
