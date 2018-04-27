package com.agilewhisperers.bunnysnaketankmaze;

import com.agilewhisperers.bunnysnaketankmaze.systems.*;
import com.badlogic.gdx.Screen;

public class MyScreen implements Screen {

    private static final float TIME_STEP = 1f / 60f;
    private static final int VELOCITY_ITERATIONS = 8;
    private static final int POSITION_ITERATIONS = 4;
    private BunnySnakeTankMaze myGame;
    private float accumulator = 0f;

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
        rendering(delta);
        float frameTime = Math.min(delta, 0.25f);
        accumulator += frameTime;
        while (accumulator >= TIME_STEP) {
            ScriptManager.getObject().runScripts(TIME_STEP);
            CollisionManager.getObject().calculateCollision();
            Physic.getObject().cleanDeadBody();
            Physic.getObject().getWorld().step(TIME_STEP, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
            accumulator -= TIME_STEP;
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

    private void calculatePhysic(float deltaTime) {

    }

    private void rendering(float deltaTime) {
        Renderer.getObject().render(deltaTime);
        Renderer.getObject().renderHitBox(Physic.getObject().getWorld());
    }
}
