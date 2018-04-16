package com.agilewhisperers.bunnysnaketankmaze.entities;

import com.agilewhisperers.bunnysnaketankmaze.components.Sprite;
import com.agilewhisperers.bunnysnaketankmaze.components.Body;
import com.agilewhisperers.bunnysnaketankmaze.systems.GameObjectManager;

/**
 * The base class of all gameobjects.
 */
public class GameObject {
   private boolean excluded;
   private Sprite sprite;
   private Body body;

   public GameObject(){
      excluded=false;
      sprite=null;
      body=null;
       GameObjectManager.getObject().addGameObject(this);
   }

   public GameObject(Sprite sprite, Body body) {
      this.sprite = sprite;
      this.body = body;
      excluded=false;
       GameObjectManager.getObject().addGameObject(this);
   }

   public GameObject(boolean excluded, Sprite sprite, Body body) {
      this.excluded = excluded;
      this.sprite = sprite;
      this.body = body;
       GameObjectManager.getObject().addGameObject(this);
   }

    /**
     * Is this object excluded from game engines.
     * @return is excluded
     */
   public boolean isExcluded() {
      return excluded;
   }

    /**
     * Set the exclusion of this object.
     * @param exclude
     */
   public void setExcluded(boolean exclude) {
      this.excluded = exclude;
   }

    /**
     * Get the sprite component of this object.
     * @return sprite
     */
   public Sprite getSprite() {
      return sprite;
   }

    /**
     * Set the sprite component of this object.
     * @param sprite
     */
   public void setSprite(Sprite sprite) {
      this.sprite = sprite;
   }

    /**
     * Set the body component of this object.
     * @return body
     */
   public Body getBody() {
      return body;
   }

    /**
     * Get the body component of this object.
     * @param body
     */
   public void setBody(Body body) {
      this.body = body;
   }
}
