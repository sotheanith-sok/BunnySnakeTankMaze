package com.agilewhisperers.bunnysnaketankmaze.screens;

import com.agilewhisperers.bunnysnaketankmaze.BunnySnakeTankMaze;
import com.agilewhisperers.bunnysnaketankmaze.entities.Player;
import com.agilewhisperers.bunnysnaketankmaze.systems.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.utils.Array;


public class MainGameScreen extends ScreenAdapter {
   private static final float TIME_STEP = 1f / 300f;
   private static final int VELOCITY_ITERATIONS = 8;
   private static final int POSITION_ITERATIONS = 4;
   private BunnySnakeTankMaze myGame;
   private float accumulator = 0f;


   public MainGameScreen(BunnySnakeTankMaze myGame) {
      this.myGame = myGame;
      Gdx.input.setInputProcessor(null);
   }


   @Override
   public void show() {
      AssetManager.getObject().loadAssets();
      AssetManager.getObject().getAssetManager().finishLoading();
      ObjectFactory.getObject().start();
      CollisionManager.getObject();
   }

   @Override
   public void render(float delta) {

      float frameTime = Math.min(delta, 0.25f);
      accumulator += frameTime;
      while (accumulator >= TIME_STEP) {
         ScriptManager.getObject().runScripts(TIME_STEP);
         if (!Physic.getObject().getWorld().isLocked()) {

            Physic.getObject().cleanDeadBody();
            accumulator -= TIME_STEP;
            ScriptManager.getObject().resetPreviousPosition();
            Physic.getObject().getWorld().step(TIME_STEP, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
         }
      }
      rendering(accumulator / TIME_STEP);
      detectWin();


   }

   private void rendering(float alpha) {
      Renderer.getObject().render(alpha, TIME_STEP);
      Renderer.getObject().renderHitBox(Physic.getObject().getWorld());
   }

   public void detectWin() {
      Array<Player> playerList = ObjectFactory.getObject().getPlayerList();
      if (playerList.get(0).getStats().getCurrentHP() == 0 && playerList.get(1).getStats().getCurrentHP() == 0) {
         myGame.changeScreen(2, 2);
         this.dispose();
      } else if (playerList.get(1).getStats().getCurrentHP() == 0) {
         myGame.changeScreen(2, 1);
         this.dispose();
      } else if (playerList.get(0).getStats().getCurrentHP() == 0) {
         myGame.changeScreen(2, 0);
         this.dispose();
      }
   }

   @Override
   public void dispose() {
      GameObjectManager.getObject().clean();
      ObjectFactory.getObject().clean();
      Physic.getObject().clean();
      ScriptManager.getObject().clean();
      Renderer.getObject().clean();
      CollisionManager.getObject().clean();
   }
}
