package backend.academy.maze.solver.algorithms;

import backend.academy.maze.generator.Cell;
import backend.academy.maze.solver.Coordinate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Класс реализует поиск решения лабиринта алгоритмом поиска в ширину
 */
public class BfsAlgorithm implements Solver {
    /**
     * Метод для решения лабиринта поиском в ширину
     * @param maze Лабиринт в виде двумерного массива
     * @param start Координата начала
     * @param end Координата конца
     * @param height Высота лабиринта
     * @param width Ширина лабиринта
     * @return Лист координат, содержащий путь от начальной точки до конечной, если таковой имеется
     */
    @Override
    public List<Coordinate> solve(Cell[][] maze, Coordinate start, Coordinate end, int height, int width) {

        Queue<Coordinate> queue = new LinkedList<>();

        boolean[][] visited = new boolean[height][width];

        Map<Coordinate, Coordinate> previous = new HashMap<>();

        int[][] directions = {
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1}
        };

        queue.add(start);
        visited[start.row()][start.col()] = true;

        while (!queue.isEmpty()) {
            Coordinate current = queue.poll();

            if (current.equals(end)) {
                return constructPath(previous, end);
            }

            for (int[] direction : directions) {
                int a = 0;
                int b = 1;
                int newRow = current.row() + direction[a];
                int newCol = current.col() + direction[b];

                if (isInBounds(newRow, newCol, height, width)
                    && !visited[newRow][newCol]
                    && maze[newRow][newCol].type() != Cell.Type.WALL) {

                    Coordinate next = new Coordinate(newRow, newCol);
                    queue.add(next);
                    visited[newRow][newCol] = true;
                    previous.put(next, current);
                }
            }
        }

        return new ArrayList<>();
    }

    /**
     * Восстанавливает путь из предыдущих клеток
     * @param previous Мап предыдущих клеток
     * @param end Конечная точка пути
     * @return Лист координат от начала до конца
     */
    private List<Coordinate> constructPath(Map<Coordinate, Coordinate> previous, Coordinate end) {
        List<Coordinate> path = new ArrayList<>();
        for (Coordinate at = end; at != null; at = previous.get(at)) {
            path.add(at);
        }

        Collections.reverse(path);
        return path;
    }

    /**
     * Проверяет, находится ли клетка в пределах лабиринта
     * @param row Строка клетки
     * @param col Столбец клетки
     * @param height Высота лабиринта
     * @param width Ширина лабиринта
     * @return Лежит ли клетка в пределах лабиринта
     */
    private boolean isInBounds(int row, int col, int height, int width) {
        return row >= 0 && row < height && col >= 0 && col < width;
    }
}
