package com.agilewhisperers.bunnysnaketankmaze.entities;

import com.agilewhisperers.bunnysnaketankmaze.components.Body;
import com.agilewhisperers.bunnysnaketankmaze.components.Sprite;
import com.agilewhisperers.bunnysnaketankmaze.components.Stats;
import com.agilewhisperers.bunnysnaketankmaze.systems.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.utils.Array;

import java.util.List;
import java.util.Random;

public class WallMover extends GameObject implements Script, Collider {
   private Random random;
   private float startTimer=0;
   private float stageTimer=0;
   private int[][] wallLocation;
   Array<Body> bodyArray;
   private boolean isEating;

   public WallMover() {
      super();
      super.setBody(new Body(Physic.getObject().getWorld(), 15, 15, 2.5f, 0, "Spider"));
      super.setSprite(new Sprite("gameObjects/NPC.atlas", "Spider", 1));
      super.getBody().getBody().setType(BodyDef.BodyType.KinematicBody);
      getStats().isExist = true;
      getStats().ID = "WallMover";
      random = new Random();
      getBody().getBody().setUserData(getStats());
      ScriptManager.getObject().addScriptListener(this);
      CollisionManager.getObject().addCollider(this);
      wallLocation=ObjectFactory.getObject().getData();
      bodyArray=new Array<>();
      isEating=true;
   }

   @Override
   public void startCollision(Contact contact) {

   }

   @Override
   public void endCollision(Contact contact) {

   }

   @Override
   public Array<Fixture> getFixtureArray() {
      return getBody().getFixtureList();
   }

   /**
    * This method will be call every game loop.
    */
   @Override
   public void runObjectScript() {
      stageTimer+= Gdx.graphics.getDeltaTime();
      startTimer+=Gdx.graphics.getDeltaTime();
      if(stageTimer>1f){
         List<GameObject> gameObjectList=GameObjectManager.getObject().getAllGameObjects();
         //EAT WALL
         if (isEating){
            Body body=gameObjectList.get(random.nextInt(gameObjectList.size())).getBody();
            Vector2 position=body.getBody().getPosition();
            Stats userData= (Stats) body.getBody().getUserData();
            if(userData.ID.equals("Wall")
                    &&position.x>=1&&position.x<wallLocation[0].length-1
                    &&position.y>=1&&position.y<wallLocation.length-1){
               wallLocation[(int)position.y][(int)position.x]=0;
               bodyArray.add(body);
               body.setPosition(-10,-10);
               body.updateFilter(Physic.CATEGORY_ENVIROMENT,(short)0);
               getBody().setPosition(position.x,position.y);
            }
            if(isEating&&bodyArray.size>10){
               isEating=false;
               getBody().setPosition(-10,-10);
            }

         }else{
            //PLACE WALL
         }
         stageTimer=0f;

      }


   }
}
