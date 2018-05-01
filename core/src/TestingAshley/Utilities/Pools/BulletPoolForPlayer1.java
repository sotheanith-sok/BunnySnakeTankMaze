package TestingAshley.Utilities.Pools;


import TestingAshley.Entities.Bullet;
import TestingAshley.Utilities.ObjectFactory;

public class BulletPoolForPlayer1 extends BulletPool {
    @Override
    protected Bullet newObject() {
        Bullet bullet=new Bullet(ObjectFactory.getObject().getWorld(),-10,-10,true);
        ObjectFactory.getObject().getEngine().addEntity(bullet);
        return bullet;
    }
}
