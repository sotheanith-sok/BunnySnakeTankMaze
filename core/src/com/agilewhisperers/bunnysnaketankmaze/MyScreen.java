package com.agilewhisperers.bunnysnaketankmaze;

import com.agilewhisperers.bunnysnaketankmaze.entities.GameObject;
import com.agilewhisperers.bunnysnaketankmaze.systems.GameObjectManager;
import com.agilewhisperers.bunnysnaketankmaze.systems.ObjectFactory;
import com.agilewhisperers.bunnysnaketankmaze.systems.Physic;
import com.agilewhisperers.bunnysnaketankmaze.systems.Renderer;
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
      control();
      Physic.getObject().getWorld().step(1/60f,6,2);
      Renderer.getObject().render();
      Renderer.getObject().renderHitBox(Physic.getObject().getWorld());

   }

   public void control(){
      Body body= GameObjectManager.getObject().getAllGameObjects().get(0).getBody().getBody();
      body.isBullet();
      if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
         body.setLinearVelocity(-5,0);
      }
      if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
         body.setLinearVelocity(new Vector2(5,0));
      }
      if(Gdx.input.isKeyPressed(Input.Keys.UP)){
         body.setLinearVelocity(0,5);
      }
      if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
         body.setLinearVelocity(0,-5);
      }
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
