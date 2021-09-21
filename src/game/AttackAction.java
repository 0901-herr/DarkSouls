package game;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Weapon;
import game.enums.Abilities;
import game.enums.Status;

/**
 * Special Action for attacking other Actors.
 */
public class AttackAction extends Action {

	/**
	 * The Actor that is to be attacked
	 */
	protected Actor target;

	/**
	 * The direction of incoming attack.
	 */
	protected String direction;

	/**
	 * Random number generator
	 */
	protected Random rand = new Random();

	/**
	 * Constructor.
	 * 
	 * @param target the Actor to attack
	 */
	public AttackAction(Actor target, String direction) {
		this.target = target;
		this.direction = direction;
	}

	@Override
	public String execute(Actor actor, GameMap map) {
		Weapon weapon = actor.getWeapon();

		if (!(rand.nextInt(100) <= weapon.chanceToHit())) {
			return actor + " misses " + target + ".";
		}

		int damage = weapon.damage();
		String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
		target.hurt(damage);
		if (!target.isConscious()) {

			// Check if Actor is Skeleton
			// If yes, revive it with Revive Action
			// TODO: Find better implementation, currently we are using downcasting
			if (actor.hasCapability(Abilities.REVIVE_FOR_ONCE) && rand.nextBoolean()) {
				actor.heal(((Enemy) actor).getMaxHitPoints());

				// Remove ability
				actor.removeCapability(Abilities.REVIVE_FOR_ONCE);
				return result;
			}

			Actions dropActions = new Actions();
			// drop all items
			for (Item item : target.getInventory())
				dropActions.add(item.getDropAction(actor));
			for (Action drop : dropActions)
				drop.execute(target, map);

			// Transfer souls
			System.out.println(target + " transferring Souls to " + actor);
			target.asSoul().transferSouls(actor.asSoul());

			// remove actor
			//TODO: In A1 scenario, you must not remove a Player from the game yet. What to do, then?
			if (target.hasCapability(Status.HOSTILE_TO_PLAYER)) {
				map.removeActor(target);
			}
			result += System.lineSeparator() + target + " is killed.";
		}

		return result;
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + target + " at " + direction;
	}
}
