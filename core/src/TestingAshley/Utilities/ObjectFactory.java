package TestingAshley.Utilities;

import TestingAshley.Components.SpriteComponent;
import TestingAshley.Entities.Wall;
import TestingAshley.Systems.PhysicSystem;
import TestingAshley.Systems.RenderingSystem;
import com.agilewhisperers.bunnysnaketankmaze.MazeGenerator.Maze;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class ObjectFactory {
   private static ObjectFactory single_instance;
   public static ObjectFactory getObject(){
      if(single_instance==null){
         single_instance=new ObjectFactory();
      }
      return single_instance;
   }


   private World world;
   private Engine engine;

   private ObjectFactory(){
      world=world = new World(new Vector2(0, 0), true);
      engine=new Engine();
      maze = new Maze();
      data = new int[32][62];
   }

   public World getWorld() {
      return world;
   }

   public Engine getEngine() {
      return engine;
   }

   public void loadSystems(){
      engine.addSystem(new RenderingSystem(world));
      engine.addSystem(new PhysicSystem(world));
   }

   public void loadEntities(){
      createWall();
   }
   private void createWall(){
      buildMaze();
      spawnWall();
   }


   private int[][] data;
   private Maze maze;
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
   public void spawnWall() {
      for (int i = 0; i < data.length; i++) {
         for (int j = 0; j < data[0].length; j++) {
            if (data[i][j] == 1) {
               engine.addEntity(new Wall(world,(float)j,(float)i));
            }
         }
      }
   }
}
