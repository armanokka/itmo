package objects;

// Класс, представляющий коридор

public class Corridor extends AbstractStructure {
    public Corridor(String description) {
        super(description);
    }

    @Override
    public String getText() {
        return super.getDescription();
    }
}
