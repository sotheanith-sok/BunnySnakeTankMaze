package com.agilewhisperers.bunnysnaketankmaze.systems;

import java.util.ArrayList;
import java.util.List;

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

   public void addScriptListener(Script script){
      scriptList.add(script);
   }
   public void runScripts(){
      for (Script s:scriptList){
         s.runObjectScript();
      }
   }

}
