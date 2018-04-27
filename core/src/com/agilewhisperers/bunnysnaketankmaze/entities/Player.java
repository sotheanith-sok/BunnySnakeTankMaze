package com.agilewhisperers.bunnysnaketankmaze.entities;

import com.agilewhisperers.bunnysnaketankmaze.components.Body;
import com.agilewhisperers.bunnysnaketankmaze.components.PlayerAnimator;
import com.agilewhisperers.bunnysnaketankmaze.systems.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;

public class Player extends GameObject implements Script, Collider {

   float rateTimer = 0;
   float reloadTimer = 0;
    float capacityCounter = 0;

   private float startRustTimer = 0;
   private float rustTime = 0;


   public Player(float posX, float posY) {
      super();
      super.setBody(new Body(Physic.getObject().getWorld(), posX, posY, 1f, 0.8f, "Player"));
      super.setAnimator(new PlayerAnimator());
      getStats().setID("Player");
      getStats().setExist(true);
      this.getBody().getBody().setType(BodyDef.BodyType.DynamicBody);
      //Add to scriptManager
      ScriptManager.getObject().addScriptListener(this);

      //Set tag for the object
      this.getBody().getBody().setUserData(getStats());
      this.getBody().getFixtureList().get(0).setUserData(getStats().getID());

      CollisionManager.getObject().addCollider(this);

      this.getBody().updateFilter(Physic.CATEGORY_PLAYER1, (short) -1);

   }



   /**
    * This method will be call every game loop.
    */
   @Override
   public void runObjectScript() {
      movement();
      fire();
      //rust();
   }

   public void movement() {
      if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
         this.getBody().addAngle(getStats().getRotatingSpeed());
         ((PlayerAnimator) getAnimator()).updateState(PlayerAnimator.State.ROTATE_COUNTERCLOCKWISE);
      } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
         this.getBody().addAngle(-getStats().getRotatingSpeed());
         ((PlayerAnimator) getAnimator()).updateState(PlayerAnimator.State.ROTATE_CLOCKWISE);
      } else {
         this.getBody().getBody().setAngularVelocity(0);
         ((PlayerAnimator) getAnimator()).updateState(PlayerAnimator.State.STANDING);

      }

      if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
         this.getBody().getBody().setLinearVelocity(MathUtils.cosDeg(this.getBody().getAngle()) * getStats().getMovingSpeed(), MathUtils.sinDeg(this.getBody().getAngle()) * getStats().getMovingSpeed());

      } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
         this.getBody().getBody().setLinearVelocity(MathUtils.cosDeg(this.getBody().getAngle()) * -getStats().getMovingSpeed(), MathUtils.sinDeg(this.getBody().getAngle()) * -getStats().getMovingSpeed());
      } else {
         this.getBody().getBody().setLinearVelocity(0, 0);
      }
   }

   public void fire() {
      //Normal Fire
      rateTimer += Gdx.graphics.getDeltaTime();
      if ((capacityCounter <= getStats().getCapacity()) && rateTimer > 1 / getStats().getRPS() && Gdx.input.isKeyPressed(Input.Keys.M)) {
         GameObjectManager.getObject().getBullet().update(this.getBody().getBody().getPosition().x,
                 this.getBody().getBody().getPosition().y,
                 this.getBody().getAngle(), getStats().getBulletSpeed(),true);
         rateTimer = 0;
         capacityCounter++;
      }

      //Reload
      if (capacityCounter > getStats().getCapacity()) {
         reloadTimer += Gdx.graphics.getDeltaTime();
         if (reloadTimer >= getStats().getReloadTime()) {
            capacityCounter = 0;
            reloadTimer = 0;
         }
      }
   }


   @Override
   public void startCollision(Contact contact) {

   }

   @Override
   public void endCollision(Contact contact) {

   }



   public void rust(){
        if(this.getBody().getBody().getAngularVelocity() == 0 && this.getBody().getBody().getLinearVelocity().x == 0 && this.getBody().getBody().getLinearVelocity().y ==0){
                startRustTimer += Gdx.graphics.getDeltaTime();
            }else{
               startRustTimer  = 0;
             }
           if(startRustTimer > 3){
                rustTime += Gdx.graphics.getDeltaTime();
                 if(rustTime > 1){
                       getStats().setCurrentHP(getStats().getCurrentHP()-(getStats().getMaxHP() * 5f/100f));
                       rustTime = 0;
                    }
              }

                 }

   @Override
   public com.badlogic.gdx.physics.box2d.Body getBodyForCollisionTesting() {
      return getBody().getBody();
   }
}
