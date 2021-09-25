package game;

import edu.monash.fit2099.engine.*;
import game.enums.Abilities;
import game.enums.Status;

/**
 * The class that handles the action when the Actor is dying.
 */
public class DyingAction extends Action {
    private Display display;
    private Location location;
    private int soul;
    private Actor target;
    private Location previousLocation;
    private TokenOfSoul tokenOfSoul;
    private TokenOfSoul previousTokenOfSoul;
    public DyingAction(Location location,int soul,Location previousLocation,Actor target,TokenOfSoul tokenOfSoul,TokenOfSoul previousTokenOfSoul){
        this.previousLocation=previousLocation;
        this.location=location;
        this.soul=soul;
        this.tokenOfSoul=tokenOfSoul;
        this.previousTokenOfSoul=previousTokenOfSoul;
        this.target=target;
    }


    @Override
    public String execute(Actor actor, GameMap map) {
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_BLUE = "\u001B[34m";
        final String ANSI_YELLOW = "\u001B[33m";
        final String ANSI_RED = "\u001B[31m";
        map.moveActor(actor, map.at(38, 12));
        System.out.println(actor);
        System.out.println(target);

        if(target.hasCapability(Status.IS_PLAYER)|| actor.hasCapability(Status.IS_PLAYER)) {
            System.out.println(ANSI_RED+
                    "------------    ------      ********   ------------        --------   ---    ---  ------------ -----------\n"+ANSI_RESET+
                    ANSI_BLUE+"************   ********    ----------  ************       **********  ***    ***  ************ ***********\n"+ANSI_RESET+
                    ANSI_BLUE+"----          ----------  ************ ----              ----    ---- ---    ---  ----         ----    ---\n"+ANSI_RESET+
                    ANSI_RED+"****  ****** ****    **** ---  --  --- ************      ***      *** ***    ***  ************ *********\n"+ANSI_RESET+
                    ANSI_RED+"----  ------ ------------ ***  **  *** ------------      ---      --- ---    ---  ------------ ---------\n"+ANSI_RESET+
                    ANSI_BLUE+"****    **** ************ ---  --  --- ****              ****    ****  ********   ****         ****  ****\n"+ANSI_RESET+
                    ANSI_BLUE+"------------ ----    ---- ***  **  *** ------------       ----------    ------    ------------ ----   ----\n"+ANSI_RESET+
                    ANSI_RED+"************ ****    **** ---      --- ************        ********      ****     ************ ****    ****"+ANSI_RESET
            );
            map.moveActor(target, map.at(38, 12));
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
            System.out.println(target + " transferring soul to " + actor);
            map.removeActor(target);
            if (target.hasCapability(Status.IS_YHORM)){

                System.out.println(ANSI_YELLOW+
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

        return "dead";
    }

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }


}

