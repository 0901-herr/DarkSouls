package game;

import edu.monash.fit2099.engine.*;
import game.enums.Status;

public class WindSlashAction extends WeaponAction {

    protected StormRuler weapon;
    protected Actor target;

    /**
     * Constructor.
     * @param stormRuler the weapon item that will activate WindSlashAction.
     * @param target the actor to attack.
     */
    public WindSlashAction(StormRuler stormRuler, Actor target){
        super(stormRuler);
        this.weapon = stormRuler;
        this.target = target;
    }

    /**
     * Perform the WindSlashAction.
     * Stun the target.
     * Double the damage.
     * Hit rate increased to 100%.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map){
        int damage = weapon.damage() * 2;
        target.hurt(damage);
        String result = actor + " uses Wind Slash on " + target + " for " + damage + " damage and stuns " + target;
        if (!target.isConscious()) {
            DyingAction dyingAction = new DyingAction(target);
            dyingAction.execute(actor,map);
            result += System.lineSeparator() + target + " is killed.";
        }
        // stun
        target.addCapability(Status.STUNNED);

        // reset charge
        weapon.resetCharge();
        weapon.removeCapability(Status.FULLY_CHARGED);

        return result;
    }

    /**
     * Returns a descriptive string
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor){
        return actor + " activates Wind Slash";
    }

}

