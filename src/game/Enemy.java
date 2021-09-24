package game;

import edu.monash.fit2099.engine.*;
import game.enums.Status;
import game.interfaces.Behaviour;
import game.interfaces.Resettable;
import game.interfaces.Soul;

import java.util.ArrayList;

public abstract class Enemy extends Actor implements Soul, Resettable {
    private int souls;
    private boolean isReset = false;

    private ArrayList<Behaviour> behaviours = new ArrayList<>();
    private FollowBehaviour followBehaviour;
    private Location initialLocation;

    public Enemy(String name, char displayChar, int hitPoints, Location initialLocation) {
        super(name, displayChar, hitPoints);
        addCapability(Status.HOSTILE_TO_PLAYER);
        setInitialLocation(initialLocation);
        registerInstance();
    }

    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        if (isReset) {
            isReset = false;
            return new DoNothingAction();
        }

        // loop through all behaviours
        for(Behaviour Behaviour : behaviours) {
            Action action = Behaviour.getAction(this, map);
            if (action != null)
                return action;
        }
        return new DoNothingAction();
    }

    public abstract void addBehaviour(Actor otherActor);

    public ArrayList<Behaviour> getBehaviours() {
        return behaviours;
    }

    // Souls
    @Override
    public void transferSouls(Soul soulObject) {
        soulObject.addSouls(getSouls());
        subtractSouls(getSouls());
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
    };

    // Reset
    @Override
    public void resetInstance() {
        isReset = true;
        // remove FollowBehaviour
        followBehaviour = null;

        // reset location
        getInitialLocation().map().moveActor(this, initialLocation);
        this.hitPoints = getMaxHitPoints();
    }

    @Override
    public boolean isExist() {
        return true;
    }

    public void setInitialLocation(Location initialLocation) {
        this.initialLocation = initialLocation;
    }

    public Location getInitialLocation() {
        return initialLocation;
    }

    public void setFollowBehaviour(FollowBehaviour followBehaviour) {
        this.followBehaviour = followBehaviour;
    }

    public void setReset(boolean reset) {
        isReset = reset;
    }

    public boolean getIsReset() {
        return this.isReset;
    }

    public FollowBehaviour getFollowBehaviour() {
        return followBehaviour;
    }

    public int getHitPoints() {
        return this.hitPoints;
    }

    public int getMaxHitPoints() {
        return this.maxHitPoints;
    }

    @Override
    public String toString() {
        String hitPointsStatus = "(" + this.hitPoints + "/" + this.maxHitPoints + ")";
        String weaponStatus = getWeapon().toString();
        String weaponStatusMessage = " (" + weaponStatus + ")";
        return (name + hitPointsStatus + weaponStatusMessage);
    }
}


