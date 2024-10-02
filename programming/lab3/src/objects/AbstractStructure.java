package objects;

import interfaces.Printable;

import java.util.Objects;

// Абстрактный класс для представления абстрактного строения
public abstract class AbstractStructure implements Printable {
    private String description;

    public AbstractStructure(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    // Переопределение метода toString()
    @Override
    public String toString() {
        return getText();
    }

    // Переопределение метода equals()
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        AbstractStructure that = (AbstractStructure) obj;
        return Objects.equals(description, that.description);
    }

    // Переопределение метода hashCode()
    @Override
    public int hashCode() {
        return Objects.hash(description);
    }
}
