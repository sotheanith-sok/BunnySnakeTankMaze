package com.agilewhisperers.bunnysnaketankmaze.systems;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.utils.Array;

public class CollisionManager implements ContactListener {
   private static CollisionManager single_instance;
   private Array<Contact> contacts;
   private Array<Integer> contactsType;
   private Array<Collider> colliders;

   private CollisionManager() {
      Physic.getObject().setContactListener(this);
      contacts = new Array<>();
      colliders = new Array<>();
      contactsType = new Array<>();
   }

   public static CollisionManager getObject() {
      if (single_instance == null) {
         single_instance = new CollisionManager();
      }
      return single_instance;
   }

   public void addCollider(Collider collider) {
      colliders.add(collider);
   }

   /**
    * Called when two fixtures begin to touch.
    *
    * @param contact
    */
   @Override
   public void beginContact(Contact contact) {
      contacts.add(contact);
      contactsType.add(0);
   }

   /**
    * Called when two fixtures cease to touch.
    *
    * @param contact
    */
   @Override
   public void endContact(Contact contact) {
      contacts.add(contact);
      contactsType.add(1);
   }

   @Override
   public void preSolve(Contact contact, Manifold oldManifold) {

   }

   @Override
   public void postSolve(Contact contact, ContactImpulse impulse) {

   }

   public void calculateCollision() {
      while (contacts.size > 0) {
         Contact contact = contacts.removeIndex(0);
         int type = contactsType.removeIndex(0);
         int count = 0;
         for (int i = 0; i < colliders.size; i++) {
            if (colliders.get(i).getFixtureArray().contains(contact.getFixtureA(), false) || colliders.get(i).getFixtureArray().contains(contact.getFixtureB(), false)) {
               if (type == 0) {
                  colliders.get(i).startCollision(contact);
               } else if (type == 1) {
                  colliders.get(i).endCollision(contact);
               }
                    /*count++;
                    if (count > 1) {
                        break;
                    }*/
            }
         }
      }
   }
}
