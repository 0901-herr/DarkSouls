package game;

import edu.monash.fit2099.engine.*;
import game.enums.Abilities;
import game.enums.Status;
import game.interfaces.Resettable;
import game.interfaces.Soul;

/**
 * Class representing the Player.
 */
public class Player extends Actor implements Soul, Resettable {

	private final Menu menu = new Menu();
	private int souls = 1500;
	private int requiredSouls = 0;
	private Location previousLocation;
	private TokenOfSoul previousTokenOfSoul;
	private TokenOfSoul ts;

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
		this.maxHitPoints = hitPoints;
		this.addCapability(Status.IS_PLAYER);
		this.addCapability(Status.HOSTILE_TO_ENEMY);
		this.addCapability(Status.ABLE_TO_BUY);
		this.addCapability(Abilities.REST);

		this.addItemToInventory(new BroadSword());
		this.addItemToInventory(new GiantAxe());

		this.addItemToInventory(new EstusFlask(this));
		registerInstance();

	}

	/**
	 * Every single playturn will call this method to get the player's Action
	 * @param actions    collection of possible Actions for this Actor
	 * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
	 * @param map        the map containing the Actor
	 * @param display    the I/O object to which messages may be written
	 * @return the action object that player need to perform in this play turn.
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		if (!isConscious()){
			System.out.println("dead");
			ts= new TokenOfSoul("Token of Soul",'$',false,this);
			return new DyingAction(map.locationOf(this),souls,previousLocation,this,ts,previousTokenOfSoul);
		}
		this.previousTokenOfSoul=ts;
		this.previousLocation=map.locationOf(this);
		// Handle multi-turn Actions

		if (lastAction.getNextAction() != null) {
			return lastAction.getNextAction();
		}
		// TODO: Change weapon name
		String holdWeaponMessage = ", holding " + getWeapon();
		String soulCountMessage = ", " + getSouls() + " souls";

		display.println(name + " (" + hitPoints + "/" + getMaxHp() + ")" + holdWeaponMessage + soulCountMessage);

		// return/print the console menu
		return menu.showMenu(this, actions, display);
	}

	/**
	 *
	 * @param otherActor the Actor that might be performing attack
	 * @param direction  String representing the direction of the other Actor
	 * @param map        current GameMap
	 * @return
	 */
	@Override
	public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
		Actions actions = new Actions();
		// it can be attacked only by the HOSTILE opponent.
		if(otherActor.hasCapability(Status.HOSTILE_TO_PLAYER)) {
			actions.add(new AttackAction(this,direction));
		}
		return actions;
	}

	/**
	 *
	 * @param soulObject a target souls.
	 */
	@Override
	public void transferSouls(Soul soulObject) {
		soulObject.addSouls(requiredSouls);
		subtractSouls(requiredSouls);
	}

	/**
	 *
	 * @param souls number of souls to be incremented.
	 * @return
	 */
	@Override
	public boolean addSouls(int souls) {
		boolean isSuccess = false;
		if (souls > 0) {
			this.souls += souls;
			isSuccess = true;
		}
		else {
			isSuccess = false;
		}
		return isSuccess;
	}

	/**
	 *
	 * @param souls number souls to be deducted
	 * @return
	 */
	@Override
	public boolean subtractSouls(int souls) {
		boolean isSuccess = false;
		if (souls > 0) {
			this.souls -= souls;
			isSuccess = true;
		}
		else {
			isSuccess = false;
		}
		return isSuccess;
	}

	/**
	 *
	 * @param requiredSouls
	 */
	@Override
	public void setRequiredSouls(int requiredSouls) {
		this.requiredSouls = requiredSouls;
	}

	/**
	 *
	 * @param souls
	 */
	@Override
	public void setSouls(int souls) {
		this.souls = souls;
	}

	/**
	 *
	 * @return
	 */
	@Override
	public int getSouls() {
		return souls;
	}

	/**
	 *
	 * @return
	 */
	public int getMaxHp() {
		return maxHitPoints;
	}

	/**
	 *
	 */
	@Override
	public void resetInstance() {
		this.hitPoints=100;

	}

	/**
	 *
	 * @return
	 */
	@Override
	public boolean isExist() {
		return true;
	}
}
