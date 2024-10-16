package backend.academy.maze;

import backend.academy.maze.generator.GeneratorHandler;
import backend.academy.maze.solver.SolveHandler;

/**
 * Класс, который поочередно, сначала создает генератор и генерирует лабиринт, затем солвер и решает лабиринт
 */
public class MazeHandler {
    public void handle() {
        GeneratorHandler newGeneratorHandler = new GeneratorHandler();
        newGeneratorHandler.handle();
        SolveHandler solveHandler =
            new SolveHandler(newGeneratorHandler.height(), newGeneratorHandler.width(), newGeneratorHandler.maze(),
                newGeneratorHandler.aCoordinate(),
                newGeneratorHandler.bCoordinate());
        solveHandler.handle();
    }

}
