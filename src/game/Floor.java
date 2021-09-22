package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import game.enums.Abilities;

/**
 * A class that represents the floor inside a building.
 */
public class Floor extends Ground {

	public Floor() {
		super('_');
	}

	@Override
	public boolean canActorEnter(Actor actor) {
		if(actor.hasCapability(Abilities.IS_PLAYER)){
			return true;
		}
		else{
			return false;
		}
	}
}
