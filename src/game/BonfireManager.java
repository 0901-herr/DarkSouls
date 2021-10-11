package game;

import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

import java.util.ArrayList;
import java.util.HashMap;

public class BonfireManager {
    private HashMap<Location,Bonfire> bonfires;
    private static BonfireManager instance;
    public BonfireManager() {
        bonfires = new HashMap<Location,Bonfire>();
    }

    public static BonfireManager getInstance() {
        if(instance==null){
            instance=new BonfireManager();
        }
        return instance;
    }
    public Bonfire getBonfire(Location key){
        return bonfires.get(key);
    }
    public void addBonfire(){
        GameMap pMap= ProfaneCapital.getInstance();
        GameMap aMap= AnorLondo.getInstance();
        Bonfire pBn = new Bonfire("Firelink Shrine");
        pMap.at(38,11).setGround(pBn);
        bonfires.put(pMap.at(38,11),pBn);
        Bonfire aBn = new Bonfire("AnorLondo's Bonfire");
        aMap.at(22,10).setGround(aBn);
        bonfires.put(aMap.at(22,10),aBn);

    }

    public HashMap<Location, Bonfire> getBonfires() {
        return bonfires;
    }
}
