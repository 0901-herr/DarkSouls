package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Weapon;
import game.interfaces.Soul;

public abstract class Enemy extends Actor implements Soul {
    public Enemy(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
    }

    @Override
    public void transferSouls(Soul soulObject) {
        //TODO: transfer Enemy's souls to another Soul's instance.
    }
}


