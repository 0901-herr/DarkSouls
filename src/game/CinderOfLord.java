package game;

import game.enums.Status;

/**
 * The item dropping after Yhorm is defeated
 */
public class CinderOfLord extends PortableItem {
    LordOfCinder lordOfCinder;

    /**
     * Constructor for the item.
     *
     */
    public CinderOfLord(LordOfCinder lordOfCinder, String name) {
        super(name, '%');
        this.lordOfCinder = lordOfCinder;
        this.addCinderOfLordCapability();
    }

    /**
     * To add the capability of different types of Cinder of Lord
     *
     */
    public void addCinderOfLordCapability() {
        this.addCapability(Status.TRADABLE);

        // Aldrich's Cinder of Lord
        if (lordOfCinder.hasCapability(Status.IS_ALDRICH)) {
            this.addCapability(Status.IS_ALDRICH);
        }
        // Yhorm's Cinder of Lord
        else if (lordOfCinder.hasCapability(Status.IS_YHORM)){
            this.addCapability(Status.IS_YHORM);
        }
    }
}
