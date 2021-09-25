package game;

import edu.monash.fit2099.engine.*;
import game.enums.Abilities;
import game.enums.Status;

/**
 * The class that handles the action when the Actor is dying.
 */
public class DyingAction extends Action {
    private Location location;
    private int soul;
    private Actor target;
    private Location previousLocation;
    private TokenOfSoul tokenOfSoul;
    private TokenOfSoul previousTokenOfSoul;
    private Boolean UndeadRandomDead;

    /**
     *
     * @param location
     * @param soul
     * @param previousLocation
     * @param target
     * @param tokenOfSoul
     * @param previousTokenOfSoul
     * Bind all the parameter with the class variable
     */
    public DyingAction(Location location,int soul,Location previousLocation,Actor target,TokenOfSoul tokenOfSoul,TokenOfSoul previousTokenOfSoul,Boolean UndeadRandomDead){
        this.previousLocation=previousLocation;
        this.location=location;
        this.soul=soul;
        this.tokenOfSoul=tokenOfSoul;
        this.previousTokenOfSoul=previousTokenOfSoul;
        this.target=target;
        this.UndeadRandomDead=UndeadRandomDead;
    }

    /**
     * Core of the class that handle few implementations of the dying mechanism:
     * When player dies, it will remove from the map and drop tokenOfSoul and also reset.
     * When enemy dies, it will transfer soul to the target(Player) and remove from map.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String indicate the current action.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_BLUE = "\u001B[34m";
        final String ANSI_YELLOW = "\u001B[33m";
        final String ANSI_RED = "\u001B[31m";

        Display display = new Display();
        String res = actor + " is dead";

        if (UndeadRandomDead) {
            map.removeActor(actor);
            return actor.toString() + " died instantly by chance";
        }

        if(actor.hasCapability(Status.IS_PLAYER) && target.hasCapability(Status.IS_PLAYER)) {
            display.println(ANSI_RED+
                    "------------    ------      ********   ------------        --------   ---    ---  ------------ -----------\n"+ANSI_RESET+
                    ANSI_BLUE+"************   ********    ----------  ************       **********  ***    ***  ************ ***********\n"+ANSI_RESET+
                    ANSI_BLUE+"----          ----------  ************ ----              ----    ---- ---    ---  ----         ----    ---\n"+ANSI_RESET+
                    ANSI_RED+"****  ****** ****    **** ---  --  --- ************      ***      *** ***    ***  ************ *********\n"+ANSI_RESET+
                    ANSI_RED+"----  ------ ------------ ***  **  *** ------------      ---      --- ---    ---  ------------ ---------\n"+ANSI_RESET+
                    ANSI_BLUE+"****    **** ************ ---  --  --- ****              ****    ****  ********   ****         ****  ****\n"+ANSI_RESET+
                    ANSI_BLUE+"------------ ----    ---- ***  **  *** ------------       ----------    ------    ------------ ----   ----\n"+ANSI_RESET+
                    ANSI_RED+"************ ****    **** ---      --- ************        ********      ****     ************ ****    ****"+ANSI_RESET);
            map.moveActor(actor, map.at(38, 12));
            if (previousTokenOfSoul != null) {
                previousTokenOfSoul.getLocation().removeItem(previousTokenOfSoul);
            }
            tokenOfSoul.setSouls(soul);
            if (location.getGround().hasCapability(Status.IS_VALLEY)) {
                tokenOfSoul.setLocation(previousLocation);
                previousLocation.addItem(tokenOfSoul);
            } else {
                tokenOfSoul.setLocation(location);
                location.addItem(tokenOfSoul);
            }
            actor.asSoul().subtractSouls(soul);

            ResetManager.getInstance().run();
        }
        else{
            target.asSoul().transferSouls(actor.asSoul());
            Actions dropActions = new Actions();
            // drop all items
            for (Item item : target.getInventory())
                dropActions.add(item.getDropAction(actor));
            for (Action drop : dropActions)
                drop.execute(target, map);
            map.removeActor(target);
            if (target.hasCapability(Status.IS_YHORM)){
                display.println(ANSI_YELLOW+
                        "----           --------   -----------  ----------          --------   ------------      ------------ --------  ----    ---- ----------   ------------ -----------\n"+
                        "****          **********  ***********  ************       **********  ************      ************ ********  *****   **** ************ ************ ***********\n"+
                        "----         ----    ---- ----    ---  --        --      ----    ---- ----              ---            ----    ------  ---- --        -- ----         ----    ---\n"+
                        "****         ***      *** *********    **        **      ***      *** ************      ***            ****    ************ **        ** ************ *********\n"+
                        "----         ---      --- ---------    --        --      ---      --- ------------      ---            ----    ------------ --        -- ------------ ---------\n"+
                        "************ ****    **** ****  ****   **        **      ****    **** ****              ***            ****    ****  ****** **        ** ****         ****  ****\n"+
                        "------------  ----------  ----   ----  ------------       ----------  ----              ------------ --------  ----   ----- ------------ ------------ ----   ----\n"+
                        "************   ********   ****    **** **********          ********   ****              ************ ********  ****    **** **********   ************ ****    ****\n"+ANSI_RESET);
            }
        }

        return res;
    }
    /**
     * Menu Description method
     * @param actor The actor performing the action.
     * @return null
     */
    @Override
    public String menuDescription(Actor actor) {
        return null;
    }


}

