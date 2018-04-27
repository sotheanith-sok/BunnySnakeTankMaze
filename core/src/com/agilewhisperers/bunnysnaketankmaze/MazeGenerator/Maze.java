package com.agilewhisperers.bunnysnaketankmaze.MazeGenerator;
import com.badlogic.gdx.math.MathUtils;

public class Maze {
   private int column, row;
   private Cell[][] maze;

   public Maze() {
      this.column = 10;
      this.row = 10;
      initialize();
   }

   public Maze(int column, int row) {
      this.column = column;
      this.row = row;
      initialize();

   }

   private void initialize() {
      maze = new Cell[row][column];

   }

   public int[][] getMazeAsData(int column, int row) {
      this.column = column;
      this.row = row;
      initialize();
      Cell[][] maze = getMaze();
      int[][] mazeData = new int[maze.length * 3 + 1][maze[0].length * 3 + 1];

      for (int i = 0; i < maze.length; i++) {
         for (int j = 0; j < maze[0].length; j++) {
            Cell current = maze[i][j];
            if (current.getSide(Direction.North)) {
               mazeData[i * 3][j * 3 + 0] = 1;
               mazeData[i * 3][j * 3 + 1] = 1;
               mazeData[i * 3][j * 3 + 2] = 1;
               mazeData[i * 3][j * 3 + 3] = 1;
            }
            if (current.getSide(Direction.South)) {
               mazeData[i * 3 + 3][j * 3 + 0] = 1;
               mazeData[i * 3 + 3][j * 3 + 1] = 1;
               mazeData[i * 3 + 3][j * 3 + 2] = 1;
               mazeData[i * 3 + 3][j * 3 + 3] = 1;
            }
            if (current.getSide(Direction.West)) {
               mazeData[i * 3 + 0][j * 3] = 1;
               mazeData[i * 3 + 1][j * 3] = 1;
               mazeData[i * 3 + 2][j * 3] = 1;
               mazeData[i * 3 + 3][j * 3] = 1;
            }
            if (current.getSide(Direction.East)) {
               mazeData[i * 3 + 0][j * 3 + 3] = 1;
               mazeData[i * 3 + 1][j * 3 + 3] = 1;
               mazeData[i * 3 + 2][j * 3 + 3] = 1;
               mazeData[i * 3 + 3][j * 3 + 3] = 1;
            }
         }
      }
      return mazeData;
   }

   public Cell[][] getMaze() {

      for (int i = 0; i < column; i++) {
         for (int j = 0; j < row; j++) {
            maze[j][i] = new Cell(i, j, false, false, false, false);
            if (j == 0) {
               maze[j][i].setSide(true, Direction.North);
            }
            if (j == row - 1) {
               maze[j][i].setSide(true, Direction.South);
            }
            if (i == 0) {
               maze[j][i].setSide(true, Direction.West);
            }
            if (i == column - 1) {
               maze[j][i].setSide(true, Direction.East);
            }
         }
      }
      generateMaze(0, 0, column - 1, row - 1);
      return maze;
   }

   private void generateMaze(int x1, int y1, int x2, int y2) {
      int value = MathUtils.random(1);
      // Horizontal divide
      if (value == 0) {
         if (y1 < y2) {
            int middle = ((y2 - y1) / 2) + y1;
            for (int i = x1; i <= x2; i++) {
               maze[middle][i].setSide(true, Direction.South);
               maze[middle + 1][i].setSide(true, Direction.North);

            }
            int gate = MathUtils.random(x2 - x1 ) + x1;

            maze[middle][gate].setSide(false, Direction.South);
            maze[middle + 1][gate].setSide(false, Direction.North);
            generateMaze(x1, y1, x2, middle);
            generateMaze(x1, middle + 1, x2, y2);
         }
      }

      // Vertical divide
      if (value == 1) {
         if (x1 < x2) {
            int middle = ((x2 - x1) / 2) + x1;
            for (int i = y1; i <= y2; i++) {
               maze[i][middle].setSide(true, Direction.East);
               maze[i][middle + 1].setSide(true, Direction.West);
            }
            int gate =  MathUtils.random(y2 - y1 ) + y1;

            maze[gate][middle].setSide(false, Direction.East);
            maze[gate][middle + 1].setSide(false, Direction.West);

            generateMaze(x1, y1, middle, y2);
            generateMaze(middle + 1, y1, x2, y2);
         }
      }
   }


   public void printMaze() {
      for (int i = 0; i < column; i++) {
         for (int j = 0; j < row; j++) {
            System.out.println(maze[j][i].toString());
         }
      }
   }
}
