package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.WeaponAction;
import game.enums.*;

public class GreatMachete extends MeleeWeapon{

    protected Actor actor;

    public GreatMachete(Actor actor) throws Exception{
        super("Yhorm's Great Machete", 'M', 95, "smashes", 60);
        if (actor.hasCapability(Abilities.IS_YHORM)){
            this.actor = actor;
        }
        else{
            throw new Exception("Incorrect user");
        }
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
    public WeaponAction getActiveSkill(Actor target, String direction){
        return new BurnAction(this);
    }

}
