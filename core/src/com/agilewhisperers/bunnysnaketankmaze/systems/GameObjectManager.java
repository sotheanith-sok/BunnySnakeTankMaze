package com.agilewhisperers.bunnysnaketankmaze.systems;

import com.agilewhisperers.bunnysnaketankmaze.entities.GameObject;

import java.util.ArrayList;
import java.util.List;

public class GameObjectManager {
   private static GameObjectManager single_instance;
   private List<GameObject> gameObjectList;

   private GameObjectManager(){
      gameObjectList=new ArrayList<GameObject>();
   }

   public static GameObjectManager getObject(){
      if(single_instance==null){
         single_instance= new GameObjectManager();
      }
      return single_instance;
   }


   public void addGameObject(GameObject gameObject){
      gameObjectList.add(gameObject);
   }

   public List<GameObject> getAllGameObjects() {
      return gameObjectList;
   }
}
