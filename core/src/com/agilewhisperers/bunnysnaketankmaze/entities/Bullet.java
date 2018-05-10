package com.agilewhisperers.bunnysnaketankmaze.entities;

import com.agilewhisperers.bunnysnaketankmaze.components.Body;
import com.agilewhisperers.bunnysnaketankmaze.components.Sprite;
import com.agilewhisperers.bunnysnaketankmaze.components.Stats;
import com.agilewhisperers.bunnysnaketankmaze.systems.*;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.utils.Pool;

import static com.agilewhisperers.bunnysnaketankmaze.systems.Physic.*;

public class Bullet extends GameObject implements Script, Pool.Poolable, Collider {
   //In second
   private static final float max_Lifetime = 10f;
   private float lifetime = 0f;
   private boolean freed = false;
   private boolean isPlayer1;
   private boolean needToReset;

   public Bullet(boolean isPlayer1) {
      super();
      super.setBody(new Body(Physic.getObject().getWorld(), -10, -10, 1f, 0, "Carrot"));
      super.setSprite(new Sprite("gameObjects/Projectiles.atlas", "Carrot", 1));

      this.getBody().getBody().setType(BodyDef.BodyType.DynamicBody);

      getStats().setID("Bullet");
      getStats().setExist(true);

      //Add to scriptManager
      ScriptManager.getObject().addScriptListener(this);
      //Set tag for the object
      this.getBody().getBody().setUserData(this);
      this.getBody().getFixtureList().get(0).setUserData(getStats().getID());
      this.getBody().updateFilter(CATEGORY_BULLET, (short) -1);

      if (isPlayer1) {
         getBody().updateFilter(CATEGORY_BULLET, MASK_PLAYER1);
      } else {
         getBody().updateFilter(CATEGORY_BULLET, MASK_PLAYER2);
      }
      this.isPlayer1 = isPlayer1;
      needToReset = false;
   }


   public void update(float posX, float posY, float angle, float speed) {
      lifetime = 0;
      freed = false;
      this.getBody().setPosition(posX, posY);
      this.getBody().setAngle(angle);
      this.getBody().getBody().setLinearVelocity(
              MathUtils.cosDeg(angle) * speed,
              MathUtils.sinDeg(angle) * speed);


   }

   /**
    * This method will be call every game loop.
    *
    * @param deltaTime
    */
   @Override
   public void runObjectScript(float deltaTime) {
      resetThisObject();
      lifetime += deltaTime;
      if (lifetime >= max_Lifetime && !freed) {
         GameObjectManager.getObject().freeBullet(this, isPlayer1);
      }
   }


   /**
    * Resets the object for reuse. Object references should be nulled and fields may be set to default values.
    */
   @Override
   public void reset() {
      needToReset = true;
      freed = true;
   }

   @Override
   public void startCollision(Contact contact) {
      //Damage
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
         if (((Player) body).getiFrameTimer() < 0) {
            stats.setCurrentHP(stats.getCurrentHP() - (stats.getMaxHP() * 0.3f));
            GameObjectManager.getObject().freeBullet(this, isPlayer1);
            ((Player) body).startIFrame();
         }

      }


   }

   @Override
   public void endCollision(Contact contact) {

   }

   private void resetThisObject() {
      if (needToReset && !Physic.getObject().getWorld().isLocked()) {
         this.getBody().getBody().setLinearVelocity(Vector2.Zero);
         this.getBody().getBody().setAngularVelocity(0);
         this.getBody().setPosition(-10, -10);
         needToReset = false;
      }
   }


}

