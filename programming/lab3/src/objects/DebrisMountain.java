package objects;

// Класс, представляющий гору обломков

public class DebrisMountain extends AbstractStructure {
    public DebrisMountain(String description) {
        super(description);
    }

    @Override
    public String getText() {
        return super.getDescription();
    }
}
