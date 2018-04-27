package com.agilewhisperers.bunnysnaketankmaze.entities;

import com.agilewhisperers.bunnysnaketankmaze.components.Body;
import com.agilewhisperers.bunnysnaketankmaze.components.Sprite;
import com.agilewhisperers.bunnysnaketankmaze.systems.Collider;
import com.agilewhisperers.bunnysnaketankmaze.systems.CollisionManager;
import com.agilewhisperers.bunnysnaketankmaze.systems.Physic;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;

public class Wall extends GameObject implements Collider {
   public Wall(float posX, float posY) {
      super(new Sprite(
              "gameObjects/Environment.atlas", "Wall", 1
      ), new Body(Physic.getObject().getWorld(), posX, posY, 1, 0, "Wall"));
      this.getBody().getBody().setType(BodyDef.BodyType.KinematicBody);
      getStats().setID("Wall");
      getStats().setExist( true);
      this.getBody().getBody().setUserData(getStats());
      this.getBody().getFixtureList().get(0).setUserData(getStats().getID());


      CollisionManager.getObject().addCollider(this);

      this.getBody().updateFilter(Physic.CATEGORY_ENVIROMENT, (short) -1);

   }

   @Override
   public void startCollision(Contact contact) {

   }

   @Override
   public void endCollision(Contact contact) {

   }
   @Override
   public com.badlogic.gdx.physics.box2d.Body getBodyForCollisionTesting() {
      return getBody().getBody();
   }




}
