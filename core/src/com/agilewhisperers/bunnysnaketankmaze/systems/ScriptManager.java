package com.agilewhisperers.bunnysnaketankmaze.systems;

import java.util.ArrayList;
import java.util.List;

/**
 * The Script system.
 */
public class ScriptManager {
   private static ScriptManager single_instance;
   List<Script> scriptList;
   private ScriptManager(){
      scriptList=new ArrayList<>();
   }
   public static ScriptManager getObject(){
      if(single_instance==null){
         single_instance=new ScriptManager();
      }
      return single_instance;
   }

   /**
    * Add listener to script engine so it knows who to call.
    * @param script object that implement script.
    */
   public void addScriptListener(Script script){
      scriptList.add(script);
   }


   /**
    * Run script of all listeners.
    */
   public void runScripts(){
      for (Script s:scriptList){
         s.runObjectScript();
      }
   }

}
