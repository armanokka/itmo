package objects;

// Класс, представляющий дом

public class House extends AbstractStructure {
    public House(String description) {
        super(description);
    }

    @Override
    public String getText() {
        return super.getDescription();
    }
}
