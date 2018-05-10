package com.agilewhisperers.bunnysnaketankmaze.entities;

import com.agilewhisperers.bunnysnaketankmaze.components.Body;
import com.agilewhisperers.bunnysnaketankmaze.components.Sprite;
import com.agilewhisperers.bunnysnaketankmaze.components.Stats;
import com.agilewhisperers.bunnysnaketankmaze.systems.*;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;


public class MrFrog extends GameObject implements Script, Collider {
   private boolean needReset;
   private boolean reseted = false;
   private float respawnTimer = 0;
   private float accumulator = 0;

   public MrFrog(float posX, float posY) {
      super();
      super.setBody(new Body(Physic.getObject().getWorld(), posX, posY, 1f, 0f, "MrFrog"));
      super.setSprite(new Sprite("gameObjects/NPC.atlas", "MrFrog", 1));
      getStats().setID("MrFrog");
      getStats().setExist(true);
      getBody().getBody().setType(BodyDef.BodyType.DynamicBody);
      //Add to scriptManager
      ScriptManager.getObject().addScriptListener(this);

      //Set tag for the object
      this.getBody().getBody().setUserData(this);
      getBody().getFixtureList().get(0).setUserData(getStats().getID());

      getBody().updateFilter(Physic.CATEGORY_PLAYER1, (short) -1);

   }


   /**
    * This method will be call every game loop.
    */
   @Override
   public void runObjectScript(float deltaTime) {
      this.getBody().getBody().setAngularVelocity(0);
      if (needReset) {
         if (!Physic.getObject().getWorld().isLocked()) {
            reset();
         }
      }

      if (reseted) {
         respawnTimer += deltaTime;
         if (respawnTimer > 5 && !Physic.getObject().getWorld().isLocked()) {
            ObjectFactory.getObject().respawnFrog();
            reseted = false;
            respawnTimer = 0;
         }
      } else {
         accumulator -= deltaTime;
         if (accumulator < 0) {
            movement();
            accumulator = 5f;
         }
      }
   }

   private void movement() {
      int rand = MathUtils.random(100);
      if (rand > 75) {
         getBody().getBody().setLinearVelocity(1, getBody().getBody().getLinearVelocity().y);
      } else if (rand > 50) {
         getBody().getBody().setLinearVelocity(getBody().getBody().getLinearVelocity().x, 1);
      } else if (rand > 25) {
         getBody().getBody().setLinearVelocity(-1, getBody().getBody().getLinearVelocity().y);
      } else {
         getBody().getBody().setLinearVelocity(getBody().getBody().getLinearVelocity().x, -1);
      }

   }


   @Override
   public void startCollision(Contact contact) {
      //getBody().updateFilter(Physic.CATEGORY_BULLET, (short) -1);
      com.badlogic.gdx.physics.box2d.Body body1, body2;
      if (contact.getFixtureA().getBody() == this.getBody().getBody()) {
         body1 = contact.getFixtureA().getBody();
         body2 = contact.getFixtureB().getBody();
      } else {
         body2 = contact.getFixtureA().getBody();
         body1 = contact.getFixtureB().getBody();
      }
      GameObject body = (GameObject) body2.getUserData();
      Stats stats = body.getStats();

      if (stats.getID().equals("Player")) {
         stats.setCurrentHP(100f);
         needReset = true;

      }
   }

   @Override
   public void endCollision(Contact contact) {

   }

   public void reset() {
      this.getBody().getBody().setTransform(-6, -7, 0);
      this.getBody().getBody().setLinearVelocity(Vector2.Zero);
      this.getBody().getBody().setAngularVelocity(0);
      needReset = false;
      reseted = true;
   }
}