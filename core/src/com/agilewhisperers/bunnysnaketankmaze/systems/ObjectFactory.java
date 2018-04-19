package com.agilewhisperers.bunnysnaketankmaze.systems;

import com.agilewhisperers.bunnysnaketankmaze.MazeGenerator.Maze;
import com.agilewhisperers.bunnysnaketankmaze.entities.Player;
import com.agilewhisperers.bunnysnaketankmaze.entities.Wall;

import java.util.Random;

/**
 * Method use to creates a batch of object.
 */
public class ObjectFactory {
    public static ObjectFactory single_instance;
    private int[][] data;

    private ObjectFactory() {
        Maze maze = new Maze(20, 10);
        data = maze.getMazeAsData();
    }

    public static ObjectFactory getObject() {

        if (single_instance == null) {
            single_instance = new ObjectFactory();
        }
        return single_instance;
    }

    private void removeSomeWall() {
        Random random = new Random();
        for (int i = 1; i < data.length - 1; i++) {
            for (int j = 1; j < data[0].length - 1; j++) {
                if (random.nextBoolean() == true) {
                    data[i][j] = 0;
                }
            }
        }
    }

    /**
     * Call at the start of the game. Use to create all gameObjects
     */
    public void start() {
        //Bullet bullet=new Bullet();
        removeSomeWall();
        spawnPlayer();
        spawnWall();


    }

    public void spawnPlayer() {
        Random random = new Random();
        int x = 0;
        int y = 0;
        do {
            x = random.nextInt(data[0].length);
            y = random.nextInt(data.length);
        } while (data[y][x] == 1);
        Player player = new Player(x, y);
    }

    public void spawnWall() {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                if (data[i][j] == 1) {
                    new Wall(j, i);
                }
            }
        }
    }


}
