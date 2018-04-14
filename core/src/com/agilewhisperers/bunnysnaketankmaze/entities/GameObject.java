package com.agilewhisperers.bunnysnaketankmaze.entities;

import com.agilewhisperers.bunnysnaketankmaze.components.Sprite;
import com.agilewhisperers.bunnysnaketankmaze.components.Body;

public class GameObject {
   private boolean excluded;
   private Sprite sprite;
   private Body body;

   public GameObject(){
      excluded=false;
      sprite=null;
      body=null;
   }

   public GameObject(Sprite sprite, Body body) {
      this.sprite = sprite;
      this.body = body;
      excluded=false;
   }

   public GameObject(boolean excluded, Sprite sprite, Body body) {
      this.excluded = excluded;
      this.sprite = sprite;
      this.body = body;
   }

   public boolean isExcluded() {
      return excluded;
   }

   public void setExcluded(boolean exclude) {
      this.excluded = exclude;
   }

   public Sprite getSprite() {
      return sprite;
   }

   public void setSprite(Sprite sprite) {
      this.sprite = sprite;
   }

   public Body getBody() {
      return body;
   }

   public void setBody(Body body) {
      this.body = body;
   }
}
