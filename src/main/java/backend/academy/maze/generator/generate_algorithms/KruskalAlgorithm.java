package backend.academy.maze.generator.generate_algorithms;

import backend.academy.maze.generator.Cell;
import backend.academy.maze.generator.GeneratorHandler;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import lombok.Getter;

/**
 * Класс, содержащий логику генерации лабиринтов алгоритмом Краскала
 */
public class KruskalAlgorithm implements Algorithm {

    @Getter
    private final int height;
    @Getter
    private final int width;
    private final int passageTypes = 3;
    private final int passageMultiplier = 3;
    private final Cell[][] maze;
    private final List<Edge> edges;
    private final int[] parent;
    private final Random random = new SecureRandom();

    /**
     * Конструктор класса
     * @param height высота лабиринта
     * @param width ширина лабиринта
     */
    public KruskalAlgorithm(int height, int width) {
        this.height = height;
        this.width = width;
        this.maze = new Cell[height][width];
        this.edges = new ArrayList<>();
        this.parent = new int[height * width];

        for (int row = 0; row < this.height; row++) {
            for (int col = 0; col < this.width; col++) {
                maze[row][col] = new Cell(row, col, Cell.Type.WALL);
            }
        }

        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }

        generateEdges();
    }

    /**
     * Генерирует список ребер между клетками лабиринта
     */
    private void generateEdges() {
        for (int row = 1; row < height - 1; row++) {
            for (int col = 1; col < width - 1; col++) {
                if (row > 1) {
                    edges.add(new Edge(row, col, row - 1, col, getRandomCellType()));
                }
                if (col > 1) {
                    edges.add(new Edge(row, col, row, col - 1, getRandomCellType()));
                }
            }
        }

        Collections.shuffle(edges, random);
    }

    /**
     * Генерирует лабиринт
     * @return объект {@link GeneratorHandler}, содержащий сгенерированный лабиринт
     */
    @Override
    public GeneratorHandler generateMaze() {
        int wallsRemoved = 0;

        for (Edge edge : edges) {
            int cell1Index = edge.row1 * width + edge.col1;
            int cell2Index = edge.row2 * width + edge.col2;

            if (find(cell1Index) != find(cell2Index)) {
                union(cell1Index, cell2Index);
                maze[edge.row1][edge.col1] = new Cell(edge.row1, edge.col1, edge.type);
                maze[edge.row2][edge.col2] = new Cell(edge.row2, edge.col2, edge.type);
                wallsRemoved++;
            }

            if (wallsRemoved >= (height * width) / passageMultiplier) {
                break;
            }
        }

        GeneratorHandler generatedGeneratorHandler = new GeneratorHandler();
        generatedGeneratorHandler.height(height);
        generatedGeneratorHandler.width(width);
        generatedGeneratorHandler.maze(maze);
        return generatedGeneratorHandler;
    }

    /**
     * Находит корневой элемент множества для заданного индекса
     * @param x индекс клетки
     * @return корневой элемент множества
     */
    private int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    /**
     * Объединяет два множества
     * @param x индекс первой клетки
     * @param y индекс второй клетки
     */
    private void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX != rootY) {
            parent[rootY] = rootX;
        }
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
     * Приватный класс для представления ребер между клетками
     */
    private static class Edge {
        int row1;
        int col1;
        int row2;
        int col2;
        Cell.Type type;

        Edge(int row1, int col1, int row2, int col2, Cell.Type type) {
            this.row1 = row1;
            this.col1 = col1;
            this.row2 = row2;
            this.col2 = col2;
            this.type = type;
        }
    }
}
