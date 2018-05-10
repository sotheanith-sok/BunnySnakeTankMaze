package com.agilewhisperers.bunnysnaketankmaze;

import com.agilewhisperers.bunnysnaketankmaze.screens.EndGameScreen;
import com.agilewhisperers.bunnysnaketankmaze.screens.MainGameScreen;
import com.agilewhisperers.bunnysnaketankmaze.screens.MainMenuScreen;
import com.badlogic.gdx.Game;

public class BunnySnakeTankMaze extends Game {

   @Override
   public void create() {

      setScreen(new MainMenuScreen(this));

   }

   public void changeScreen(int choice, int decision) {
      switch (choice) {
         case 1:
            setScreen(new MainGameScreen(this));
            break;
         case 2:
            setScreen(new EndGameScreen(this, decision));
            break;
         default:
            setScreen(new MainMenuScreen(this));
            break;
      }
   }


}
