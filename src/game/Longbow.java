package game;

import edu.monash.fit2099.engine.*;
import game.enums.Abilities;
import game.enums.Status;

import java.util.List;

public class Longbow extends RangedWeapon {

    /**
     * Constructor.
     */
    public Longbow(Actor actor) {
        super("Darkmoon Longbow", '8', 70, "pierces", 80, 3);
        this.actor = actor;
        this.addCapability(Abilities.CRITICAL_STRIKE);
        this.criticalStrikeRate = 15;
    }

    /**
     * Getter of the allowable actions of Darkmoon Longbow.
     *
     * Returns an unmodifiable copy of the actions list so that calling methods won't.
     * be able to change what this Item can do without the Item checking.
     * @return an unmodifiable list of Actions.
     */
    @Override
    public List<Action> getAllowableActions() {
        Actions allowableActions = new Actions();

        if (actor.hasCapability(Status.IS_PLAYER)) {
            allowableActions.add(new FireArrowAction(this));
        }
        allowableActions.add(super.getAllowableActions());
        return allowableActions.getUnmodifiableActionList();
    }

    @Override
    public WeaponAction getActiveSkill(Actor target, String direction) {
        return new FireArrowAction(this);
    }

    @Override
    public String toString(){
        if (this.hasCapability(Abilities.FIRE))
            return name +  " (ACTIVATED)";
        else
            return name +  " (DEACTIVATED)";
    }
}
