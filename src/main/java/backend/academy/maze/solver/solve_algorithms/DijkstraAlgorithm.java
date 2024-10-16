package backend.academy.maze.solver.solve_algorithms;

import backend.academy.maze.generator.Cell;
import backend.academy.maze.solver.Coordinate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Класс реализует поиск решения лабиринта алгоритмом Дейкстры, т.е. учитывая различные типы поверхностей
 */
public class DijkstraAlgorithm implements Solver {
    /**
     * Метод для решения лабиринта алгоритмом Дейкстры
     * @param maze Лабиринт в виде двумерного массива
     * @param start Координата начала
     * @param end Координата конца
     * @param height Высота лабиринта
     * @param width Ширина лабиринта
     * @return Лист координат, содержащий путь от начальной точки до конечной, если таковой имеется
     */
    @Override
    public List<Coordinate> solve(Cell[][] maze, Coordinate start, Coordinate end, int height, int width) {

        int[][] dist = new int[height][width];
        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.distance));
        Map<Coordinate, Coordinate> previous = new HashMap<>();

        dist[start.row()][start.col()] = 0;
        pq.add(new Node(start.row(), start.col(), 0));

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int curRow = current.row;
            int curCol = current.col;

            if (curRow == end.row() && curCol == end.col()) {
                return reconstructPath(previous, start, end);
            }

            for (int[] dir : directions) {
                int a = 0;
                int b = 1;
                int newRow = curRow + dir[a];
                int newCol = curCol + dir[b];

                if (newRow >= 0 && newRow < height && newCol >= 0 && newCol < width
                    && maze[newRow][newCol].type() != Cell.Type.WALL) {

                    int newDist = dist[curRow][curCol] + maze[newRow][newCol].type().weight();

                    if (newDist < dist[newRow][newCol]) {
                        dist[newRow][newCol] = newDist;
                        pq.add(new Node(newRow, newCol, newDist));
                        previous.put(new Coordinate(newRow, newCol), new Coordinate(curRow, curCol));
                    }
                }
            }
        }

        return Collections.emptyList();
    }

    /**
     * Восстанавливает путь из предыдущих пройденных клеток
     * @param previous Мап, содержащий предыдущие клетки
     * @param start Начальная точка
     * @param end Конечная точка
     * @return Лист координат от начала до конца
     */
    private List<Coordinate> reconstructPath(Map<Coordinate, Coordinate> previous, Coordinate start, Coordinate end) {
        List<Coordinate> path = new ArrayList<>();
        Coordinate step = end;

        while (step != null && !step.equals(start)) {
            path.add(step);
            step = previous.get(step);
        }

        if (step != null) {
            path.add(start);
        }

        Collections.reverse(path);
        return path;
    }

    /**
     * Приватный класс для информации о клетке
     */
    private static class Node {
        int row;
        int col;
        int distance;

        Node(int row, int col, int distance) {
            this.row = row;
            this.col = col;
            this.distance = distance;
        }
    }
}
