package com.agilewhisperers.bunnysnaketankmaze.screens;

import com.agilewhisperers.bunnysnaketankmaze.BunnySnakeTankMaze;
import com.agilewhisperers.bunnysnaketankmaze.systems.ObjectFactory;
import com.badlogic.gdx.Screen;

public class BScreen implements Screen {

    private BunnySnakeTankMaze myGame;

    public BScreen(BunnySnakeTankMaze myGame) {
        this.myGame = myGame;
    }

    @Override
    public void show() {
        ObjectFactory.getObject().start();
    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

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
}
