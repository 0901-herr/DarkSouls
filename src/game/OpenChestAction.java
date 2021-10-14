package game;

import edu.monash.fit2099.engine.*;

import java.util.Random;

public class OpenChestAction extends Action {

    protected Actor chest;
    protected String direction;
    private Random rand = new Random();

    public OpenChestAction(Actor chest, String direction){
        this.chest = chest;
        this.direction = direction;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        String result = menuDescription(actor);
        Location here = map.locationOf(chest);
        Actions actions = new Actions();
        boolean luck = rand.nextBoolean();

        if (luck){
            for (Item item: chest.getInventory()){
                actions.add(new DropItemAction(item));
            }
            for (Action action: actions){
                result += System.lineSeparator() + action.execute(chest, map);
            }
            map.removeActor(chest);
        }
        else{
            map.removeActor(chest);
            here.addActor(new Mimic("Mimic", here));
            result += System.lineSeparator() + "Oh no!! "+ chest + " turns into mimic.";
        }

        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " opens the Chest at " + direction;
    }

}
