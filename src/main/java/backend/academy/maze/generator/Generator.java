package backend.academy.maze.generator;

import backend.academy.maze.generator.algorithms.Algorithm;

/**
 * Интерфейс, который реализуется {@link GeneratorHandler}
 */
public interface Generator {

    GeneratorHandler generate(int height, int width, Algorithm algorithm);

}
