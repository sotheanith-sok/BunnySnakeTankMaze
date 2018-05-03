package com.agilewhisperers.bunnysnaketankmaze.systems;

import com.agilewhisperers.bunnysnaketankmaze.MazeGenerator.Maze;
import com.agilewhisperers.bunnysnaketankmaze.components.Box2dRaycastCollisionDetector;
import com.agilewhisperers.bunnysnaketankmaze.components.SteerableComponent;
import com.agilewhisperers.bunnysnaketankmaze.entities.*;
import com.badlogic.gdx.ai.steer.behaviors.Arrive;
import com.badlogic.gdx.ai.steer.behaviors.BlendedSteering;
import com.badlogic.gdx.ai.steer.behaviors.RaycastObstacleAvoidance;
import com.badlogic.gdx.ai.steer.utils.RayConfiguration;
import com.badlogic.gdx.ai.steer.utils.rays.CentralRayWithWhiskersConfiguration;
import com.badlogic.gdx.ai.steer.utils.rays.SingleRayConfiguration;
import com.badlogic.gdx.ai.utils.Collision;
import com.badlogic.gdx.ai.utils.Ray;
import com.badlogic.gdx.ai.utils.RaycastCollisionDetector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

/**
 * Method use to creates a batch of object.
 */
public class ObjectFactory {
    public static ObjectFactory single_instance;
    private int[][] data;
    private Maze maze;

    private ObjectFactory() {
        maze = new Maze();
        data = new int[32][62];

    }

    public static ObjectFactory getObject() {

        if (single_instance == null) {
            single_instance = new ObjectFactory();
        }
        return single_instance;
    }

    public int[][] getData() {
        return data;
    }

    private void buildMaze() {

        //First maze
        int[][] temp = maze.getMazeAsData(8, 3);
        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp[0].length; j++) {
                data[i + 4][j + 4] = temp[i][j];
            }
        }

        //Second maze
        temp = maze.getMazeAsData(8, 3);
        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp[0].length; j++) {
                data[i + 4][j + data[0].length / 2 + 2] = temp[i][j];
            }
        }

        //Third maze
        temp = maze.getMazeAsData(8, 3);
        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp[0].length; j++) {
                data[i + data.length / 2 + 2][j + 4] = temp[i][j];
            }
        }

        //Fourth maze
        temp = maze.getMazeAsData(8, 3);
        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp[0].length; j++) {
                data[i + data.length / 2 + 2][j + data[0].length / 2 + 2] = temp[i][j];
            }
        }

        //Outside wall
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                if (i == 0 || j == 0 || i == data.length - 1 || j == data[0].length - 1) {
                    data[i][j] = 1;
                }
            }
        }

        //Add gate
        data[4][data[0].length / 4] = 0;
        data[4][data[0].length / 4 + 1] = 0;
        data[4][data[0].length / 4 + 2] = 0;
        data[4][data[0].length * 3 / 4] = 0;
        data[4][data[0].length * 3 / 4 - 2] = 0;
        data[4][data[0].length * 3 / 4 - 1] = 0;
        data[data.length / 2 - 3][data[0].length / 4] = 0;
        data[data.length / 2 - 3][data[0].length / 4 + 1] = 0;
        data[data.length / 2 - 3][data[0].length / 4 + 2] = 0;
        data[data.length / 2 - 3][data[0].length * 3 / 4] = 0;
        data[data.length / 2 - 3][data[0].length * 3 / 4 - 2] = 0;
        data[data.length / 2 - 3][data[0].length * 3 / 4 - 1] = 0;
        data[data.length - 5][data[0].length / 4] = 0;
        data[data.length - 5][data[0].length / 4 + 1] = 0;
        data[data.length - 5][data[0].length / 4 + 2] = 0;
        data[data.length - 5][data[0].length * 3 / 4] = 0;
        data[data.length - 5][data[0].length * 3 / 4 - 2] = 0;
        data[data.length - 5][data[0].length * 3 / 4 - 1] = 0;
        data[data.length / 2 + 2][data[0].length / 4] = 0;
        data[data.length / 2 + 2][data[0].length / 4 + 1] = 0;
        data[data.length / 2 + 2][data[0].length / 4 + 2] = 0;
        data[data.length / 2 + 2][data[0].length * 3 / 4] = 0;
        data[data.length / 2 + 2][data[0].length * 3 / 4 - 2] = 0;
        data[data.length / 2 + 2][data[0].length * 3 / 4 - 1] = 0;

        data[data.length / 4][4] = 0;
        data[data.length / 4 + 1][4] = 0;
        data[data.length * 3 / 4 - 1][4] = 0;
        data[data.length * 3 / 4 - 2][4] = 0;
        data[data.length / 4][data[0].length / 2 - 3] = 0;
        data[data.length / 4 + 1][data[0].length / 2 - 3] = 0;
        data[data.length * 3 / 4 - 1][data[0].length / 2 - 3] = 0;
        data[data.length * 3 / 4 - 2][data[0].length / 2 - 3] = 0;
        data[data.length / 4][data[0].length / 2 + 2] = 0;
        data[data.length / 4 + 1][data[0].length / 2 + 2] = 0;
        data[data.length * 3 / 4 - 1][data[0].length / 2 + 2] = 0;
        data[data.length * 3 / 4 - 2][data[0].length / 2 + 2] = 0;
        data[data.length / 4][data[0].length - 5] = 0;
        data[data.length / 4 + 1][data[0].length - 5] = 0;
        data[data.length * 3 / 4 - 1][data[0].length - 5] = 0;
        data[data.length * 3 / 4 - 2][data[0].length - 5] = 0;

        //Remove extra wall
        data[4 + 1][data[0].length / 4] = 0;
        data[4 + 1][data[0].length / 4 + 1] = 0;
        data[4 + 1][data[0].length / 4 + 2] = 0;
        data[4 + 1][data[0].length * 3 / 4] = 0;
        data[4 + 1][data[0].length * 3 / 4 - 2] = 0;
        data[4 + 1][data[0].length * 3 / 4 - 1] = 0;
        data[data.length / 2 - 3 - 1][data[0].length / 4] = 0;
        data[data.length / 2 - 3 - 1][data[0].length / 4 + 1] = 0;
        data[data.length / 2 - 3 - 1][data[0].length / 4 + 2] = 0;
        data[data.length / 2 - 3 - 1][data[0].length * 3 / 4] = 0;
        data[data.length / 2 - 3 - 1][data[0].length * 3 / 4 - 2] = 0;
        data[data.length / 2 - 3 - 1][data[0].length * 3 / 4 - 1] = 0;
        data[data.length - 5 - 1][data[0].length / 4] = 0;
        data[data.length - 5 - 1][data[0].length / 4 + 1] = 0;
        data[data.length - 5 - 1][data[0].length / 4 + 2] = 0;
        data[data.length - 5 - 1][data[0].length * 3 / 4] = 0;
        data[data.length - 5 - 1][data[0].length * 3 / 4 - 2] = 0;
        data[data.length - 5 - 1][data[0].length * 3 / 4 - 1] = 0;
        data[data.length / 2 + 2 + 1][data[0].length / 4] = 0;
        data[data.length / 2 + 2 + 1][data[0].length / 4 + 1] = 0;
        data[data.length / 2 + 2 + 1][data[0].length / 4 + 2] = 0;
        data[data.length / 2 + 2 + 1][data[0].length * 3 / 4] = 0;
        data[data.length / 2 + 2 + 1][data[0].length * 3 / 4 - 2] = 0;
        data[data.length / 2 + 2 + 1][data[0].length * 3 / 4 - 1] = 0;
    }

    /**
     * Call at the start of the game. Use to create all gameObjects
     */
    public void start() {
        //Bullet bullet=new Bullet();
        buildMaze();
        spawnPlayer();
       // spawnWall();



    }

    public void spawnPlayer() {
        int x = 0;
        int y = 0;
        do {
            x = MathUtils.random(data[0].length - 1);
            y = MathUtils.random(data.length - 1);
        } while (data[y][x] == 1);
        Player player1 = new Player1(x, y);
        do {
            x = MathUtils.random(data[0].length - 1);
            y = MathUtils.random(data.length - 1);
        } while (data[y][x] == 1);
        Player player2 = new Player2(x, y);

        player1.setSteerableComponent(new SteerableComponent(player1.getBody().getBody(),1));
        player2.setSteerableComponent(new SteerableComponent(player2.getBody().getBody(),1));
        Arrive<Vector2> arriveSB=new Arrive<Vector2>(player2.getSteerableComponent(),player1.getSteerableComponent())
                .setTimeToTarget(0.01f)
                .setArrivalTolerance(0.1f)
                .setDecelerationRadius(0.1f);
        CentralRayWithWhiskersConfiguration<Vector2> configuration=new CentralRayWithWhiskersConfiguration<>(
                player2.getSteerableComponent(),
                0.1f,0.05f,
                35*MathUtils.degreesToRadians);

        RaycastCollisionDetector<Vector2>raycastCollisionDetector=new Box2dRaycastCollisionDetector(Physic.getObject().getWorld());

        RaycastObstacleAvoidance<Vector2>raycastObstacleAvoidance=new RaycastObstacleAvoidance<>(player2.getSteerableComponent(),
                configuration
        ,raycastCollisionDetector,0.01f);
        BlendedSteering<Vector2>blendedSteering=new BlendedSteering<>(player2.getSteerableComponent());
        blendedSteering.add(arriveSB,1);
      //  blendedSteering.add(raycastObstacleAvoidance,2);

        player2.getSteerableComponent().setSteeringBehavior(blendedSteering);
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
