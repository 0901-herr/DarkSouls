package game;

import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

import java.util.ArrayList;
import java.util.HashMap;

public class BonfireManager {
    private HashMap<Location,Bonfire> bonfires;
    private static BonfireManager instance;
    private Location lastInteract;
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
        Bonfire pBn = new Bonfire("Firelink Shrine",true);
        pMap.at(38,11).setGround(pBn);
        bonfires.put(pMap.at(38,11),pBn);
        this.lastInteract=pMap.at(38,12);
        Bonfire aBn = new Bonfire("AnorLondo's Bonfire",false);
        aMap.at(22,10).setGround(aBn);
        bonfires.put(aMap.at(22,10),aBn);

    }

    public HashMap<Location, Bonfire> getBonfires() {
        return bonfires;
    }

    public void updateLastInteract(Location location){
        this.lastInteract=location;
    }
    public Location getLastInteract(){
        return this.lastInteract;
    }
}
