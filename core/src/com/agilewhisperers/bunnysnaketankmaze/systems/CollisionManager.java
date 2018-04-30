package com.agilewhisperers.bunnysnaketankmaze.systems;

import com.agilewhisperers.bunnysnaketankmaze.entities.GameObject;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

public class CollisionManager implements ContactListener {
   private static CollisionManager single_instance;


   private CollisionManager() {
      Physic.getObject().setContactListener(this);
   }

   public static CollisionManager getObject() {
      if (single_instance == null) {
         single_instance = new CollisionManager();
      }
      return single_instance;
   }


   /**
    * Called when two fixtures begin to touch.
    *
    * @param contact
    */
   @Override
   public void beginContact(Contact contact) {
      GameObject gameObject1 = (GameObject) contact.getFixtureA().getBody().getUserData();
      GameObject gameObject2 = (GameObject) contact.getFixtureB().getBody().getUserData();
      if (gameObject1 instanceof Collider) {
         ((Collider) gameObject1).startCollision(contact);
      }
      if (gameObject2 instanceof Collider) {
         ((Collider) gameObject2).startCollision(contact);
      }
   }

   /**
    * Called when two fixtures cease to touch.
    *
    * @param contact
    */
   @Override
   public void endContact(Contact contact) {
      GameObject gameObject1 = (GameObject) contact.getFixtureA().getBody().getUserData();
      GameObject gameObject2 = (GameObject) contact.getFixtureA().getBody().getUserData();
      if (gameObject1 instanceof Collider) {
         ((Collider) gameObject1).endCollision(contact);
      }
      if (gameObject2 instanceof Collider) {
         ((Collider) gameObject2).endCollision(contact);
      }
   }

   @Override
   public void preSolve(Contact contact, Manifold oldManifold) {

   }

   @Override
   public void postSolve(Contact contact, ContactImpulse impulse) {

   }

}
