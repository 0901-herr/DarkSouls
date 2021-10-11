package game;

import edu.monash.fit2099.engine.FancyGroundFactory;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.GroundFactory;

import java.util.Arrays;
import java.util.List;

public class ProfaneCapital extends GameMap {
    private static ProfaneCapital instance;
    private ProfaneCapital() {
        super(new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Valley(),new FogDoor())
                , Arrays.asList(
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
                        "___.......___#.++++++++++++++.+++.............................................++"));

    }

    public static ProfaneCapital getInstance() {
        if (instance==null){
            instance=new ProfaneCapital();
        }
        return instance;
    }
}
