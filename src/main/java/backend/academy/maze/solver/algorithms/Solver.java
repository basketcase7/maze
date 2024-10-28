package backend.academy.maze.solver.algorithms;

import backend.academy.maze.generator.Cell;
import backend.academy.maze.solver.Coordinate;
import java.util.List;

/**
 * Интерфейс, который реализуется алгоритмами решения лабиринта
 */
public interface Solver {
    List<Coordinate> solve(Cell[][] maze, Coordinate start, Coordinate end, int height, int width);
}
