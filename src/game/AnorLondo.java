package game;

import edu.monash.fit2099.engine.FancyGroundFactory;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.GroundFactory;

import java.util.Arrays;
import java.util.List;

public class AnorLondo extends GameMap {
    private static AnorLondo instance;
    private AnorLondo() {
        super(new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Valley(),new FogDoor())
                , Arrays.asList(
                        "___________###........................................+..............+++++..",
                        "___________###........................................+.................++..",
                        "___________###........................................+.................++..",
                        "___________###..............+.........................+.................+...",
                        "..############...............+++.......++++...........+.....................",
                        ".............................++.......+...............+......++++...........",
                        ".........................####################........._......+++++..........",
                        ".........................##_______+________##.........+.......+.............",
                        ".........................##_______+________##................+++............",
                        "...........++............##_______+________##.........+.......+.............",
                        ".........+++.............##_______+________##.........+........++...........",
                        "............+++..........##________________##....................+..........",
                        "..............+..........########____########.......+............++.........",
                        "++...+++............................................+........++++...........",
                        "+++......................................+++........+...........+.++........",
                        "++++....+++++++.........................++..........+..........+....++......",
                        "#####___#####++++......................+............+..............+..+.....",
                        "_..._....._._#.++...................................+..................+....",
                        "...+.__..+...#+++...................................+...................+...",
                        "...+.....+._.#.+.....+++++...++....................._....................++.",
                        "___.......___#.++++++++++++++.+++...................+.....................++"));
    }

    public static AnorLondo getInstance() {
        if (instance==null) {
            instance=new AnorLondo();
        }
        return instance;
    }


}
