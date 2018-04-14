package com.agilewhisperers.bunnysnaketankmaze;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BunnySnakeTankMaze extends Game {

   private MyScreen myScreen;
   @Override
   public void create() {
      myScreen=new MyScreen(this);
      setScreen(myScreen);

   }
}
