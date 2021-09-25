package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.WeaponAction;
import game.enums.Status;

import java.util.List;

public class GreatMachete extends MeleeWeapon{

    protected Actor actor;

    /**
     * Constructor
     * @param actor that will equip this weapon.
     */
    public GreatMachete(Actor actor){
        super("Yhorm's Great Machete", 'M', 95, "smashes", 60);
        this.actor = actor;
        if (!(actor.hasCapability(Status.IS_YHORM))){
            display.println(actor + " cannot equip " + this);
        }
    }

    /**
     * A chance to hit the target (this is a dividend part of percentage).
     *
     * If the holder is in RAGE_MODE, hit rate of Great Machete will increase 30%.
     *
     * @return the chance, in integer for probability with nextInt(), e.g 100 = 100% hit rate.
     */
    @Override
    public int chanceToHit(){
        int hitRate = this.hitRate;
        if (actor.hasCapability(Status.RAGE_MODE)){
            hitRate += 30;
        }
        return hitRate;
    }

    /**
     * Getter of active skills of Great Machete.
     *
     * Return BurnGroundAction.
     *
     * Returns an unmodifiable copy of the actions list so that calling methods won't
     * be able to change what this Item can do without the Item checking.
     * @return an unmodifiable list of Actions
     */
    @Override
    public WeaponAction getActiveSkill(Actor target, String direction) {
        return new BurnGroundAction(this);
    }
}
