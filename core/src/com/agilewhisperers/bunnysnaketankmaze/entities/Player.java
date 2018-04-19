package com.agilewhisperers.bunnysnaketankmaze.entities;

import com.agilewhisperers.bunnysnaketankmaze.components.Body;
import com.agilewhisperers.bunnysnaketankmaze.components.Sprite;
import com.agilewhisperers.bunnysnaketankmaze.systems.GameObjectManager;
import com.agilewhisperers.bunnysnaketankmaze.systems.Physic;
import com.agilewhisperers.bunnysnaketankmaze.systems.Script;
import com.agilewhisperers.bunnysnaketankmaze.systems.ScriptManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.*;

public class Player extends GameObject implements Script, ContactListener {

   private float rateTimer = 0;
   private float reloadTimer=0;
   private float capacityCounter=0;

   public Player() {
      super(new Sprite(
              "gameObjects/Player.jpg"
      ), new Body(Physic.getObject().getWorld(), 0 + 0.5f, 0 + 0.5f, 1, 0.8f, "Player"));

      getBody().getBody().setType(BodyDef.BodyType.DynamicBody);
      getState().ID = "Player";
      getState().isExist = true;

      //Add to scriptManager
      ScriptManager.getObject().addScriptListener(this);

      //Add to Physic engine
      Physic.getObject().addCollision(this);

      //Set tag for the object
      this.getBody().getBody().setUserData(getState());
   }

   //
   public Player(float posX, float posY) {
      super(new Sprite(
              "gameObjects/Player.jpg"
      ), new Body(Physic.getObject().getWorld(), posX , posY , 1, 0.8f, "Player"));
      getState().ID = "Player";
      getState().isExist = true;
      getBody().getBody().setType(BodyDef.BodyType.DynamicBody);
      //Add to scriptManager
      ScriptManager.getObject().addScriptListener(this);

      //Add to Physic engine
      Physic.getObject().addCollision(this);

      //Set tag for the object
      this.getBody().getBody().setUserData(getState());
   }


   /**
    * This method will be call every game loop.
    */
   @Override
   public void runObjectScript() {
      movement();
      fire();
   }

   private void movement() {
      if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
         getBody().addAngle(getState().rotatingSpeed);
      } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
         getBody().addAngle(-getState().rotatingSpeed);
      } else {
         getBody().getBody().setAngularVelocity(0);
      }

      if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
         this.getBody().getBody().setLinearVelocity(MathUtils.cosDeg(getBody().getAngle()) * getState().movingSpeed, MathUtils.sinDeg(getBody().getAngle()) * getState().movingSpeed);

      } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
         this.getBody().getBody().setLinearVelocity(MathUtils.cosDeg(getBody().getAngle()) * -getState().movingSpeed, MathUtils.sinDeg(getBody().getAngle()) * -getState().movingSpeed);
      } else {
         this.getBody().getBody().setLinearVelocity(0, 0);
      }
   }

   private void fire() {
      //Normal Fire
      rateTimer+=Gdx.graphics.getDeltaTime();
      if ((capacityCounter<=getState().capacity)&&rateTimer>1/getState().RPS&&Gdx.input.isKeyPressed(Input.Keys.M)) {
         GameObjectManager.getObject().getBullet().update(getBody().getBody().getPosition().x - 0.25f + getBody().getWidth()*MathUtils.cosDeg(getBody().getAngle()),
                 getBody().getBody().getPosition().y-0.25f+getBody().getHeight()*MathUtils.sinDeg(getBody().getAngle()) ,
                 getBody().getAngle(), getState().bulletSpeed);
         rateTimer=0;
         capacityCounter++;
      }

      //Reload
      if(capacityCounter>getState().capacity){
         reloadTimer+=Gdx.graphics.getDeltaTime();
         if(reloadTimer>=getState().reloadTime){
            capacityCounter=0;
            reloadTimer=0;
         }
      }
   }

   /**
    * Called when two fixtures begin to touch.
    *
    * @param contact
    */
   @Override
   public void beginContact(Contact contact) {

   }

   /**
    * Called when two fixtures cease to touch.
    *
    * @param contact
    */
   @Override
   public void endContact(Contact contact) {

   }

   @Override
   public void preSolve(Contact contact, Manifold oldManifold) {

   }

   @Override
   public void postSolve(Contact contact, ContactImpulse impulse) {

   }
}
