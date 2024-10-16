package backend.academy.maze.output;

import backend.academy.maze.generator.Cell;
import backend.academy.maze.solver.Coordinate;
import java.util.List;
import lombok.experimental.UtilityClass;
import static backend.academy.maze.output.OutputClass.printSmth;

/**
 * Класс для отображения лабиринта на пользовательском интерфейсе
 */
@UtilityClass
public class MazeDisplay {

    private String unValue = "Unexpected value: ";

    /**
     * Вывод сгенерированного лабиринта
     * @param maze Лабиринт в виде двумерного массива
     * @param height Высота лабиринта
     * @param width Ширина лабиринта
     */
    public static void printMaze(Cell[][] maze, int height, int width) {

        for (int row = 0; row < height; row++) {
            StringBuilder line = new StringBuilder();

            for (int col = 0; col < width; col++) {
                char symbol;

                switch (maze[row][col].type()) {
                    case WALL -> symbol = '█';
                    case PASSAGE -> symbol = ' ';
                    case SAND -> symbol = '░';
                    case ICE -> symbol = '○';
                    default -> throw new IllegalStateException(unValue + maze[row][col].type());
                }
                line.append(symbol);
            }

            printSmth(String.valueOf(line));
        }
    }

    /**
     * Вывод лабиринта с решением
     * @param path Лист координат, из которых состоит путь от точки A к точке B
     * @param height Высота лабиринта
     * @param width Ширина лабиринта
     * @param pointA Координата точки A
     * @param pointB Координата точки B
     * @param maze Лабиринт в виде двумерного массива
     */
    public static void printMazeWithSolution(
        List<Coordinate> path,
        int height,
        int width,
        Coordinate pointA,
        Coordinate pointB,
        Cell[][] maze
    ) {
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

                    switch (maze[row][col].type()) {
                        case WALL -> symbol = '█';
                        case PASSAGE -> symbol = ' ';
                        case SAND -> symbol = '░';
                        case ICE -> symbol = '○';
                        default -> throw new IllegalStateException(unValue + maze[row][col].type());
                    }
                }
                line.append(symbol);
            }

            printSmth(line.toString());
        }
    }

}
