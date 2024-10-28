package backend.academy.maze.generator;

import backend.academy.maze.generator.algorithms.Algorithm;
import backend.academy.maze.generator.algorithms.KruskalAlgorithm;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Testing the maze generation using the Kruskal Algorithm")
public class KruskalAlgorithmTest {

    private KruskalAlgorithm kruskalAlgorithm;
    private final int height = 10;
    private final int width = 10;

    @BeforeEach
    public void setUp() {
        kruskalAlgorithm = new KruskalAlgorithm(height, width);
    }

    @Test
    @DisplayName("Checking that the maze is generated of a given size")
    public void testHeightAndWidth() {
        GeneratorHandler generatorHandler = kruskalAlgorithm.generateMaze();

        assertEquals(height, generatorHandler.height());
        assertEquals(width, generatorHandler.width());
    }

    @Test
    @DisplayName("Checking that there are walls along the bounds of the maze")
    public void testBounds() {
        GeneratorHandler generatorHandler = kruskalAlgorithm.generateMaze();
        Cell[][] maze = generatorHandler.maze();

        for (int i = 0; i < height; i++) {
            assertEquals(Cell.Type.WALL, maze[i][0].type());
            assertEquals(Cell.Type.WALL, maze[i][width - 1].type());
        }
        for (int j = 0; j < width; j++) {
            assertEquals(Cell.Type.WALL, maze[0][j].type());
            assertEquals(Cell.Type.WALL, maze[height - 1][j].type());
        }
    }

    @Test
    @DisplayName("Checking that there are passages in the maze")
    public void testPassage() {
        GeneratorHandler generatorHandler = kruskalAlgorithm.generateMaze();
        Cell[][] maze = generatorHandler.maze();

        boolean passageFound = false;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (maze[i][j].type() == Cell.Type.PASSAGE
                    || maze[i][j].type() == Cell.Type.SAND
                    || maze[i][j].type() == Cell.Type.ICE) {
                    passageFound = true;
                    break;
                }
            }
        }

        assertTrue(passageFound);
    }

    @Test
    @DisplayName("Checking that the mazes are randomly generated and do not match")
    public void testRandomMaze() {
        Algorithm firstKruskalAlgorithm = new KruskalAlgorithm(30, 30);
        Cell[][] firstMaze = firstKruskalAlgorithm.generateMaze().maze();

        Algorithm secondKruskalAlgorithm = new KruskalAlgorithm(30, 30);
        Cell[][] secondMaze = secondKruskalAlgorithm.generateMaze().maze();

        boolean isDifferent = false;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (firstMaze[i][j].type() != secondMaze[i][j].type()) {
                    isDifferent = true;
                    break;
                }
            }
        }

        assertTrue(isDifferent);
    }

    @Test
    @DisplayName("Checking that all kinds of surfaces are found in the mazes")
    public void testAllCells() {
        Algorithm kruskalAlgorithm = new KruskalAlgorithm(40, 40);
        Cell[][] maze = kruskalAlgorithm.generateMaze().maze();
        Set<Cell.Type> foundTypes = new HashSet<>();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                foundTypes.add(maze[i][j].type());
            }
        }

        assertTrue(foundTypes.contains(Cell.Type.PASSAGE));
        assertTrue(foundTypes.contains(Cell.Type.SAND));
        assertTrue(foundTypes.contains(Cell.Type.ICE));
        assertTrue(foundTypes.contains(Cell.Type.WALL));
    }

}
