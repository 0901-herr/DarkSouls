package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;

public class BuyGiantAxeAction extends BuyItemAction {
    public BuyGiantAxeAction(Actor vendor) {
        super(vendor, new GiantAxe(), 1000);
    }
}
