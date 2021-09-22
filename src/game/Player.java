package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Menu;
import game.enums.Abilities;
import game.enums.Status;
import game.interfaces.Resettable;
import game.interfaces.Soul;

/**
 * Class representing the Player.
 */
public class Player extends Actor implements Soul, Resettable {

	private final Menu menu = new Menu();
	private int souls = 100;
	private int requiredSouls = 0;
	private int maxHp;

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
		this.maxHp=hitPoints;
		this.addCapability(Abilities.IS_PLAYER);
		this.addCapability(Status.HOSTILE_TO_ENEMY);
		this.addCapability(Status.ABLE_TO_BUY);
		this.addCapability(Abilities.REST);

		this.addItemToInventory(new BroadSword());
//		this.addItemToInventory(new GiantAxe());

		this.addItemToInventory(new EstusFlask(this));
		this.addItemToInventory(new EstusFlask(this));
		this.addItemToInventory(new EstusFlask(this));
	}

	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		if (!isConscious()){
			registerInstance();
			return new DyingAction(this,map.locationOf(this),souls);
		}
		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();

		// TODO: Change weapon name
		String holdWeaponMessage = ", holding " + getWeapon();
		String soulCountMessage = ", " + getSouls() + " souls";

		display.println(name + " (" + hitPoints + "/" + getMaxHp() + ")" + holdWeaponMessage + soulCountMessage);

		// return/print the console menu
		return menu.showMenu(this, actions, display);
	}

	@Override
	public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
		Actions actions = new Actions();
		// it can be attacked only by the HOSTILE opponent.
		if(otherActor.hasCapability(Status.HOSTILE_TO_PLAYER)) {
			actions.add(new AttackAction(this,direction));
			System.out.println("Player send AttackAction to " + otherActor);
		}
		return actions;
	}

	@Override
	public void transferSouls(Soul soulObject) {
		soulObject.addSouls(requiredSouls);
		subtractSouls(requiredSouls);
	}

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

	@Override
	public void setRequiredSouls(int requiredSouls) {
		this.requiredSouls = requiredSouls;
	}

	@Override
	public void setSouls(int souls) {
		this.souls = souls;
	}

	@Override
	public int getSouls() {
		return souls;
	}

	public int getMaxHp() {
		return maxHp;
	}

	@Override
	public void resetInstance() {
		this.hitPoints=100;
		this.souls=0;

	}

	@Override
	public boolean isExist() {
		return true;
	}
}
