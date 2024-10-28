package backend.academy.maze.solver;

import backend.academy.maze.generator.Cell;
import backend.academy.maze.solver.algorithms.BfsAlgorithm;
import backend.academy.maze.solver.algorithms.DijkstraAlgorithm;
import backend.academy.maze.solver.algorithms.Solver;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    private Map<String, Solver> solversMap;
    private final int height;
    private final int width;
    private final Cell[][] maze;
    public final Coordinate pointA;
    public final Coordinate pointB;

    /**
     * Конструктор класса
     *
     * @param height Высота лабиринта
     * @param width  Ширина Лабиринта
     * @param maze   Лабиринт в виде двумерного массива
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
        iniSolversMap();
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
     *
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
        return solversMap.get(choice);
    }

    /**
     * Метод для инициализации мапы алгоритмов решения
     */
    private void iniSolversMap() {
        solversMap = new HashMap<>();
        solversMap.put("1", new DijkstraAlgorithm());
        solversMap.put("2", new BfsAlgorithm());
    }

}
