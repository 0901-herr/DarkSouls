package game;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.enums.Abilities;
import game.enums.Status;

/**
 * The gorge or endless gap that is dangerous for the Player.
 */
public class Valley extends Ground {

	public Valley() {
		super('+');
	}
	/**
	 * FIXME: At the moment, the Player cannot enter it. It is boring.
	 * @param actor the Actor to check
	 * @return false or actor cannot enter.
	 */

	@Override
	public boolean canActorEnter(Actor actor){
		if (actor.hasCapability(Status.HOSTILE_TO_ENEMY)){
			return true;
		}
		else{
			return false;
		}
	}

	@Override
	public Actions allowableActions(Actor actor, Location location, String direction) {
		if (location.containsAnActor()){
			actor.hurt(Integer.MAX_VALUE);
		}
		return new Actions();
	}
}
