package game;

import edu.monash.fit2099.engine.*;
import game.enums.Status;
import game.interfaces.Behaviour;
import game.interfaces.Soul;

import java.util.ArrayList;

public abstract class Enemy extends Actor implements Soul {
    private int souls;
    private ArrayList<Behaviour> behaviours = new ArrayList<>();
    private FollowBehaviour followBehaviour;

    public Enemy(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        addCapability(Status.HOSTILE_TO_PLAYER);
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

    public void addBehaviour(Actor otherActor) {
        if (followBehaviour == null) {
            // Add AttackBehaviour
            System.out.println(this + " AttackBehaviour added");
            behaviours.add(0, new AttackBehaviour());

            // add FollowBehaviour
            System.out.println(this + " FollowBehaviour added");
            followBehaviour = new FollowBehaviour(otherActor);
            behaviours.add(1, followBehaviour);
        }
    }

    public ArrayList<Behaviour> getBehaviours() {
        return behaviours;
    }

    // Souls

    @Override
    public abstract void transferSouls(Soul soulObject);

    @Override
    public boolean addSouls(int souls) {
        boolean isSuccess = false;
        if (souls > 0) {
            this.souls += souls;
            isSuccess = true;
        }
        else {
            isSuccess = false;
        }
        return isSuccess;
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
        return souls;
    }

    @Override
    public String toString() {
        String hitPointsStatus = "(" + this.hitPoints + "/" + this.maxHitPoints + ")";
        String weaponStatus = getWeapon().toString();
        String weaponStatusMessage = " (" + weaponStatus + ")";
        return (name + hitPointsStatus + weaponStatusMessage);
    }

    public int getHitPoints() {
        return this.hitPoints;
    }

    public int getMaxHitPoints() {
        return this.maxHitPoints;
    }
}


