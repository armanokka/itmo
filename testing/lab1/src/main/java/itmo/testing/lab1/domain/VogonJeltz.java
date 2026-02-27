package itmo.testing.lab1.domain;

public class VogonJeltz {
    private boolean muscleSequenceForgotten;
    private boolean rested;
    private SmileSpeed smileSpeed;
    private EmotionalState emotionalState;

    public VogonJeltz() {
        this.muscleSequenceForgotten = false;
        this.rested = false;
        this.smileSpeed = SmileSpeed.NOT_SMILING;
        this.emotionalState = EmotionalState.NEUTRAL;
    }

    public void forgetCorrectMuscleSequence() {
        this.muscleSequenceForgotten = true;
    }

    public void smile() {
        if (muscleSequenceForgotten) {
            this.smileSpeed = SmileSpeed.VERY_SLOW;
            return;
        }
        this.smileSpeed = SmileSpeed.NORMAL;
    }

    public void performRefreshingSeriesOfShoutsOnPrisoners() {
        this.rested = true;
        this.emotionalState = EmotionalState.READY_FOR_SMALL_VILENESS;
    }

    public boolean isMuscleSequenceForgotten() {
        return muscleSequenceForgotten;
    }

    public boolean isRested() {
        return rested;
    }

    public SmileSpeed getSmileSpeed() {
        return smileSpeed;
    }

    public EmotionalState getEmotionalState() {
        return emotionalState;
    }
}
