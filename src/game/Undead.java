package game;


import edu.monash.fit2099.engine.*;

import java.util.Random;


/**
 * An undead minion.
 */
public class Undead extends Enemy {
	Random rand = new Random();

	/**
	 * Constructor.
	 * All Undeads are represented by an 'u' and have 30 hit points.
	 * @param name the name of this Undead
	 */
	public Undead(String name, Location initialLocation) {
		super(name, 'u', 50, initialLocation, 50);
		this.getBehaviours().add(new WanderBehaviour());
		this.setInitialLocation(initialLocation);
	}

	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		// 10% chance to die
//		if (rand.nextInt(10)==1){
//			resetInstance();
//		}

		// loop through all behaviours
		for(game.interfaces.Behaviour Behaviour : getBehaviours()) {
			Action action = Behaviour.getAction(this, map);
			if (action != null)
				return action;
		}
		return new DoNothingAction();
	}

	@Override
	public void resetInstance() {
		this.getBehaviours().clear();
		this.setFollowBehaviour(null);
		this.getInitialLocation().map().removeActor(this);
	}

	@Override
	protected IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(20, "thwacks");
	}

	@Override
	public String toString() {
		String hitPointsStatus = "(" + getHitPoints() + "/" + getMaxHitPoints() + ")";
		String weaponStatus = "no weapon";
		String weaponStatusMessage = " (" + weaponStatus + ")";
		return (name + hitPointsStatus + weaponStatusMessage);
	}
}




