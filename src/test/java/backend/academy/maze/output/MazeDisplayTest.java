package backend.academy.maze.output;

import backend.academy.maze.generator.Cell;
import backend.academy.maze.solver.Coordinate;
import backend.academy.maze.solver.solve_algorithms.BfsAlgorithm;
import backend.academy.maze.solver.solve_algorithms.DijkstraAlgorithm;
import backend.academy.maze.solver.solve_algorithms.Solver;
import java.io.PrintStream;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DisplayName("Testing the maze display")
public class MazeDisplayTest {

    private PrintStream mockPrintStream;

    @BeforeEach
    public void setUp() {
        mockPrintStream = Mockito.mock(PrintStream.class);
        OutputClass.output = mockPrintStream;
    }

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
    @DisplayName("Checking the correct display of the generated maze")
    public void printMazeWithoutSolutionTest() {
        int height = 5;
        int width = 5;
        Cell[][] maze = getKnownMaze();

        MazeDisplay.printMaze(maze, height, width);

        verify(mockPrintStream, times(2)).println("█████");
        verify(mockPrintStream, times(1)).println("█ ○○█");
        verify(mockPrintStream, times(1)).println("█░█○█");
        verify(mockPrintStream, times(1)).println("█░░ █");

    }

    @Test
    @DisplayName("Сhecking the correct display of the maze solved by the BFS algorithm")
    public void printMazeWithBfsSolutionTest() {
        int height = 5;
        int width = 5;
        Cell[][] maze = getKnownMaze();
        Coordinate start = new Coordinate(1, 1);
        Coordinate end = new Coordinate(3, 3);

        Solver bfsAlgorithm = new BfsAlgorithm();
        List<Coordinate> path = bfsAlgorithm.solve(maze, start, end, height, width);

        MazeDisplay.printMazeWithSolution(path, height, width, start, end, maze);

        verify(mockPrintStream, times(2)).println("█████");
        verify(mockPrintStream, times(1)).println("█A○○█");
        verify(mockPrintStream, times(1)).println("█.█○█");
        verify(mockPrintStream, times(1)).println("█..B█");
    }

    @Test
    @DisplayName("Сhecking the correct display of the maze solved by the Dijkstra algorithm")
    public void printMazeWithDijkstraSolutionTest() {
        int height = 5;
        int width = 5;
        Cell[][] maze = getKnownMaze();
        Coordinate start = new Coordinate(1, 1);
        Coordinate end = new Coordinate(3, 3);

        Solver dijkstraAlgorithm = new DijkstraAlgorithm();
        List<Coordinate> path = dijkstraAlgorithm.solve(maze, start, end, height, width);

        MazeDisplay.printMazeWithSolution(path, height, width, start, end, maze);

        verify(mockPrintStream, times(2)).println("█████");
        verify(mockPrintStream, times(1)).println("█A..█");
        verify(mockPrintStream, times(1)).println("█░█.█");
        verify(mockPrintStream, times(1)).println("█░░B█");
    }
}
