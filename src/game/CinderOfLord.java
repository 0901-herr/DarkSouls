package game;

import edu.monash.fit2099.engine.Actor;
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

    public void addCinderOfLordCapability() {
        this.addCapability(Status.TRADABLE);

        if (lordOfCinder.hasCapability(Status.IS_ALDRICH)) {
            this.addCapability(Status.IS_ALDRICH);
        }
        else if (lordOfCinder.hasCapability(Status.IS_YHORM)){
            this.addCapability(Status.IS_YHORM);
        }
    }
}
