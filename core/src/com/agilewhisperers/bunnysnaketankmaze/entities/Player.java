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
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

public class Player extends GameObject implements Script,ContactListener {
    private static final float speed=10;
    private boolean moveForward;
   public Player (){
      super(false, new Sprite(
              // Modify this line below to make it work on your system. For some reason, I have to use the absolute
              // path of the file in order for it to work.
              // Sorry for the inconvenience. -RB
              "C:\\Users\\rubes\\Documents\\GitHub\\BunnySnakeTankMaze\\android\\assets\\game\\Player.jpg"
      ), new Body(Physic.getObject().getWorld(),30,15,10,10,0));

      //Add to scriptManager
      ScriptManager.getObject().addScriptListener(this);

      //Add to Physic engine
      Physic.getObject().addCollision(this);

      //Set tag for the object
      //this.getBody().getFixture().setUserData("Player");
   }


   /**
    * This method will be call every game loop.
    */
   @Override
   public void runObjectScript() {
      if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
          getBody().addAngle(1);
      }
      if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
          getBody().addAngle(-1);
      }
      if(Gdx.input.isKeyPressed(Input.Keys.UP)){
         this.getBody().getBody().setLinearVelocity(MathUtils.cosDeg(getBody().getAngle())*speed,MathUtils.sinDeg(getBody().getAngle())*speed);
         //FIRE!!!
         Bullet bullet = new Bullet(getBody().getBody().getPosition().x,
                                    getBody().getBody().getPosition().y,
                                    getBody().getAngle());
      }else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
           this.getBody().getBody().setLinearVelocity(MathUtils.cosDeg(getBody().getAngle())*-speed,MathUtils.sinDeg(getBody().getAngle())*-speed);
      }else{
          this.getBody().getBody().setLinearVelocity(0,0);
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
