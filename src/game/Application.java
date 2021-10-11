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

			GameMap gameMap = new ProfaneCapital();
			world.addGameMap(gameMap);
			GameMap secMap=new AnorLondo();
			world.addGameMap(secMap);

			Actor player = new Player("Unkindled (Player)", '@', 1000);
			world.addPlayer(player, gameMap.at(38, 12));
//			world.addPlayer(player, gameMap.at(8, 25));

			//Place Yhorm the Giant/boss in the map
			Location YhormInitialLocation = gameMap.at(6, 25);
			Enemy Yhorm = new Yhorm("Yhorm the Giant", 'Y', 500, YhormInitialLocation);
			YhormInitialLocation.addActor(Yhorm);

			// Place Storm Ruler in the map
			gameMap.at(7, 25).addItem(new StormRuler(player));

			// Place Cemeteries in the map
			gameMap.at(32, 7).setGround(new Cementery());
			gameMap.at(27, 14).setGround(new Cementery());
			gameMap.at(49, 12).setGround(new Cementery());
			gameMap.at(58, 5).setGround(new Cementery());
			gameMap.at(36, 15).setGround(new Cementery());

			// Place Skeleton
			Location skeleton1InitialLocation = gameMap.at(50, 15);
			Enemy skeleton1 = new Skeleton("Skeleton", skeleton1InitialLocation);
			skeleton1InitialLocation.addActor(skeleton1);

			Location skeleton2InitialLocation = gameMap.at(20, 4);
			Enemy skeleton2 = new Skeleton("Skeleton", skeleton2InitialLocation);
			skeleton2InitialLocation.addActor(skeleton2);

			Location skeleton3InitialLocation = gameMap.at(45, 18);
			Enemy skeleton3 = new Skeleton("Skeleton", skeleton3InitialLocation);
			skeleton3InitialLocation.addActor(skeleton3);

			Location skeleton4InitialLocation = gameMap.at(22, 16);
			Enemy skeleton4 = new Skeleton("Skeleton", skeleton4InitialLocation);
			skeleton4InitialLocation.addActor(skeleton4);

			Location skeleton5InitialLocation = gameMap.at(44, 8);
			Enemy skeleton5 = new Skeleton("Skeleton", skeleton5InitialLocation);
			skeleton5InitialLocation.addActor(skeleton5);

			Location skeleton6InitialLocation = gameMap.at(34, 6);
			Enemy skeleton6 = new Skeleton("Skeleton", skeleton6InitialLocation);
			skeleton6InitialLocation.addActor(skeleton6);

			gameMap.at(38,16).setGround(new FogDoor(secMap));

			// Place Bonfire at the center of the map
			gameMap.at(38,11).setGround(new Bonfire("Firelink Shrine"));

			// Place Fire Keeper (Vendor) besides Bonfire
			gameMap.at(37,11).addActor(new Vendor());

			//Place Yhorm the Giant/boss in the map
			Location AldrichInitialLocation = secMap.at(32, 11);
			Enemy Aldrich = new Aldrich("Aldrich the Devourer", 'A', 350, AldrichInitialLocation);
			AldrichInitialLocation.addActor(Aldrich);

			// run the game
			world.run();
	}
}
