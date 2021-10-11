package game;

import edu.monash.fit2099.engine.*;
import game.enums.Abilities;
import game.enums.Status;

import java.util.ArrayList;
import java.util.List;

public class Longbow extends MeleeWeapon {

    private Actor actor;
    private Actions allowableActions = new Actions();

    /**
     * Constructor.
     */
    public Longbow() {
        super("Darkmoon Longbow", '8', 70, "pierces", 80);
        this.addCapability(Abilities.CRITICAL_STRIKE);
        this.criticalStrikeRate = 15;
    }

    /**
     * Getter of the allowable actions of Darkmoon Longbow.
     *
     * Returns an unmodifiable copy of the actions list so that calling methods won't.
     * be able to change what this Item can do without the Item checking.
     * @return an unmodifiable list of Actions.
     */
    @Override
    public List<Action> getAllowableActions() {
        return allowableActions.getUnmodifiableActionList();
    }

    /**
     * Inform a carried Item of the passage of time.
     *
     * This method is called once per turn, if the Item is being carried.
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        this.actor = actor;
        allowableActions = new Actions();
        List<Exit> exits = new ArrayList<>();
        GameMap map = currentLocation.map();
        int x = currentLocation.x();
        int y = currentLocation.y();

        // add exit
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                exits.add(new Exit("North", map.at(x, y - j), null));
                exits.add(new Exit("North-East", map.at(x + i, y - j), null));
                exits.add(new Exit("East", map.at(x + i, y), null));
                exits.add(new Exit("South-East", map.at(x + i, y + j), null));
                exits.add(new Exit("South", map.at(x, y + j), null));
                exits.add(new Exit("South-West", map.at(x - i, y + j), null));
                exits.add(new Exit("West", map.at(x - i, y), null));
                exits.add(new Exit("North-West", map.at(x - i, y - j), null));
            }
        }

        // find enemy
        for (Exit exit: exits){
            Location destination = exit.getDestination();
            if (destination.containsAnActor()) {
                Actor target = destination.getActor();

                if (actor.hasCapability(Status.IS_PLAYER) && target.hasCapability(Status.HOSTILE_TO_PLAYER) ||
                        actor.hasCapability(Status.HOSTILE_TO_PLAYER) && target.hasCapability(Status.IS_PLAYER)) {
                    System.out.println("target found !!!!");
                    allowableActions.add(new AttackAction(target, exit.getName()));
                }
            }
        }
    }
}
