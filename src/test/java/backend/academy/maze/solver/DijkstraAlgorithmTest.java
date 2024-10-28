package backend.academy.maze.solver;

import backend.academy.maze.generator.Cell;
import backend.academy.maze.solver.algorithms.DijkstraAlgorithm;
import backend.academy.maze.solver.algorithms.Solver;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Testing the maze solution using the Dijkstra's Algorithm")
public class DijkstraAlgorithmTest {

    private Cell[][] getKnownMaze() {
        Cell[][] maze = new Cell[5][5];
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                if (row == 0 || row == 4 || col == 0 || col == 4) {
                    maze[row][col] = new Cell(row, col, Cell.Type.WALL);
                } else {
                    maze[row][col] = new Cell(row, col, Cell.Type.PASSAGE);
                }
            }
        }
        maze[2][2] = new Cell(2, 2, Cell.Type.WALL);

        maze[1][2] = new Cell(1, 2, Cell.Type.ICE);
        maze[1][3] = new Cell(1, 2, Cell.Type.ICE);
        maze[2][3] = new Cell(1, 2, Cell.Type.ICE);

        maze[2][1] = new Cell(1, 2, Cell.Type.SAND);
        maze[3][1] = new Cell(1, 2, Cell.Type.SAND);
        maze[3][2] = new Cell(1, 2, Cell.Type.SAND);

        return maze;
    }

    @Test
    @DisplayName("Checking that the Dijkstra's algorithm follows the most profitable route")
    public void testDijkstraAlgorithmForCost() {
        int height = 5;
        int width = 5;
        Cell[][] maze = getKnownMaze();
        Coordinate start = new Coordinate(1, 1);
        Coordinate end = new Coordinate(3, 3);
        Solver dijkstraAlgorithm = new DijkstraAlgorithm();

        List<Coordinate> path = dijkstraAlgorithm.solve(maze, start, end, height, width);

        assertNotNull(path);
        assertEquals(path.get(1).equals(new Coordinate(1, 2)), true);
        assertEquals(path.get(2).equals(new Coordinate(1, 3)), true);
        assertEquals(path.get(3).equals(new Coordinate(2, 3)), true);
    }

    @Test
    @DisplayName("Checking for the absence of the Dijkstra's algorithm a path with a non-existent path")
    public void testBfsAlgorithmWithoutSolution() {
        int height = 5;
        int width = 5;
        Cell[][] maze = getKnownMaze();
        Coordinate start = new Coordinate(1, 1);
        Coordinate end = new Coordinate(2, 2);
        Solver dijkstraAlgorithm = new DijkstraAlgorithm();

        List<Coordinate> path = dijkstraAlgorithm.solve(maze, start, end, height, width);
        assertTrue(path.isEmpty());
    }

    @Test
    @DisplayName("Checking the Dijkstra's algorithm path with an existing path")
    public void testBfsAlgorithmWithSolution() {
        int height = 5;
        int width = 5;
        Cell[][] maze = getKnownMaze();
        Coordinate start = new Coordinate(1, 1);
        Coordinate end = new Coordinate(3, 3);
        Solver dijkstraAlgorithm = new DijkstraAlgorithm();

        List<Coordinate> path = dijkstraAlgorithm.solve(maze, start, end, height, width);

        assertNotNull(path);
        assertEquals(start, path.getFirst());
        assertEquals(end, path.getLast());
    }
}
