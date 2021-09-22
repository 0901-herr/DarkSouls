package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Item;

import java.util.ArrayList;
import java.util.List;

public class EstusFlask extends Item {
    private Player player;
    private int MAX_CHARGE=3;
    private int charge;
    private DrinkEstusFlaskAction drinkEstusFlaskAction;

    public EstusFlask(Player player) {
        super("EstusFlask", 'e', false);
        this.player=player;
        this.charge=MAX_CHARGE;
        drinkEstusFlaskAction = new DrinkEstusFlaskAction(this);
    }

    @Override
    public List<Action> getAllowableActions() {
        List<Action> drink = new ArrayList<Action>();
        drink.add(drinkEstusFlaskAction);
        return drink;
    }
    public int getMAX_CHARGE(){
        return MAX_CHARGE;
    }
    public int getCharge() {
        return charge;
    }

    public void setCharge(int charge) {
        this.charge = charge;
    }
}
