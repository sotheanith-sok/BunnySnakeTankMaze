package com.agilewhisperers.bunnysnaketankmaze.systems;

import com.agilewhisperers.bunnysnaketankmaze.entities.Bullet;

public class BulletPoolForPlayer1 extends BulletPool {
   @Override
   protected Bullet newObject() {
      return new Bullet(true);
   }
}
