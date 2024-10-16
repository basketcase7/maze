package backend.academy.maze;

import backend.academy.maze.solver.Coordinate;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import lombok.experimental.UtilityClass;
import static backend.academy.maze.output.OutputClass.printSmth;

/**
 * Класс, организующий ввод пользователя, а также предоставляющий валидацию ввода
 */
@UtilityClass
public class InputClass {

    public final int minSize = 5;
    public final int maxHeight = 30;
    public final int maxWidth = 150;
    public final int algAmount = 2;
    public final int firstAlg = 1;

    public final String exitString = "exit";

    public static final Scanner SCANNER = new Scanner(System.in, StandardCharsets.UTF_8);

    /**
     * Валидация ввода высоты лабиринта
     *
     * @param input Высота лабиринта, которую пользователь желает установить
     * @return Можно ли установить такую высоту
     */
    public static boolean inputHeight(String input) {
        if (!checkInputValid(input)) {
            return false;
        }
        int height = Integer.parseInt(input);
        return height >= minSize && height <= maxHeight;
    }

    /**
     * Валидация ввода ширины лабиринта
     *
     * @param input Ширина лабиринта, которую пользователь желает установить
     * @return Можно ли установить такую ширину
     */
    public static boolean inputWidth(String input) {
        if (!checkInputValid(input)) {
            return false;
        }
        int width = Integer.parseInt(input);
        return width >= minSize && width <= maxWidth;
    }

    /**
     * Валидация выбора алгоритма
     * @param input Номер алгоритма, который желает использовать пользователь
     * @return Можно ли использовать алгоритм с таким номером
     */
    public static boolean inputAlgorithm(String input) {
        if (exitString.equalsIgnoreCase(input)) {
            System.exit(0);
        }
        if (!checkInputValid(input)) {
            return false;
        }
        int alg = Integer.parseInt(input);
        return alg >= firstAlg && alg <= algAmount;
    }

    /**
     * Организация и валидация ввода координат пользователем
     * @param height Максимальная высота, которая может быть выбрана для координаты
     * @param width Максимальная ширина, которая может быть выбрана для координаты
     * @return Координата начальной/конечной точки
     */
    public static Coordinate inputCoordinate(int height, int width) {
        int firstCoordinate = 0;
        int secondCoordinate = 0;
        printSmth("Enter two coordinates (height and width) separated by a space:");
        while (firstCoordinate == 0) {
            String input = SCANNER.nextLine();

            String[] coordinates = input.split("\\s+");

            if (coordinates.length != 2 || !checkInputValid(coordinates[0]) || !checkInputValid(coordinates[1])) {
                printSmth("Invalid input. Please enter two valid numbers separated by a space.");
                continue;
            }

            int coor1 = Integer.parseInt(coordinates[0]);
            int coor2 = Integer.parseInt(coordinates[1]);

            if (coor1 >= 2 && coor1 <= height && coor2 >= 2 && coor2 <= width) {
                firstCoordinate = coor1;
                secondCoordinate = coor2;
            } else {
                printSmth("Invalid input. Coordinates must be between 2 and " + (height)
                    + " for height, and between 2 and " + (width) + " for width.");
            }
        }

        return new Coordinate(firstCoordinate - 1, secondCoordinate - 1);
    }

    /**
     * Проверка, что пользователь ввел натуральное число
     * @param input Строка, введенная пользователем
     * @return Может ли строка быть натуральным числом
     */
    public Boolean checkInputValid(String input) {
        return input.matches("[1-9][0-9]*");
    }
}
