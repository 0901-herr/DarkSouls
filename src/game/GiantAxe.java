package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.WeaponAction;

import java.util.List;

public class GiantAxe extends MeleeWeapon {

    public GiantAxe(){
        super("Giant Axe", 'G', 50, "axes", 80);
    }

    @Override
    public List<Action> getAllowableActions() {
        Actions allowableActions = new Actions();
        allowableActions.add(new SpinAttackAction(this));
        return allowableActions.getUnmodifiableActionList();
    }
}
