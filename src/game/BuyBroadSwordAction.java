package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;

public class BuyBroadSwordAction extends BuyItemAction {
    public BuyBroadSwordAction(Actor vendor) {
        super(vendor, new BroadSword(), 500);
    }
}
