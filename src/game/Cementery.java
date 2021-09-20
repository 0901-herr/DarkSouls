package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

import java.util.ArrayList;
import java.util.Random;

public class Cementery extends Ground {
    public Cementery(){
        super('c');
    }


    @Override
    public void tick(Location location){
        Random rand= new Random();
        if (rand.nextInt(100)<=25){
            location.map().addActor(new Undead("Undead"),location);
        }
    }
}
