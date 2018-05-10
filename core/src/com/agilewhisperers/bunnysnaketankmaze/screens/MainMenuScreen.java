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

public class MainMenuScreen extends ScreenAdapter {
   private BunnySnakeTankMaze myGame;
   private Stage stage;
   private Skin skin;
   private Table table;
   private TextButton startButton, quitButton;
   private Label titleLable;

   public MainMenuScreen(BunnySnakeTankMaze myGame) {
      this.myGame = myGame;
      skin = new Skin(Gdx.files.internal("Menu/tracerUISkin/skin/tracer-ui.json"));
      stage = new Stage(new ScreenViewport());

      table = new Table();
      table.setWidth(stage.getWidth());
      table.setHeight(stage.getHeight());
      table.align(Align.center);
      table.setPosition(0, 0);

      titleLable = new Label("Bunny Snake Tank Maze", skin);
      titleLable.setAlignment(Align.center);
      startButton = new TextButton("Start", skin);
      quitButton = new TextButton("Quit", skin);
      table.add(titleLable).width(100).height(100);
      table.row();
      table.add(startButton).padBottom(30).width(250).height(100);
      table.row();
      table.add(quitButton).width(250).height(100);
      stage.addActor(table);
      startButton.addListener(new ClickListener() {
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
