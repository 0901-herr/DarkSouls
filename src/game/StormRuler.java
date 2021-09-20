package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.WeaponAction;
import game.enums.Abilities;


public class StormRuler extends MeleeWeapon{


    protected int numberOfCharge;
    protected int maxNumberOfCharge = 3;

    // constructor
    public StormRuler(){
        super("Storm Ruler", '7', 70, "strikes", 60);
        this.addCapability(Abilities.CRITICAL_STRIKE);
        this.criticalStrikeRate = 20;
        this.numberOfCharge = 0;
    }

    @Override
    public WeaponAction getActiveSkill(Actor target, String direction){
        if (this.numberOfCharge == this.maxNumberOfCharge){
            return new WindSlash(this, target, direction);
        }
        return new Charge(this);
    }

}

