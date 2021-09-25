package game;

import game.enums.Abilities;

public class BroadSword extends MeleeWeapon{

    /**
     * Constructor
     */
    public BroadSword() {
        super("Broadsword", 'B', 30, "slashes", 80);
        this.addCapability(Abilities.CRITICAL_STRIKE);
        this.criticalStrikeRate = 20;
    }

}

