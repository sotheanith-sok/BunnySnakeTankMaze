package com.agilewhisperers.bunnysnaketankmaze;

import com.agilewhisperers.bunnysnaketankmaze.entities.GameObject;
import com.agilewhisperers.bunnysnaketankmaze.systems.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import java.util.Random;

public class MyScreen implements Screen {

   private BunnySnakeTankMaze myGame;

   public  MyScreen(BunnySnakeTankMaze myGame){
      this.myGame=myGame;

   }
   @Override
   public void show() {

      ObjectFactory.getObject().start();


   }

   @Override
   public void render(float delta) {
      ScriptManager.getObject().runScripts();
      Physic.getObject().getWorld().step(1/60f,6,2);
      Renderer.getObject().render();
      Renderer.getObject().renderHitBox(Physic.getObject().getWorld());

   }


   @Override
   public void pause() {

   }

   @Override
   public void resume() {

   }

   @Override
   public void hide() {

   }

   @Override
   public void dispose() {

   }

   @Override
   public void resize(int width, int height) {

   }
}
