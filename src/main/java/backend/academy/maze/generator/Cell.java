package backend.academy.maze.generator;

import lombok.Getter;

/**
 * Рекорд для сущности клетки
 * @param row Строчка клетки
 * @param col Столбец клетки
 * @param type Тип клетки
 */
public record Cell(int row, int col, Type type) {
    /**
     * Enum для представления различных видов поверхности лабиринта
     */
    public enum Type {
        WALL(0),
        PASSAGE(50),
        SAND(100),
        ICE(25);

        @Getter
        private final int weight;

        Type(int weight) {
            this.weight = weight;
        }
    }
}
