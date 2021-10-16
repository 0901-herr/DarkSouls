package game;


import edu.monash.fit2099.engine.Display;
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
        Display display = new Display();
        super.tick(location);

        if (round < 3) {
            if (location.containsAnActor()) {
                if (!location.getActor().hasCapability(Status.IMMUNE_TO_BURN)) {
                    location.getActor().hurt(25);

                    if (!location.getActor().hasCapability(Status.IS_PLAYER) && !location.getActor().isConscious()) {
                        DyingAction dyingAction = new DyingAction(location, location.getActor().asSoul().getSouls(), null, location.getActor(), null, null);
                        dyingAction.execute(location.getActor(), location.map());
                        display.println(location.getActor() + " died in Burned Ground");
                    }
                }
            }
        }

        round++;
        if (round == 4){
            location.setGround(previousGround);
        }
    }
}
