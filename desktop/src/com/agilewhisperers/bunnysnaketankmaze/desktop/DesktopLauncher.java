package com.agilewhisperers.bunnysnaketankmaze.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.agilewhisperers.bunnysnaketankmaze.BunnySnakeTankMaze;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new BunnySnakeTankMaze(), config);

		config.title="Bunny-Snake Tank Maze";
		config.useGL30=true;
		config.width=1600;
		config.height=900;
		config.allowSoftwareMode=true;
		config.x=-1;
		config.y=-1;

	}
}
