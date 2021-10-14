package game;


import edu.monash.fit2099.engine.*;
import game.enums.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RangedWeapon extends WeaponItem {

    protected Actor actor;
    protected GameMap map;
    protected int criticalStrikeRate;
    protected int range;
    protected Display display = new Display();
    protected Random rand = new Random();

    /**
     * Constructor.
     *
     * @param name        name of the item
     * @param displayChar character to use for display when item is on the ground
     * @param damage      amount of damage this weapon does
     * @param verb        verb to use for this weapon, e.g. "hits", "zaps"
     * @param hitRate     the probability/chance to hit the target.
     */
    public RangedWeapon(String name, char displayChar, int damage, String verb, int hitRate, int range) {
        super(name, displayChar, damage, verb, hitRate);
        this.range = range;
    }

    /**
     * Accessor for damage done by this weapon.
     * <p>
     * If this weapon has ability to hit critical strike, damage will be doubled
     *
     * @return the damage
     */
    @Override
    public int damage() {
        int damage = this.damage;
        if (this.hasCapability(Abilities.CRITICAL_STRIKE)) {
            if (rand.nextInt(100) <= criticalStrikeRate) {
                damage *= 2;
                display.println("CRITICAL STRIKE !!!");
            }
        }
        return damage;
    }

    /**
     * Disable the DropItemAction of weapon
     *
     * @param actor an actor that will interact with this item
     * @return null
     */
    @Override
    public DropItemAction getDropAction(Actor actor) {
        return null;
    }

    /**
     * Getter of the allowable actions of Darkmoon Longbow.
     * <p>
     * Returns an unmodifiable copy of the actions list so that calling methods won't.
     * be able to change what this Item can do without the Item checking.
     *
     * @return an unmodifiable list of Actions.
     */
    @Override
    public List<Action> getAllowableActions() {
        Actions allowableActions = new Actions();
        if (map != null) {
            List<Exit> exits = new ArrayList<>();
            Location here = map.locationOf(actor);

            // add exit
            int x = here.x();
            int y = here.y();
            for (int i = 2; i <= range; i++) {
                for (int j = 1; j <= i; j++) {

                    if (j == i) {
                        createExit(exits, x, y - i, "North", null);
                        createExit(exits, x + i, y, "East", null);
                        createExit(exits, x, y + i, "South", null);
                        createExit(exits, x - i, y, "West", null);

                        for (int k = 1; k <= j; k++) {
                            createExit(exits, x + i, y - k, "North-East", null);
                            createExit(exits, x + i, y + k, "South-East", null);
                            createExit(exits, x - i, y - k, "North-West", null);
                            createExit(exits, x - i, y + k, "South-West", null);
                        }
                    } else {
                        createExit(exits, x + j, y - i, "North-East", null);
                        createExit(exits, x + j, y + i, "South-East", null);
                        createExit(exits, x - j, y - i, "North-West", null);
                        createExit(exits, x - j, y + i, "South-West", null);
                    }
                }
            }

            // find target
            for (Exit exit : exits) {
                Location destination = exit.getDestination();
                if (destination.containsAnActor()) {
                    Actor target = destination.getActor();

                    // target found
                    if (actor.hasCapability(Status.IS_PLAYER) && target.hasCapability(Status.HOSTILE_TO_PLAYER) ||
                            actor.hasCapability(Status.HOSTILE_TO_PLAYER) && target.hasCapability(Status.IS_PLAYER)){
                        // check if attack is blocked
                        if (isBlock(actor, target)){
                            target.addCapability(Status.BLOCKED);
                        }
                        allowableActions.add(new AttackAction(target, exit.getName()));
                    }
                }
            }
        }
        return allowableActions.getUnmodifiableActionList();
    }

    /**
     * Inform a carried Item of the passage of time.
     * <p>
     * This method is called once per turn, if the Item is being carried.
     *
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor           The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        this.map = currentLocation.map();
        this.actor = actor;
    }

    public void createExit(List<Exit> exits, int x, int y, String name, String hotKey) {
        if (map.getXRange().contains(x) && map.getYRange().contains(y)) {
            exits.add(new Exit(name, map.at(x, y), hotKey));
        }
    }

    public boolean isBlock(Actor actor, Actor target){
        Location here = map.locationOf(actor);
        Location there = map.locationOf(target);

        NumberRange xs, ys;
        xs = new NumberRange(Math.min(here.x(), there.x()), Math.abs(here.x() - there.x()) + 1);
        ys = new NumberRange(Math.min(here.y(), there.y()), Math.abs(here.y() - there.y()) + 1);

        for (int x : xs) {
            for (int y : ys) {
                if(map.at(x, y).getGround().blocksThrownObjects())
                    return true;
            }
        }
        return false;
    }
}

