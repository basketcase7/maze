package backend.academy.maze.generator.algorithms;

import backend.academy.maze.generator.Cell;
import backend.academy.maze.generator.GeneratorHandler;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lombok.Getter;

/**
 * Класс, содержащий логику генерации лабиринтов алгоритмом Прима
 */
public class PrimAlgorithm implements Algorithm {

    @Getter
    private final int height;
    @Getter
    private final int width;
    private final int passageTypes = 3;
    private final Cell[][] maze;
    private final Random random = new SecureRandom();
    private final List<Wall> walls = new ArrayList<>();

    /**
     * Конструктор класса
     * @param height Высота лабиринта
     * @param width Ширина лабиринта
     */
    public PrimAlgorithm(int height, int width) {
        this.height = height;
        this.width = width;
        this.maze = new Cell[height][width];

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                maze[row][col] = new Cell(row, col, Cell.Type.WALL);
            }
        }
    }

    /**
     * Генерирует лабиринт
     * @return объект {@link GeneratorHandler}, содержащий сгенерированный лабиринт
     */
    @Override
    public GeneratorHandler generateMaze() {
        GeneratorHandler generatedGeneratorHandler = new GeneratorHandler();
        generatedGeneratorHandler.height(height);
        generatedGeneratorHandler.width(width);
        generatedGeneratorHandler.maze(maze);

        int startRow = random.nextInt(height - 2) + 1;
        int startCol = random.nextInt(width - 2) + 1;
        maze[startRow][startCol] = new Cell(startRow, startCol, getRandomCellType());
        addWalls(startRow, startCol);

        while (!walls.isEmpty()) {
            Wall wall = walls.remove(random.nextInt(walls.size()));

            if (canOpenWall(wall.row, wall.col)) {
                maze[wall.row][wall.col] = new Cell(wall.row, wall.col, getRandomCellType());
                addWalls(wall.row, wall.col);
            }
        }
        return generatedGeneratorHandler;
    }

    /**
     * Добавляет соседние стены в список
     * @param row строка текущей стенки
     * @param col колонка текущей стенки
     */
    private void addWalls(int row, int col) {
        if (row > 1 && maze[row - 1][col].type() == Cell.Type.WALL) {
            walls.add(new Wall(row - 1, col));
        }
        if (row < height - 2 && maze[row + 1][col].type() == Cell.Type.WALL) {
            walls.add(new Wall(row + 1, col));
        }
        if (col > 1 && maze[row][col - 1].type() == Cell.Type.WALL) {
            walls.add(new Wall(row, col - 1));
        }
        if (col < width - 2 && maze[row][col + 1].type() == Cell.Type.WALL) {
            walls.add(new Wall(row, col + 1));
        }
    }

    /**
     *
     * Проверяет, можно ли создать проход
     * @param row строка текущей стенки
     * @param col колонка текущей стенки
     * @return Можно ли открыть проход
     */
    private boolean canOpenWall(int row, int col) {
        int openCells = 0;

        if (row > 1 && maze[row - 1][col].type() != Cell.Type.WALL) {
            openCells++;
        }
        if (row < height - 2 && maze[row + 1][col].type() != Cell.Type.WALL) {
            openCells++;
        }
        if (col > 1 && maze[row][col - 1].type() != Cell.Type.WALL) {
            openCells++;
        }
        if (col < width - 2 && maze[row][col + 1].type() != Cell.Type.WALL) {
            openCells++;
        }

        return openCells == 1;
    }

    /**
     * Генерирует случайный тип поверхности прохода
     * @return Тип поверхности прохода (Обычный проход, лед или песок)
     */
    private Cell.Type getRandomCellType() {
        int randomCellType = random.nextInt(passageTypes);
        return switch (randomCellType) {
            case 0 -> Cell.Type.PASSAGE;
            case 1 -> Cell.Type.SAND;
            case 2 -> Cell.Type.ICE;
            default -> Cell.Type.WALL;
        };
    }

    /**
     * Приватный класс для представления стен
     */
    private static class Wall {
        int row;
        int col;

        Wall(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

}
