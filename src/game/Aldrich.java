package game;

import edu.monash.fit2099.engine.*;
import game.enums.Abilities;
import game.enums.Status;

import java.util.ArrayList;

public class Aldrich extends LordOfCinder{
    ArrayList<Location> expandedLocations = new ArrayList<>();

    public Aldrich(String name, char displayChar, int hitPoints, Location initialLocation) {
        super(name, displayChar, hitPoints, initialLocation, 5000);
        this.addCapability(Status.IS_ALDRICH);
        this.addCapability(Status.IMMUNE_TO_BURN);
        this.addItemToInventory(new Longbow(this));
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
        if (this.getHitPoints() < (this.getMaxHitPoints()*0.5) && this.getEnrageBehaviour() == null) {
            this.addCapability(Status.RAGE_MODE);
            this.addEnrageBehaviour();

            // heal by 20% life
            this.heal((int) (this.getMaxHitPoints()*0.2));

            display.println(this + " is in RAGE MODE, hit rate increases");
        }
        return super.playTurn(actions, lastAction, map, display);
    }

    public void scanExpandedExits(GameMap map) {
        Location here = map.locationOf(this);
        NumberRange xs, ys;
        int range = 3;
        xs = new NumberRange(here.x() - range, range * 2 + 1);
        ys = new NumberRange(here.y() - range, range * 2 + 1);

        for (int x : xs) {
            for (int y : ys) {
                if (map.getXRange().contains(x) && map.getYRange().contains(y)) {
                    if (!(x == here.x() && y == here.y()) && map.at(x, y).containsAnActor()){
                        Actor target = map.at(x, y).getActor();
                        if (target.hasCapability(Status.HOSTILE_TO_ENEMY)){
                            this.addBehaviour(target);
                            System.out.println("target found");
                        }
                    }
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

    @Override
    public void resetInstance() {
        super.resetInstance();
    }
}
