package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.WeaponItem;
import game.enums.Abilities;

import java.util.Random;

public class MeleeWeapon extends WeaponItem {

    protected Random rand = new Random();
    protected  int criticalStrikeRate;
    protected Display display = new Display();

    /**
     * Constructor.
     *
     * @param name        name of the item
     * @param displayChar character to use for display when item is on the ground
     * @param damage      amount of damage this weapon does
     * @param verb        verb to use for this weapon, e.g. "hits", "zaps"
     * @param hitRate     the probability/chance to hit the target.
     */
    public MeleeWeapon(String name, char displayChar, int damage, String verb, int hitRate) {
        super(name, displayChar, damage, verb, hitRate);
    }

    @Override
    public int damage(){
        int damage = this.damage;
        if (this.hasCapability(Abilities.CRITICAL_STRIKE)){
            if (rand.nextInt(100) <= criticalStrikeRate){
                damage *= 2;
                display.println("CRITICAL STRIKE !!!");
            }
        }
        return damage;
    }

    //TODO: please figure out how to disable dropping item action.
    @Override
    public DropItemAction getDropAction(Actor actor){
        return null;
    }
}
