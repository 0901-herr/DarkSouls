package game;

import edu.monash.fit2099.engine.*;
import game.interfaces.Soul;

import java.util.ArrayList;
import java.util.List;

public class TokenOfSoul extends Item implements Soul {
    private Actor actor;
    private int soul;

    public TokenOfSoul(String name, char displayChar, boolean portable, Actor actor) {
        super(name, displayChar, portable);
        this.actor = actor;
    }

    @Override
    public List<Action> getAllowableActions() {
        List<Action> pickup= new ArrayList<Action>();
        pickup.add(new PickUpTokenAction(this));
        return pickup;
    }

    @Override
    public void transferSouls(Soul soulObject) {
        soulObject.addSouls(getSouls());
        subtractSouls(getSouls());
    }
    @Override
    public boolean subtractSouls(int soul) {
        boolean isSuccess = false;
        if (soul > 0) {
            this.soul -= soul;
            isSuccess = true;
        }
        else {
            isSuccess = false;
        }
        return isSuccess;
    }

    @Override
    public void setSouls(int souls) {
        this.soul=souls;
    }

    @Override
    public int getSouls() {
        return soul;
    }
}
