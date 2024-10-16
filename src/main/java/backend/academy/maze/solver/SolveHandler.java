package backend.academy.maze.solver;

import backend.academy.maze.generator.Cell;
import backend.academy.maze.solver.solve_algorithms.BfsAlgorithm;
import backend.academy.maze.solver.solve_algorithms.DijkstraAlgorithm;
import backend.academy.maze.solver.solve_algorithms.Solver;
import java.util.List;
import static backend.academy.maze.InputClass.SCANNER;
import static backend.academy.maze.InputClass.inputAlgorithm;
import static backend.academy.maze.output.MazeDisplay.printMazeWithSolution;
import static backend.academy.maze.output.OutputClass.CHOOSE_SOLVER;
import static backend.academy.maze.output.OutputClass.INVALID_INPUT;
import static backend.academy.maze.output.OutputClass.printSmth;

/**
 * Класс для управления решением лабиринта
 */
public class SolveHandler {

    private final int height;
    private final int width;
    private final Cell[][] maze;
    public final Coordinate pointA;
    public final Coordinate pointB;

    /**
     * Конструктор класса
     * @param height Высота лабиринта
     * @param width Ширина Лабиринта
     * @param maze Лабиринт в виде двумерного массива
     * @param pointA Координата начала пути
     * @param pointB Координата конца пути
     */
    public SolveHandler(int height, int width, Cell[][] maze, Coordinate pointA, Coordinate pointB) {
        this.height = height;
        this.width = width;
        this.maze = maze;
        this.pointA = pointA;
        this.pointB = pointB;
    }

    /**
     * Метод организует решение лабиринта и его отображение с решением, если оно имеется
     */
    public void handle() {
        while (true) {
            printSmth(CHOOSE_SOLVER);
            Solver solver = chooseSolveAlgorithm();
            List<Coordinate> path = solver.solve(maze, pointA, pointB, height, width);
            if (path != null && !path.isEmpty()) {
                printMazeWithSolution(path, height, width, pointA, pointB, maze);
            } else {
                printSmth("No way.");
            }

            printSmth("");
        }
    }

    /**
     * Метод для выбора алгоритма решения лабиринта
     * @return Выбранный алгоритм решения
     */
    private Solver chooseSolveAlgorithm() {
        String localAlgorithm;
        String choice = "";
        while (choice.isEmpty()) {
            localAlgorithm = SCANNER.nextLine();
            if (inputAlgorithm(localAlgorithm)) {
                choice = localAlgorithm;
            } else {
                printSmth(INVALID_INPUT);
            }
        }
        return switch (choice) {
            case "1" -> new DijkstraAlgorithm();
            case "2" -> new BfsAlgorithm();
            default -> new DijkstraAlgorithm();
        };
    }

}
