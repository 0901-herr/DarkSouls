package game;


import edu.monash.fit2099.engine.*;
import game.enums.Status;
import game.interfaces.Behaviour;
import game.interfaces.Resettable;
import game.interfaces.Soul;

import java.util.ArrayList;

/**
 * An undead minion.
 */
public class Undead extends Enemy implements Resettable {
	// Will need to change this to a collection if Undeads gets additional Behaviours.
	private int souls = 50;
	private Location location;

	/**
	 * Constructor.
	 * All Undeads are represented by an 'u' and have 30 hit points.
	 * @param name the name of this Undead
	 */
	public Undead(String name, Location location) {
		super(name, 'u', 50);
		getBehaviours().add(new WanderBehaviour());
		registerInstance();
		this.location = location;
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
		// create follow behaviour here
		// add followbehaviour in behaviour list
		Actions actions = new Actions();

		// it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
		if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
			// add behaviours for Undead
			addBehaviour(otherActor);

			// AttackAction to Player
			actions.add(new AttackAction(this, direction));
		}

		return actions;
	}

	@Override
	public void transferSouls(Soul soulObject) {
		soulObject.addSouls(getSouls());
		subtractSouls(getSouls());
	}

	@Override
	public int getSouls() {
		return souls;
	}

	@Override
	protected IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(20, "thwacks");
	}

	@Override
	public String toString() {
		String hitPointsStatus = "(" + getHitPoints() + "/" + getMaxHitPoints() + ")";
		String weaponStatus = "no weapon";
		String weaponStatusMessage = " (" +weaponStatus + ")";
		return (name + hitPointsStatus + weaponStatusMessage);
	}

	@Override
	public void resetInstance() {
		location.map().removeActor(this);
	}

	@Override
	public boolean isExist() {
		return true;
	}
}




