package game;

import edu.monash.fit2099.engine.*;
import game.enums.Status;

import java.util.Random;

public class Aldrich extends LordOfCinder{
    Random rand = new Random();

    public Aldrich(String name, char displayChar, int hitPoints, Location initialLocation) {
        super(name, displayChar, hitPoints, initialLocation, 5000);
        this.addCapability(Status.IS_ALDRICH);
//        this.addItemToInventory(new LongBow(this));
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
        Actor target;
        Location here = map.locationOf(this);

        // TODO: extend exit search to 7x7
        // search exits of the actor to find the target
        // instead of adding behaviours in enemy's getAllowableActions
        // Aldrich needs to actively scan its surroundings to detect player

        if (this.getFollowBehaviour() != null) {
            for (Exit exit : here.getExits()) {
                Location destination = exit.getDestination();

                if (destination.containsAnActor() && destination.getActor().hasCapability(Status.IS_PLAYER)) {
                    target = destination.getActor();
                    this.addBehaviour(target);
                    break;
                }
            }
        }

        // when Aldrich hp < 50% max hp, add EnrageBehaviour
        if (this.getHitPoints() < (this.getMaxHitPoints()*0.5)) {
            this.addCapability(Status.RAGE_MODE);
            this.addEnrageBehaviour();

            // heal by 20% life
            this.heal((int) (this.getMaxHitPoints()*0.2));

            display.println(this + " is in RAGE MODE, hit rate increases");
        }

        // when Aldrich is stunned
        if (this.hasCapability(Status.STUNNED)){
            display.println(this + " is stunned");
            this.removeCapability(Status.STUNNED);
            return new DoNothingAction();
        }

        return super.playTurn(actions, lastAction, map, display);
    }

    @Override
    public void addBehaviour(Actor otherActor) {
        if (getFollowBehaviour() == null) {
            // Add AttackBehaviour
            this.getBehaviours().add(0, new AttackBehaviour());

            // add FollowBehaviour
            this.setFollowBehaviour(new FollowBehaviour(otherActor));
            this.getBehaviours().add(1, this.getFollowBehaviour());
        }
    }
}
