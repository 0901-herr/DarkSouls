package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Item;

import java.util.ArrayList;
import java.util.List;

public class EstusFlask extends Item {
    Player player;
    int charge;
    DrinkEstusFlaskAction drinkEstusFlaskAction;
    public EstusFlask(Player player) {
        super("EstusFlask", 'e', false);
        this.player=player;
        this.charge=3;
        drinkEstusFlaskAction = new DrinkEstusFlaskAction();
    }

    @Override
    public List<Action> getAllowableActions() {
        List<Action> drink = new ArrayList<Action>();
        drink.add(drinkEstusFlaskAction);
        return drink;
    }

    public void heal(){
        player.heal((int)(player.getMaxHp()*0.4));
        charge-=1;
        if (charge<=0){
            player.removeItemFromInventory(this);
        }
        }

}
