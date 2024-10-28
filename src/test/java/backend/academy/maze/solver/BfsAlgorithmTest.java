package backend.academy.maze.solver;

import backend.academy.maze.generator.Cell;
import backend.academy.maze.solver.algorithms.BfsAlgorithm;
import backend.academy.maze.solver.algorithms.Solver;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Testing the maze solution using the BFS Algorithm")
public class BfsAlgorithmTest {

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
        maze[2][1] = new Cell(2, 1, Cell.Type.WALL);
        maze[2][3] = new Cell(2, 3, Cell.Type.WALL);
        return maze;
    }

    @Test
    @DisplayName("Checking the Prim's algorithm path with an existing path")
    public void testBfsAlgorithmWithSolution() {
        int height = 5;
        int width = 5;
        Cell[][] maze = getKnownMaze();
        Coordinate start = new Coordinate(1, 1);
        Coordinate end = new Coordinate(3, 3);
        Solver bfsAlgorithm = new BfsAlgorithm();

        List<Coordinate> path = bfsAlgorithm.solve(maze, start, end, height, width);
        assertNotNull(path);
        assertEquals(start, path.getFirst());
        assertEquals(end, path.getLast());
    }

    @Test
    @DisplayName("Checking for the absence of the Prim's algorithm path with a non-existent path")
    public void testBfsAlgorithmWithoutSolution() {
        int height = 5;
        int width = 5;
        Cell[][] maze = getKnownMaze();
        Coordinate start = new Coordinate(1, 1);
        Coordinate end = new Coordinate(2, 3);
        Solver bfsAlgorithm = new BfsAlgorithm();

        List<Coordinate> path = bfsAlgorithm.solve(maze, start, end, height, width);
        assertTrue(path.isEmpty());
    }
}
