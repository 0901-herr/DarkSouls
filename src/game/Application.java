package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.FancyGroundFactory;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.World;

/**
 * The main class for the Jurassic World game.
 *
 */
public class Application {

	public static void main(String[] args) {

			World world = new World(new Display());

			FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Valley());

			List<String> map = Arrays.asList(
					"..++++++..+++...........................++++......+++.................+++.......",
					"........+++++..............................+++++++.................+++++........",
					"...........+++.......................................................+++++......",
					"........................................................................++......",
					".........................................................................+++....",
					"............................+.............................................+++...",
					".............................+++.......++++.....................................",
					".............................++.......+......................++++...............",
					".............................................................+++++++............",
					"..................................###___###...................+++...............",
					"..................................#_______#......................+++............",
					"...........++.....................#_______#.......................+.............",
					".........+++......................#_______#........................++...........",
					"............+++...................####_####..........................+..........",
					"..............+......................................................++.........",
					"..............++.................................................++++++.........",
					"............+++...................................................++++..........",
					"+..................................................................++...........",
					"++...+++.........................................................++++...........",
					"+++......................................+++........................+.++........",
					"++++.......++++.........................++.........................+....++......",
					"#####___#####++++......................+...............................+..+.....",
					"_..._....._._#.++......................+...................................+....",
					"...+.__..+...#+++...........................................................+...",
					"...+.....+._.#.+.....+++++...++..............................................++.",
					"___.......___#.++++++++++++++.+++.............................................++");
			GameMap gameMap = new GameMap(groundFactory, map);
			world.addGameMap(gameMap);

			Actor player = new Player("Unkindled (Player)", '@', 100);
			world.addPlayer(player, gameMap.at(38, 12));

			// Place Yhorm the Giant/boss in the map
			gameMap.at(6, 25).addActor(new Yhorm("Yhorm the Giant", 'Y', 20));
			gameMap.at(7, 25).addItem(new StormRuler(player));

			// Place a Hollow in the the map
			// FIXME: the Undead should be generated from the Cemetery
			gameMap.at(32, 7).setGround(new Cementery());
			gameMap.at(27, 14).setGround(new Cementery());
			gameMap.at(49, 12).setGround(new Cementery());
			gameMap.at(58, 5).setGround(new Cementery());
			gameMap.at(36, 20).setGround(new Cementery());

			// Place Skeleton
			gameMap.at(50, 15).addActor(new Skeleton("Skeleton"));
			gameMap.at(20, 4).addActor(new Skeleton("Skeleton"));
			gameMap.at(45, 18).addActor(new Skeleton("Skeleton"));
			gameMap.at(12, 20).addActor(new Skeleton("Skeleton"));
			gameMap.at(22, 16).addActor(new Skeleton("Skeleton"));
			gameMap.at(25, 10).addActor(new Skeleton("Skeleton"));

			// Place Bonfire at the center of the map
			gameMap.at(37,11).setGround(new Bonfire());

			// Place Fire Keeper (Vendor) besides Bonfire
			gameMap.at(38,11).addActor(new Vendor());

			// run the game
			world.run();
	}
}
