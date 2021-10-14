package game;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.enums.Status;

import java.util.Random;

public class Chest extends Enemy {

    private Random rand = new Random();

    public Chest(Location initialLocation) {
        super("Chest", '?', 9999, initialLocation, 200);
        this.setInitialLocation(initialLocation);
        addRandomTokenOfSouls();
    }

    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions actions = new Actions();

        // it can be opened only by the HOSTILE opponent
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new OpenChestAction(this, direction));
        }

        return actions;
    }

    public void addRandomTokenOfSouls() {
        int randomNumber;
        randomNumber = rand.nextInt(3);

        for (int i = 0; i < randomNumber+1; i++) {
            TokenOfSoul tokenOfSouls = new TokenOfSoul("Token of Soul", '$', false, this);
            tokenOfSouls.setSouls(100);
            this.addItemToInventory(tokenOfSouls);
        }
    }

    @Override
    public String toString() {
        return name;
    }

}
