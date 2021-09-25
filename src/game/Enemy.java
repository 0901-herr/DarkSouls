package game;

import edu.monash.fit2099.engine.*;
import game.enums.Status;
import game.interfaces.Behaviour;
import game.interfaces.Resettable;
import game.interfaces.Soul;

import java.util.ArrayList;

public abstract class Enemy extends Actor implements Soul, Resettable {
    private int souls;
    private ArrayList<Behaviour> behaviours = new ArrayList<>();
    private FollowBehaviour followBehaviour;
    private Location initialLocation;

    public Enemy(String name, char displayChar, int hitPoints, Location initialLocation, int souls) {
        super(name, displayChar, hitPoints);
        this.addCapability(Status.HOSTILE_TO_PLAYER);
        this.setSouls(souls);
        this.setInitialLocation(initialLocation);
        this.registerInstance();
    }

    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        // loop through all behaviours
        for(Behaviour Behaviour : behaviours) {
            Action action = Behaviour.getAction(this, map);
            if (action != null)
                return action;
        }
        return new DoNothingAction();
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
        Actions actions = new Actions();

        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            // add behaviours for Undead
            this.addBehaviour(otherActor);

            // AttackAction to Player
            actions.add(new AttackAction(this, direction));
        }

        return actions;
    }

    public void addBehaviour(Actor otherActor) {
        if (getFollowBehaviour() == null) {
            // Add AttackBehaviour
            this.getBehaviours().add(0, new AttackBehaviour());

            // add FollowBehaviour
            this.setFollowBehaviour(new FollowBehaviour(otherActor));
            this.getBehaviours().add(1, this.getFollowBehaviour());
        }
    }

    public ArrayList<Behaviour> getBehaviours() {
        return behaviours;
    }

    // Reset
    @Override
    public void resetInstance() {
        // remove FollowBehaviour
        setFollowBehaviour(null);
        getBehaviours().clear();

        // reset location
        getInitialLocation().map().moveActor(this, initialLocation);
        this.hitPoints = getMaxHitPoints();
    }

    // Souls
    @Override
    public void transferSouls(Soul soulObject) {
        soulObject.addSouls(getSouls());
        subtractSouls(getSouls());
    }

    @Override
    public boolean isExist() {
        return true;
    }

    @Override
    public boolean subtractSouls(int souls) {
        boolean isSuccess = false;
        if (souls > 0) {
            this.souls -= souls;
            isSuccess = true;
        }
        else {
            isSuccess = false;
        }
        return isSuccess;
    }

    @Override
    public void setSouls(int souls) {
        this.souls = souls;
    }

    @Override
    public int getSouls() {
        return this.souls;
    }

    public int getHitPoints() {
        return this.hitPoints;
    }

    public int getMaxHitPoints() {
        return this.maxHitPoints;
    }

    public Location getInitialLocation() {
        return initialLocation;
    }

    public void setInitialLocation(Location initialLocation) {
        this.initialLocation = initialLocation;
    }

    public FollowBehaviour getFollowBehaviour() {
        return followBehaviour;
    }

    public void setFollowBehaviour(FollowBehaviour followBehaviour) {
        this.followBehaviour = followBehaviour;
    }

    @Override
    public String toString() {
        String hitPointsStatus = "(" + this.hitPoints + "/" + this.maxHitPoints + ")";
        String weaponStatus = getWeapon().toString();
        String weaponStatusMessage = " (" + weaponStatus + ")";
        return (name + hitPointsStatus + weaponStatusMessage);
    }
}


