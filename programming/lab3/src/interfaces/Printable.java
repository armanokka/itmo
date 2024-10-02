package interfaces;

// Интерфейс для представления структуры, имеющей текстовое представление
public interface Printable {
    default String getText() {
        return null;
    }
}

