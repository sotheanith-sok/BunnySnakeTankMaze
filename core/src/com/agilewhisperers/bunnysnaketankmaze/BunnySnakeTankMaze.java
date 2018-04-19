package com.agilewhisperers.bunnysnaketankmaze;

import com.badlogic.gdx.Game;

public class BunnySnakeTankMaze extends Game {

    private MyScreen myScreen;

    @Override
    public void create() {
        myScreen = new MyScreen(this);
        setScreen(myScreen);

    }
}
