package com.agilewhisperers.bunnysnaketankmaze.entities;

import com.agilewhisperers.bunnysnaketankmaze.components.Body;
import com.agilewhisperers.bunnysnaketankmaze.components.Sprite;
import com.agilewhisperers.bunnysnaketankmaze.systems.GameObjectManager;
import com.agilewhisperers.bunnysnaketankmaze.systems.Physic;
import com.agilewhisperers.bunnysnaketankmaze.systems.Renderer;

public class Bullet extends GameObject {
   public Bullet(){
      super(false,new Sprite("game/bullet.png"),new Body(Physic.getObject().getWorld(),0,0,10,10));
      GameObjectManager.getObject().addGameObject(this);
      this.getSprite().getSprite().flip(false,true);
   }
}
