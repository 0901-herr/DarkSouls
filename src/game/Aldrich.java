package game;

import edu.monash.fit2099.engine.*;
import game.enums.Status;

import java.util.ArrayList;

public class Aldrich extends LordOfCinder{
    ArrayList<Location> expandedLocations = new ArrayList<>();

    public Aldrich(String name, char displayChar, int hitPoints, Location initialLocation) {
        super(name, displayChar, hitPoints, initialLocation, 5000);
        this.addCapability(Status.IS_ALDRICH);
        this.addItemToInventory(new Longbow());
        this.addItemToInventory(new CinderOfLord(this, "Cinder of Aldrich the Devourer"));
    }

    /**
     * Figure out what to do next, including invoking enrage behaviour,
     * being stunned etc.
     *
     * @param actions    the available options of actions
     * @param lastAction the last action performed
     * @param map        the map containing Yhorm
     * @param display    the I/O object to which messages may be written
     * @return do nothing action
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        // scan surrounding 7x7 exits for target
        if (getFollowBehaviour() == null) {
            this.scanExpandedExits(map);
        }

        // when Aldrich hp < 50% max hp, add EnrageBehaviour
        if (this.getHitPoints() < (this.getMaxHitPoints()*0.5)) {
            this.addCapability(Status.RAGE_MODE);
            this.addEnrageBehaviour();

            // heal by 20% life
            this.heal((int) (this.getMaxHitPoints()*0.2));

            display.println(this + " is in RAGE MODE, hit rate increases");
        }

        return super.playTurn(actions, lastAction, map, display);
    }

    public void scanExpandedExits(GameMap map) {
        Actor target;

        // add expanded exits to scan
        this.addExpandedExits(map);

        // search exits of the actor to find the target
        // instead of adding behaviours in enemy's getAllowableActions
        // because Aldrich needs to actively scan its surroundings to detect player

        for (Location location : this.expandedLocations) {
            for (Exit exit: location.getExits()) {
                System.out.println("searching...");
                Location destination = exit.getDestination();
                if (destination.containsAnActor() && destination.getActor().hasCapability(Status.IS_PLAYER)) {
                    target = destination.getActor();
                    this.addBehaviour(target);
                    System.out.println("Found player, add behaviours");
                    return;
                }
            }
        }
    }

    public void addBehaviour(Actor otherActor) {
        // Add AttackBehaviour
        this.getBehaviours().add(0, new AttackBehaviour());

        // add FollowBehaviour
        this.setFollowBehaviour(new FollowBehaviour(otherActor));
        this.getBehaviours().add(1, this.getFollowBehaviour());
    }

    public void addExpandedExits(GameMap map) {
        expandedLocations.clear();
        Location here = map.locationOf(this);

        Location topLeft = map.at(here.x()-2, here.y()-2);
        Location topRight = map.at(here.x()+2, here.y()-2);
        Location midLeft = map.at(here.x()-2, here.y());
        Location midRight = map.at(here.x()+2, here.y());
        Location botLeft = map.at(here.x()-2, here.y()+2);
        Location botRight = map.at(here.x()+2, here.y()+2);

        expandedLocations.add(topLeft);
        expandedLocations.add(topRight);
        expandedLocations.add(midLeft);
        expandedLocations.add(midRight);
        expandedLocations.add(botLeft);
        expandedLocations.add(botRight);
    }
}
