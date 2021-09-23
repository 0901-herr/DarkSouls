package game;

import edu.monash.fit2099.engine.*;
import game.enums.Abilities;
import game.enums.Status;

public class DyingAction extends Action {
    private Location location;
    private int soul;
    private Actor target;
    private Location previousLocation;
    public DyingAction(Location location,int soul,Location previousLocation,Actor target){
        this.previousLocation=previousLocation;
        this.location=location;
        this.soul=soul;
        this.target=target;
    }
    @Override
    public String execute(Actor actor, GameMap map) {
        System.out.println(actor);
        System.out.println(target);
        if(target==null){
            System.out.println("I'm Dead");
            map.moveActor(actor,map.at(38, 12));
            TokenOfSoul ts=new TokenOfSoul("Token Of Soul",'$',false,actor);
            ts.setSouls(soul);
            if (location.getGround().hasCapability(Status.IS_VALLEY)){
                previousLocation.addItem(ts);
            }
            else{
                location.addItem(ts);
            }
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
                final String ANSI_RESET = "\u001B[0m";
                final String ANSI_YELLOW = "\u001B[33m";
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

        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }


}

