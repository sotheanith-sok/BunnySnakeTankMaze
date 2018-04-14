package com.agilewhisperers.bunnysnaketankmaze.systems;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.World;

public class Physic {
   private static Physic single_instance;
   private World world;


   private Physic(){
      world=new World(new Vector2(0,0),true);
   }

   public static Physic getObject(){
      if(single_instance==null){
         single_instance=new Physic();
      }
      return single_instance;
   }

   public World getWorld(){
      return world;
   }

   public void addCollision(ContactListener contactListener){
      world.setContactListener(contactListener);
   }



}
