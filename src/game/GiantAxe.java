package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.WeaponAction;
import edu.monash.fit2099.engine.WeaponItem;
import game.interfaces.Behaviour;

import java.util.ArrayList;
import java.util.List;

public class GiantAxe extends MeleeWeapon {
    private List<Action> actions = new ArrayList<>();

    public GiantAxe(){
        super("Giant Axe", 'G', 50, "axes", 80);
    }

    @Override
    public List<Action> getAllowableActions() {
        actions.add(new SpinAttackAction(this));
        return actions;
    }

    @Override
    public WeaponAction getActiveSkill(Actor target, String direction){
        return new SpinAttackAction(this);
    }
}
