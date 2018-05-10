package com.agilewhisperers.bunnysnaketankmaze.screens;

import com.agilewhisperers.bunnysnaketankmaze.BunnySnakeTankMaze;
import com.agilewhisperers.bunnysnaketankmaze.systems.AssetManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class EndGameScreen extends ScreenAdapter {

   private BunnySnakeTankMaze myGame;
   private Stage stage;
   private Table table;
   private TextButton restartButton, quitButton;
   private Label label;
   private Skin skin;

   public EndGameScreen(BunnySnakeTankMaze bunnySnakeTankMaze, int decision) {
      this.myGame = bunnySnakeTankMaze;
      stage = new Stage(new ScreenViewport());
      skin = new Skin(Gdx.files.internal("Menu/tracerUISkin/skin/tracer-ui.json"));

      if (decision == 0) {
         label = new Label("Player 1 Won!!!", skin);
      } else if (decision == 1) {
         label = new Label("Player 2 Won!!!", skin);
      } else {
         label = new Label("DRAW", skin);
      }
      label.setAlignment(Align.center);
      restartButton = new TextButton("Restart", skin);
      quitButton = new TextButton("Quit", skin);
      table = new Table();
      table.setPosition(0, 0);
      table.align(Align.center);
      table.setWidth(stage.getWidth());
      table.setHeight(stage.getHeight());

      table.add(label).padBottom(30).width(100).height(100);
      table.row();
      table.add(restartButton).padBottom(30).width(250).height(100);
      table.row();
      table.add(quitButton).padBottom(30).width(250).height(100);

      stage.addActor(table);

      restartButton.addListener(new ClickListener() {
         @Override
         public void clicked(InputEvent event, float x, float y) {
            myGame.changeScreen(1, -1);
         }
      });

      quitButton.addListener(new ClickListener() {
         @Override
         public void clicked(InputEvent event, float x, float y) {
            AssetManager.getObject().getAssetManager().dispose();
            Gdx.app.exit();
         }
      });
      Gdx.input.setInputProcessor(stage);

   }

   @Override
   public void render(float delta) {
      Gdx.gl.glClearColor(0, 0, 0, 1);
      Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

      stage.act(delta);
      stage.draw();
   }
}
