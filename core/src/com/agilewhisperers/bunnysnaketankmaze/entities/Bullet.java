package com.agilewhisperers.bunnysnaketankmaze.entities;

import com.agilewhisperers.bunnysnaketankmaze.components.Body;
import com.agilewhisperers.bunnysnaketankmaze.components.Sprite;
import com.agilewhisperers.bunnysnaketankmaze.systems.*;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

public class Bullet extends GameObject implements Script,ContactListener{
   private static final float speed=20;

   // Default(???) Bullet constructor.
   // Pretty much for testing purposes now.
   public Bullet(){
      super(false,new Sprite(
              // Modify this line below to make it work on your system. For some reason, I have to use the absolute
              // path of the file in order for it to work.
              // Sorry for the inconvenience. -RB
              "C:\\Users\\rubes\\Documents\\GitHub\\BunnySnakeTankMaze\\android\\assets\\game\\bullet.png"
      ),new Body(Physic.getObject().getWorld(),0,0,10,10,0));
      GameObjectManager.getObject().addGameObject(this);
      this.getSprite().getSprite().flip(false,true);


      //Add to scriptManager
      ScriptManager.getObject().addScriptListener(this);

      //Add to Physic engine
      Physic.getObject().addCollision(this);
      //Set tag for the object
      this.getBody().getFixture().setUserData("Bullet");
   }

   // New bullet constructor that accepts variables to determine initial position and direction.
   public Bullet(float posX, float posY, float angle) {
       super(false,new Sprite(
               // Modify this line below to make it work on your system. For some reason, I have to use the absolute
               // path of the file in order for it to work.
               // Sorry for the inconvenience. -RB
               "C:\\Users\\rubes\\Documents\\GitHub\\BunnySnakeTankMaze\\android\\assets\\game\\bullet.png"
       ), new Body(Physic.getObject().getWorld()
                       ,posX,posY,10,10,0, 0));
       GameObjectManager.getObject().addGameObject(this);
       this.getSprite().getSprite().flip(false,true);

       //Add to scriptManager
       ScriptManager.getObject().addScriptListener(this);

       //Add to Physic engine
       Physic.getObject().addCollision(this);

       // Set velocity vector.
       this.getBody().getBody().setLinearVelocity(
              MathUtils.cosDeg(angle)*speed,
              MathUtils.sinDeg(angle)*speed);

       //Set tag for the object
       this.getBody().getFixture().setUserData("Bullet");


   }

   /**
    * This method will be call every game loop.
    */
   @Override
   public void runObjectScript() {

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
