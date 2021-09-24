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
    // Will need to change this to a collection if Undeads gets additional Behaviours.
    Random rand = new Random();
    int souls = 250;

    /**
     * Constructor.
     * All Undeads are represented by an 'u' and have 30 hit points.
     * @param name the name of this Undead
     */
    public Skeleton(String name, Location initialLocation) {
        super(name, 's', 100, initialLocation);
        getBehaviours().add(new WanderBehaviour());
        addRandomizeWeapon();
        setSouls(this.souls);
        setInitialLocation(initialLocation);
    }

    /**
     * At the moment, we only make it can be attacked by enemy that has HOSTILE capability
     * You can do something else with this method.
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return list of actions
     * @see Status#HOSTILE_TO_ENEMY
     */
    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        // create follow behaviour here
        // add followbehaviour in behaviour list
        Actions actions = new Actions();

        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            // add behaviours for Undead
            addBehaviour(otherActor);

            // AttackAction to Player
            actions.add(new AttackAction(this, direction));
        }

        return actions;
    }


    /**
     * Figure out what to do next.
     * @see edu.monash.fit2099.engine.Actor#playTurn(Actions, Action, GameMap, Display)
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        // Revive it Skeleton
        if (getIsReset()) {
            setReset(false);
            return new DoNothingAction();
        }

        if (!isConscious() && this.hasCapability(Abilities.REVIVE_FOR_ONCE) && rand.nextBoolean()) {
            System.out.println("Skeleton is Revived");
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
    public void addBehaviour(Actor otherActor) {
        if (getFollowBehaviour() == null) {
            // Add AttackBehaviour
            getBehaviours().add(0, new AttackBehaviour());

            // add FollowBehaviour
            setFollowBehaviour(new FollowBehaviour(otherActor));
            getBehaviours().add(1, getFollowBehaviour());
        }
    }

    public void addRandomizeWeapon() {
        Random rand = new Random();
        WeaponItem weapon;
        WeaponItem broadSword = new BroadSword();
        WeaponItem giantAxe = new GiantAxe();

        if (rand.nextBoolean()) {
            weapon = giantAxe;
        }
        else {
            weapon = broadSword;
        }

        addItemToInventory(weapon);
    }
}
