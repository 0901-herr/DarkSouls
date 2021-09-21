package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.WeaponAction;
import edu.monash.fit2099.engine.WeaponItem;

public class GiantAxe extends MeleeWeapon {

    public GiantAxe(){
        super("Giant Axe", 'G', 50, "axes", 80);
    }

    @Override
    public WeaponAction getActiveSkill(Actor target, String direction){
        return new SpinAttackAction(this);
    }

}
