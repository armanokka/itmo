package objects;

import interfaces.Assessable;
import interfaces.Printable;

import java.util.Objects;

// Класс, представляющий бумагу

public class Paper implements Printable, Assessable {
    private String content;

    public Paper(String content) {
        this.content = content;
    }

    // Переопределение метода getText()
    @Override
    public String getText() {
        return content;
    }

    // Переопределение метода assess()
    @Override
    public boolean assess(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Paper paper = (Paper) obj;
        return Objects.equals(content, paper.content);
    }
}
