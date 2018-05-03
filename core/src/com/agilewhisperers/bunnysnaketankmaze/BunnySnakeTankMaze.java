package com.agilewhisperers.bunnysnaketankmaze;

import TestingAshley.MainScreen;
import com.badlogic.gdx.Game;

public class BunnySnakeTankMaze extends Game {

    private MainScreen ashleyScreen;
    private MyScreen myScreen;
   private int choice=0;
    @Override
    public void create() {
       if(choice==0){
          myScreen = new MyScreen(this);
          setScreen(myScreen);
       }else {
          ashleyScreen = new MainScreen(this);
          setScreen(ashleyScreen);
       }


    }
}
