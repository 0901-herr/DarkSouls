package game;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

/**
 * The gorge or endless gap that is dangerous for the Player.
 */
public class Valley extends Ground {

	public Valley() {
		super('+');
	}

	@Override
	public Actions allowableActions(Actor actor, Location location, String direction) {
		return super.allowableActions(actor, location, direction);
	}

	/**
	 * FIXME: At the moment, the Player cannot enter it. It is boring.
	 * @param actor the Actor to check
	 * @return false or actor cannot enter.
	 */

	@Override
	public boolean canActorEnter(Actor actor){
		actor.hurt(10);
		return true;
	}
}
