package game;


import edu.monash.fit2099.engine.*;
import game.enums.Status;

import java.util.Random;


/**
 * An undead minion.
 */
public class Undead extends Enemy {
	private Location location;
	int souls = 50;
	Random rand = new Random();

	/**
	 * Constructor.
	 * All Undeads are represented by an 'u' and have 30 hit points.
	 * @param name the name of this Undead
	 */
	public Undead(String name, Location location) {
		super(name, 'u', 50, location);
		getBehaviours().add(new WanderBehaviour());
		registerInstance();
		this.location = location;
		setSouls(this.souls);
	}

	/**
	 * At the moment, we only make it can be attacked by enemy that has HOSTILE capability
	 * You can do something else with this method.
	 * @param otherActor the Actor that might be performing attack
	 * @param direction  String representing the direction of the other Actor
	 * @param map        current GameMap
	 * @return list of actions
	 * @see Status#HOSTILE_TO_ENEMY
	 */
	@Override
	public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
		Actions actions = new Actions();

		// it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
		if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
			// add behaviours for Undead
			addBehaviour(otherActor);

			// add AttackAction for Player
			actions.add(new AttackAction(this, direction));
		}

		return actions;
	}

	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		if (getIsReset()) {
			setReset(false);
			return new DoNothingAction();
		}
		Random rand= new Random();
		if(this.hasCapability(Status.HOSTILE_TO_PLAYER)){
			if (rand.nextInt(10)==1){
				resetInstance();
		}}
//		// 10% chance to die
//		else if (rand.nextInt(10) == 1) {
//			map.removeActor(this);
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
	public void addBehaviour(Actor otherActor) {
		if (getFollowBehaviour() == null) {
			// Add AttackBehaviour
			getBehaviours().add(0, new AttackBehaviour());

			// add FollowBehaviour
			setFollowBehaviour(new FollowBehaviour(otherActor));
			getBehaviours().add(1, getFollowBehaviour());
		}
	}

	@Override
	public void resetInstance() {
		this.getBehaviours().clear();
		this.setFollowBehaviour(null);
		location.map().removeActor(this);
	}

	@Override
	public boolean isExist() {
		return true;
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




