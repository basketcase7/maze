package backend.academy.maze.output;

import java.io.PrintStream;
import lombok.experimental.UtilityClass;

/**
 * Класс для отображения пользовательского интерфейса
 */
@UtilityClass
public class OutputClass {

    public static PrintStream output = System.out;

    public static final String OPEN_MESSAGE = """
                **************************************
                *                                    *
                *                                    *
                *       Welcome to the "Maze"        *
                *                                    *
                *  The maze will generate            *
                *  a maze and show you               *
                *  how to pass it!                   *
                *                                    *
                *                                    *
                **************************************
        """;
    public static final String CHOOSE_HEIGHT = """
        Enter the height of the desired maze. Choose a number from 5 to 30.
        """;
    public static final String CHOOSE_WIDTH = """
        Enter the width of the desired maze. Choose a number from 5 to 150
        """;
    public static final String CHOOSE_ALGORITHM = """
        Enter the generation algorithm of the desired maze:
        1. Prim's Algorithm (one way from A to B)
        2. Kruskal Algorithm (0 or a set of paths from A to B)

        Enter "exit" so that the program completes its work
        """;
    public static final String SURFACE_TYPES = """
        Surface types:
        █ - Wall (no way)
          - Passage (default surface)
        ○ - Ice (fast surface)
        ░ - Sand (slow surface)
        """;
    public static final String CHOOSE_SOLVER = """
        Choose an algorithm to complete the maze:
        1. Dijkstra's Algorithm (Recommended for Kruskal Generation, algorithm takes into account different surfaces)
        2. BFS Algorithm (Recommended for Prim's Generation, algorithm does not takes into account different surfaces)

        Enter "exit" so that the program completes its work
        """;

    public static final String INVALID_INPUT = "Invalid input, try again";

    /**
     * Вывод различных сообщений
     * @param out Строка, которую нужно вывести
     */
    public static void printSmth(String out) {
        output.println(out);
    }

    /**
     * Отображение сообщения о вводе координаты A
     * @param height Высота лабиринта - 1, чтобы начальной точкой не могла стать граница лабиринта
     * @param width Ширина лабиринта - 1, чтобы начальной точкой не могла стать граница лабиринта
     * @return Строка с сообщением о вводе координаты
     */
    public static String getChooseA(int height, int width) {
        return String.format("""
            Select the starting coordinate "A"%n\
            by entering a value for height from 2 to %d%n\
            and a value for width from 2 to %d%n\
            """, height, width);
    }

    /**
     * Отображение сообщения о вводе координаты B
     * @param height Высота лабиринта - 1, чтобы конечной точкой не могла стать граница лабиринта
     * @param width Ширина лабиринта - 1, чтобы конечной точкой не могла стать граница лабиринта
     * @return Строка с сообщением о вводе координаты
     */
    public static String getChooseB(int height, int width) {
        return String.format("""
            Select the ending coordinate "B"%n\
            by entering a value for height from 2 to %d%n\
            and a value for width from 2 to %d%n\
            """, height, width);
    }
}
