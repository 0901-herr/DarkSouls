package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.FancyGroundFactory;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.World;
import edu.monash.fit2099.engine.Location;

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

			 //Place Yhorm the Giant/boss in the map
			Location YhormInitialLocation = new Location(gameMap, 6, 25);
			Enemy Yhorm = new Yhorm("Yhorm the Giant", 'Y', 150, YhormInitialLocation);
			gameMap.at(6, 25).addActor(Yhorm);
			YhormInitialLocation.setGround(gameMap.locationOf(Yhorm).getGround());

			// Place Storm Ruler in the map
			gameMap.at(7, 25).addItem(new StormRuler(player));

			// Place a Hollow in the the map
			gameMap.at(32, 7).setGround(new Cementery());
			gameMap.at(27, 14).setGround(new Cementery());
			gameMap.at(49, 12).setGround(new Cementery());
			gameMap.at(58, 5).setGround(new Cementery());
			gameMap.at(36, 15).setGround(new Cementery());

			// Place Skeleton
			Location skeleton1InitialLocation = new Location(gameMap, 50, 15);
			Enemy skeleton1 = new Skeleton("Skeleton", skeleton1InitialLocation);
			gameMap.at(50, 15).addActor(skeleton1);
			skeleton1InitialLocation.setGround(gameMap.locationOf(skeleton1).getGround());

			Location skeleton2InitialLocation = new Location(gameMap, 20, 4);
			Enemy skeleton2 = new Skeleton("Skeleton", skeleton2InitialLocation);
			gameMap.at(20, 4).addActor(skeleton2);
			skeleton2InitialLocation.setGround(gameMap.locationOf(skeleton2).getGround());

			Location skeleton3InitialLocation = new Location(gameMap, 45, 18);
			Enemy skeleton3 = new Skeleton("Skeleton", skeleton3InitialLocation);
			gameMap.at(45, 18).addActor(skeleton3);
			skeleton3InitialLocation.setGround(gameMap.locationOf(skeleton3).getGround());

			Location skeleton4InitialLocation = new Location(gameMap, 22, 16);
			Enemy skeleton4 = new Skeleton("Skeleton", skeleton4InitialLocation);
			gameMap.at(22, 16).addActor(skeleton4);
			skeleton4InitialLocation.setGround(gameMap.locationOf(skeleton4).getGround());

			Location skeleton5InitialLocation = new Location(gameMap, 44, 8);
			Enemy skeleton5 = new Skeleton("Skeleton", skeleton5InitialLocation);
			gameMap.at(44, 8).addActor(skeleton5);
			skeleton5InitialLocation.setGround(gameMap.locationOf(skeleton5).getGround());

			Location skeleton6InitialLocation = new Location(gameMap, 34, 6);
			Enemy skeleton6 = new Skeleton("Skeleton", skeleton6InitialLocation);
			gameMap.at(34, 6).addActor(skeleton6);
			skeleton6InitialLocation.setGround(gameMap.locationOf(skeleton6).getGround());

			// Place Bonfire at the center of the map
			gameMap.at(38,11).setGround(new Bonfire());

			// Place Fire Keeper (Vendor) besides Bonfire
			gameMap.at(37,11).addActor(new Vendor());

			// run the game
			world.run();
	}
}
