package com.agilewhisperers.bunnysnaketankmaze.systems;

import com.agilewhisperers.bunnysnaketankmaze.components.Body;
import com.agilewhisperers.bunnysnaketankmaze.components.Identifier;
import com.agilewhisperers.bunnysnaketankmaze.entities.GameObject;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.utils.Array;
import sun.font.PhysicalStrike;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Database of all gameObject.
 */
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


   /**
    * Add gameObject to the database
    * @param gameObject
    */
   public void addGameObject(GameObject gameObject){
      gameObjectList.add(gameObject);
   }

   public void removeGameObject(GameObject gameObject) {
      gameObject.getIdentifier().isExist=false;
      gameObjectList.remove(gameObject);
   }


   /**
    * Get the list of all gameObject.
    * @return gameObject list.
    */
   public List<GameObject> getAllGameObjects() {
      return gameObjectList;
   }
}
