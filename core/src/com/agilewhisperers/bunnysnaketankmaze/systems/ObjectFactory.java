package com.agilewhisperers.bunnysnaketankmaze.systems;

import com.agilewhisperers.bunnysnaketankmaze.components.Body;
import com.agilewhisperers.bunnysnaketankmaze.components.Sprite;
import com.agilewhisperers.bunnysnaketankmaze.entities.Bullet;
import com.agilewhisperers.bunnysnaketankmaze.entities.GameObject;
import com.agilewhisperers.bunnysnaketankmaze.entities.Player;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.World;

public class ObjectFactory {
   public static ObjectFactory single_instance;

   public static ObjectFactory getObject(){

      if(single_instance==null){
         single_instance=new ObjectFactory();
      }
      return single_instance;
   }

   private ObjectFactory(){
   }


   public void start(){
      Bullet bullet=new Bullet();
      Player player=new Player();

   }



}
