package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.WeaponItem;
import edu.monash.fit2099.engine.Location;

import game.enums.Abilities;
import game.enums.Status;
import game.interfaces.Behaviour;
import game.interfaces.Soul;

import java.util.ArrayList;
import java.util.Random;

/**
 * An skeleton minion.
 */

public class Skeleton extends Enemy {
    Random rand = new Random();

    /**
     * Constructor.
     * All Undeads are represented by an 'u' and have 30 hit points.
     * @param name the name of this Undead
     */
    public Skeleton(String name, Location initialLocation) {
        super(name, 's', 100, initialLocation, 250);
        this.setInitialLocation(initialLocation);
        this.getBehaviours().add(new WanderBehaviour());
        this.addRandomizeWeapon();
    }

    /**
     * Figure out what to do next.
     * @see edu.monash.fit2099.engine.Actor#playTurn(Actions, Action, GameMap, Display)
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {

        // Skeleton has 50% chance to revive once
        if (!isConscious() && this.hasCapability(Abilities.REVIVE_FOR_ONCE) && rand.nextBoolean()) {
            this.heal(getMaxHitPoints());

            // Remove ability
            this.removeCapability(Abilities.REVIVE_FOR_ONCE);
            return new DoNothingAction();
        }

        // loop through all behaviours
        for(Behaviour Behaviour : getBehaviours()) {
            Action action = Behaviour.getAction(this, map);
            if (action != null)
                return action;
        }

        return new DoNothingAction();
    }

    @Override
    public void resetInstance() {
        this.getInitialLocation().map().removeActor(this);

        this.getBehaviours().clear();
        this.getBehaviours().add(new WanderBehaviour());

        // remove FollowBehaviour
        this.setFollowBehaviour(null);

        // reset location
        this.getInitialLocation().map().moveActor(this, getInitialLocation());
        this.hitPoints = getMaxHitPoints();
    }

    public void addRandomizeWeapon() {
        WeaponItem weapon;
        WeaponItem broadSword = new BroadSword();
        WeaponItem giantAxe = new GiantAxe();

        if (rand.nextBoolean()) {
            weapon = giantAxe;
        }
        else {
            weapon = broadSword;
        }

        this.addItemToInventory(weapon);
    }
}
