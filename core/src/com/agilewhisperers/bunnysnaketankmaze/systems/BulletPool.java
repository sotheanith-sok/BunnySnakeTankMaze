package com.agilewhisperers.bunnysnaketankmaze.systems;

import com.agilewhisperers.bunnysnaketankmaze.entities.Bullet;
import com.badlogic.gdx.utils.Pool;


public abstract class BulletPool extends Pool<Bullet> {

   /**
    * Create bullet pool with varaibles
    *
    * @param init :initial number of object in pool.
    * @param max  : maximum number of object in pool.
    */
   public BulletPool(int init, int max) {
      super(init, max);
   }

   /**
    * Create bullet pool with initial number of object set to 16 and no max number of object.
    */
   public BulletPool() {
      super();
   }


}
