package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class DrinkEstusFlaskAction extends Action {
    EstusFlask ef;
    public DrinkEstusFlaskAction(EstusFlask ef) {
        this.ef=ef;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        if (ef.getCharge()==0){
        return null;
        }
        else{
            actor.heal(40);
            ef.setCharge(ef.getCharge()-1);
            return actor.toString()+" drank Estus Flask";
        }
    }

    @Override
    public String menuDescription(Actor actor) {
        return String.format("%s drink Estus Flask %d/%d",actor.toString(),ef.getCharge(),ef.getMAX_CHARGE());
    }
}
