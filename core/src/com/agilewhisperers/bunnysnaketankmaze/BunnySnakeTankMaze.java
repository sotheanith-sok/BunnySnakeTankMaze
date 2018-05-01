package com.agilewhisperers.bunnysnaketankmaze;

import TestingAshley.MainScreen;
import com.badlogic.gdx.Game;

public class BunnySnakeTankMaze extends Game {

    private MainScreen myScreen;

    @Override
    public void create() {
        myScreen = new MainScreen(this);
        setScreen(myScreen);

    }
}
