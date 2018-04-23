package com.agilewhisperers.bunnysnaketankmaze.components;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public abstract class Animator {

   private Array<Animation<TextureRegion>> animationArray;

   public Animator(){
      animationArray=new Array<>();
   }

   public void addAnimation(Animation<TextureRegion> animation){
      animationArray.add(animation);
   }
   public Animation<TextureRegion> getAnimation(int index){
      return animationArray.get(index);
   }
   public abstract TextureRegion getFrame(float stateTimer);

}
