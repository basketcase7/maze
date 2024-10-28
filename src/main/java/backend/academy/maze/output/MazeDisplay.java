package backend.academy.maze.output;

import backend.academy.maze.generator.Cell;
import backend.academy.maze.solver.Coordinate;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import lombok.experimental.UtilityClass;
import static backend.academy.maze.output.OutputClass.printSmth;

/**
 * Класс для отображения лабиринта на пользовательском интерфейсе
 */
@UtilityClass
public class MazeDisplay {

    private Map<Cell.Type, String> surfacesMap;

    /**
     * Вывод сгенерированного лабиринта
     *
     * @param maze   Лабиринт в виде двумерного массива
     * @param height Высота лабиринта
     * @param width  Ширина лабиринта
     */
    public static void printMaze(Cell[][] maze, int height, int width) {
        initSurfacesMap();
        for (int row = 0; row < height; row++) {
            StringBuilder line = new StringBuilder();
            for (int col = 0; col < width; col++) {
                line.append(surfacesMap.get(maze[row][col].type()));
            }
            printSmth(String.valueOf(line));
        }
    }

    /**
     * Вывод лабиринта с решением
     *
     * @param path   Лист координат, из которых состоит путь от точки A к точке B
     * @param height Высота лабиринта
     * @param width  Ширина лабиринта
     * @param pointA Координата точки A
     * @param pointB Координата точки B
     * @param maze   Лабиринт в виде двумерного массива
     */
    public static void printMazeWithSolution(
        List<Coordinate> path,
        int height,
        int width,
        Coordinate pointA,
        Coordinate pointB,
        Cell[][] maze
    ) {
        initSurfacesMap();
        for (int row = 0; row < height; row++) {
            StringBuilder line = new StringBuilder();

            for (int col = 0; col < width; col++) {
                char symbol;

                Coordinate current = new Coordinate(row, col);

                if (current.equals(pointA)) {
                    symbol = 'A';
                } else if (current.equals(pointB)) {
                    symbol = 'B';
                } else if (path.contains(current)) {
                    symbol = '.';
                } else {
                    symbol = surfacesMap.get(maze[row][col].type()).charAt(0);
                }
                line.append(symbol);
            }
            printSmth(line.toString());
        }
    }

    /**
     * Метод для инициализации мапы поверхностей
     */
    private void initSurfacesMap() {
        surfacesMap = new EnumMap<>(Cell.Type.class);
        surfacesMap.put(Cell.Type.WALL, "█");
        surfacesMap.put(Cell.Type.PASSAGE, " ");
        surfacesMap.put(Cell.Type.SAND, "░");
        surfacesMap.put(Cell.Type.ICE, "○");
    }

}
