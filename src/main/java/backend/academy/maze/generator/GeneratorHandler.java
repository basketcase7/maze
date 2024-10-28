package backend.academy.maze.generator;

import backend.academy.maze.generator.algorithms.Algorithm;
import backend.academy.maze.generator.algorithms.KruskalAlgorithm;
import backend.academy.maze.generator.algorithms.PrimAlgorithm;
import backend.academy.maze.solver.Coordinate;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import static backend.academy.maze.InputClass.SCANNER;
import static backend.academy.maze.InputClass.inputAlgorithm;
import static backend.academy.maze.InputClass.inputCoordinate;
import static backend.academy.maze.InputClass.inputHeight;
import static backend.academy.maze.InputClass.inputWidth;
import static backend.academy.maze.output.MazeDisplay.printMaze;
import static backend.academy.maze.output.OutputClass.CHOOSE_ALGORITHM;
import static backend.academy.maze.output.OutputClass.CHOOSE_HEIGHT;
import static backend.academy.maze.output.OutputClass.CHOOSE_WIDTH;
import static backend.academy.maze.output.OutputClass.INVALID_INPUT;
import static backend.academy.maze.output.OutputClass.OPEN_MESSAGE;
import static backend.academy.maze.output.OutputClass.SURFACE_TYPES;
import static backend.academy.maze.output.OutputClass.getChooseA;
import static backend.academy.maze.output.OutputClass.getChooseB;
import static backend.academy.maze.output.OutputClass.printSmth;

/**
 * Класс для управления генерацией лабиринта
 */
@Setter @Getter
public class GeneratorHandler implements Generator {

    private int height;
    private int width;
    private Cell[][] maze;
    private Algorithm algorithm;
    private Coordinate aCoordinate;
    private Coordinate bCoordinate;
    private Map<String, Algorithm> algorithmsMap;

    /**
     * Последовательно вызывает методы для настройки генерации лабиринта
     */
    public void handle() {
        printSmth(OPEN_MESSAGE);

        chooseHeight();
        chooseWidth();

        initAlgorithmsMap();

        printSmth(CHOOSE_ALGORITHM);
        this.algorithm(chooseGenerationAlgorithm());

        generate(height, width, algorithm);

        printMaze(maze, height, width);
        printSmth(SURFACE_TYPES);

        inputAB();
    }

    /**
     * Вызывает метод генерации лабиринта по заданным параметрам
     * @param height Высота желаемого лабиринта
     * @param width Ширина желаемого лабиринта
     * @param algorithm Желаемый алгоритм генерации
     * @return Сгенерированный лабиринт
     */
    @Override
    public GeneratorHandler generate(int height, int width, Algorithm algorithm) {
        GeneratorHandler generatedGeneratorHandler = algorithm.generateMaze();
        this.maze = generatedGeneratorHandler.maze();
        this.height = generatedGeneratorHandler.height();
        this.width = generatedGeneratorHandler.width();
        return this;
    }

    /**
     * Метод для выбора алгоритма генерации
     * @return Выбранный алгоритм генерации
     */
    private Algorithm chooseGenerationAlgorithm() {
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
        return algorithmsMap.get(choice);
    }

    /**
     * Метод для выбора точек A и B
     */
    private void inputAB() {
        int one = 1;
        printSmth(getChooseA(height - one, width - one));
        aCoordinate = inputCoordinate(height - one, width - one);
        printSmth(getChooseB(height - one, width - one));
        bCoordinate = inputCoordinate(height - one, width - one);
        while (aCoordinate.equals(bCoordinate)) {
            printSmth("Points A and B should be different!");
            bCoordinate = inputCoordinate(height - one, width - one);
        }
    }

    /**
     * Метод для выбора желаемой высоты лабиринта
     */
    private void chooseHeight() {
        printSmth(CHOOSE_HEIGHT);
        String localHeight;
        while (height == 0) {
            localHeight = SCANNER.nextLine();
            if (inputHeight(localHeight)) {
                height = Integer.parseInt(localHeight);
            } else {
                printSmth(INVALID_INPUT);
            }
        }
        this.height(height);
    }

    /**
     * Метод для выбора желаемой ширины лабиринта
     */
    private void chooseWidth() {
        printSmth(CHOOSE_WIDTH);
        String localWidth;
        while (width == 0) {
            localWidth = SCANNER.nextLine();
            if (inputWidth(localWidth)) {
                width = Integer.parseInt(localWidth);
            } else {
                printSmth(INVALID_INPUT);
            }
        }
        this.width(width);
    }

    /**
     * Метод для инициализации мапы с алгоритмами генерации
     */
    private void initAlgorithmsMap() {
        algorithmsMap = new HashMap<>();
        algorithmsMap.put("1", new PrimAlgorithm(this.height, this.width));
        algorithmsMap.put("2", new KruskalAlgorithm(this.height, this.width));
    }
}
