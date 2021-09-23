package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.WeaponAction;
import game.enums.Status;

import java.util.List;

public class GreatMachete extends MeleeWeapon{

    protected Actor actor;

    public GreatMachete(Actor actor){
        super("Yhorm's Great Machete", 'M', 95, "smashes", 60);
        this.actor = actor;
    }

    @Override
    public int chanceToHit(){
        int hitRate = this.hitRate;
        if (actor.hasCapability(Status.RAGE_MODE)){
            hitRate += 30;
        }
        return hitRate;
    }

    @Override
    public List<Action> getAllowableActions() {
        Actions allowableActions = new Actions();
        if (actor.hasCapability(Status.RAGE_MODE)) {
            allowableActions.add(new BurnGroundAction(this));
        }
        return allowableActions.getUnmodifiableActionList();
    }

    @Override
    public WeaponAction getActiveSkill(Actor target, String direction){
        return new BurnGroundAction(this);
    }

}
