package backend.academy.maze.generator.generate_algorithms;

import backend.academy.maze.generator.GeneratorHandler;

/**
 * Интерфейс, который реализуется алгоритмами генерации лабиринтов
 */
public interface Algorithm {

    GeneratorHandler generateMaze();

}
