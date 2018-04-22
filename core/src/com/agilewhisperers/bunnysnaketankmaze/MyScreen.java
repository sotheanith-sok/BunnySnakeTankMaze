package com.agilewhisperers.bunnysnaketankmaze;

import com.agilewhisperers.bunnysnaketankmaze.systems.*;
import com.badlogic.gdx.Screen;

public class MyScreen implements Screen {

    private BunnySnakeTankMaze myGame;

    public MyScreen(BunnySnakeTankMaze myGame) {
        this.myGame = myGame;

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
        if (!Physic.getObject().getWorld().isLocked()) {
            Physic.getObject().getWorld().step(1 / 300f, 8, 3);
            ScriptManager.getObject().runScripts();
            CollisionManager.getObject().calculateCollision();
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
