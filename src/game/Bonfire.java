package game;

import edu.monash.fit2099.engine.Ground;
import game.enums.Abilities;

public class Bonfire extends Ground {
    public Bonfire(){
        super('B');
        this.addCapability(Abilities.REST);
    }

}
