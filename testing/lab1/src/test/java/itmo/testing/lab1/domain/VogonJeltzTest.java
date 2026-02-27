package itmo.testing.lab1.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class VogonJeltzTest {

    @Test
    void shouldHaveExpectedInitialState() {
        VogonJeltz vogon = new VogonJeltz();

        assertFalse(vogon.isMuscleSequenceForgotten());
        assertFalse(vogon.isRested());
        assertEquals(SmileSpeed.NOT_SMILING, vogon.getSmileSpeed());
        assertEquals(EmotionalState.NEUTRAL, vogon.getEmotionalState());
    }

    @Test
    void shouldSmileVerySlowlyWhenMuscleSequenceIsForgotten() {
        VogonJeltz vogon = new VogonJeltz();

        vogon.forgetCorrectMuscleSequence();
        vogon.smile();

        assertEquals(SmileSpeed.VERY_SLOW, vogon.getSmileSpeed());
        assertTrue(vogon.isMuscleSequenceForgotten());
    }

    @Test
    void shouldBecomeRestedAfterRefreshingShouts() {
        VogonJeltz vogon = new VogonJeltz();

        vogon.performRefreshingSeriesOfShoutsOnPrisoners();

        assertTrue(vogon.isRested());
        assertEquals(EmotionalState.READY_FOR_SMALL_VILENESS, vogon.getEmotionalState());
        assertFalse(vogon.isMuscleSequenceForgotten());
        assertEquals(SmileSpeed.NOT_SMILING, vogon.getSmileSpeed());
    }

    @Test
    void shouldNotBeReadyForVilenessBeforeShouts() {
        VogonJeltz vogon = new VogonJeltz();

        assertFalse(vogon.isRested());
        assertEquals(EmotionalState.NEUTRAL, vogon.getEmotionalState());
    }

    @Test
    void shouldSmileNormallyWhenMuscleSequenceIsRemembered() {
        VogonJeltz vogon = new VogonJeltz();

        vogon.smile();

        assertEquals(SmileSpeed.NORMAL, vogon.getSmileSpeed());
        assertFalse(vogon.isMuscleSequenceForgotten());
    }
}
