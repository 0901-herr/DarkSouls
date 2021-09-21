package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Weapon;
import game.enums.Status;
import game.interfaces.Behaviour;
import game.interfaces.Soul;

import java.util.ArrayList;

public abstract class Enemy extends Actor implements Soul {
    private Actor player;
    private Boolean attackBehaviourIsAdded = false;
    private int souls;

    public Enemy(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        addCapability(Status.HOSTILE_TO_PLAYER);
    }

    // Add behaviour to each enemy
    public void addBehaviours(Actions actions, ArrayList<Behaviour> behaviours) {
        boolean attackActionIsFound = false;

        // Added by Philippe
        // Search for AttackAction
        for (Action action : actions) {
            if (action instanceof AttackAction) {
                if (attackBehaviourIsAdded) {
                    behaviours.remove(0);
                }
                behaviours.add(0, new AttackBehaviour(action));
                attackActionIsFound = true;
                attackBehaviourIsAdded = true;

                // Setting the player
                setPlayer(((AttackAction) action).target);
                break;
            }
        }

        // If Player has an AttackBehaviour but not in Undead exit
        // Remove AttackBehaviour and add FollowBehaviour
        if (attackBehaviourIsAdded && !attackActionIsFound) {
            behaviours.remove(0);
            attackBehaviourIsAdded = false;

            behaviours.add(0, new FollowBehaviour(getPlayer()));
            System.out.println("Skeleton Call FollowBehaviour");
        }
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
        String weaponStatus = getWeapon()==null ? "no weapon" : getWeapon().toString();
        String weaponStatusMessage = " (" +weaponStatus + ")";
        return (name + hitPointsStatus + weaponStatusMessage);
    }

    public void setPlayer(Actor player) {
        this.player = player;
    }

    public Actor getPlayer() {
        return this.player;
    }

    public int getHitPoints() {
        return this.hitPoints;
    }

    public int getMaxHitPoints() {
        return this.maxHitPoints;
    }
}


