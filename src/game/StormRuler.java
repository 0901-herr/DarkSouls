package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.WeaponAction;
import game.enums.Abilities;


public class StormRuler extends MeleeWeapon {

    protected Actor actor;
    protected int numberOfCharge;
    protected final int maxNumberOfCharge = 3;

    // constructor
    public StormRuler(Actor actor) throws Exception{
        super("Storm Ruler", '7', 70, "strikes", 60);
        if (actor.hasCapability(Abilities.IS_PLAYER)) {
            this.addCapability(Abilities.CRITICAL_STRIKE);
            this.criticalStrikeRate = 20;
            this.numberOfCharge = 0;
        }
        else{
            throw new Exception("Incorrect user");
        }
    }

    @Override
    public WeaponAction getActiveSkill(Actor target, String direction){
        if (this.numberOfCharge == this.maxNumberOfCharge){
            return new WindSlashAction(this, target, direction);
        }
        return new ChargeAction(this);
    }

}

