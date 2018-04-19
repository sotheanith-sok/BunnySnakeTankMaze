package com.agilewhisperers.bunnysnaketankmaze;

import com.agilewhisperers.bunnysnaketankmaze.systems.ObjectFactory;
import com.agilewhisperers.bunnysnaketankmaze.systems.Physic;
import com.agilewhisperers.bunnysnaketankmaze.systems.Renderer;
import com.agilewhisperers.bunnysnaketankmaze.systems.ScriptManager;
import com.badlogic.gdx.Screen;

public class MyScreen implements Screen {

    private BunnySnakeTankMaze myGame;

    public MyScreen(BunnySnakeTankMaze myGame) {
        this.myGame = myGame;

    }

    @Override
    public void show() {

        ObjectFactory.getObject().start();


    }

    @Override
    public void render(float delta) {
        if (!Physic.getObject().getWorld().isLocked()) {
            ScriptManager.getObject().runScripts();
            Physic.getObject().getWorld().step(1 / 60f, 6, 2);
            Renderer.getObject().render();
            Physic.getObject().cleanDeadBody();
            Renderer.getObject().renderHitBox(Physic.getObject().getWorld());
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
