package game;


import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.enums.*;

/**
 * The class to handle the generation of the burned ground
 */
public class BurnedGround extends Ground {

    private int round;
    private Ground previousGround;

    /**
     * Constructor.
     * @param previousGround previous ground before it was burned.
     */
    public BurnedGround(Ground previousGround){
        super('v');
        this.previousGround = previousGround;
        this.round = 0;
        this.addCapability(Abilities.BURN);
    }

    /**
     * Hurt the actor that stand on this ground.
     * Last for three rounds.
     *
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location){
        super.tick(location);

        if (location.containsAnActor()){
            if (!location.getActor().hasCapability(Status.IMMUNE_TO_BURN)){
                location.getActor().hurt(25);
            }
        }

        round++;
        if (round == 4){
            location.setGround(previousGround);
        }
    }
}
