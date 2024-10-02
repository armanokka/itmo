package objects;

import java.util.ArrayList;
import java.util.List;
// Класс, представляющий рюкзак Денфорта

public class DenfortsBackpack extends AbstractStructure {
    // Коллекция для хранения бумаги в рюкзаке
    private final List<Paper> papers;

    public DenfortsBackpack(String description) {
        super(description);
        this.papers = new ArrayList<>();
    }

    // Метод для добавления бумаги в рюкзак
    public void addPaper(Paper paper) {
        papers.add(paper);
        System.out.println("put " + paper.getText() + " in Denfort's backpack");
    }

    // Переопределение метода getText()
    @Override
    public String getText() {
        // Дополнительно выводим содержимое бумаги в рюкзаке
        StringBuilder result = new StringBuilder(super.getDescription() + " with papers:\n");
        for (Paper paper : papers) {
            result.append(paper.getText()).append("\n");
        }
        return result.toString();
    }
}
