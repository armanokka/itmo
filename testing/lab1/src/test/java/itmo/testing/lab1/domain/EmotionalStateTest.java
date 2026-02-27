package itmo.testing.lab1.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class EmotionalStateTest {

    @Test
    void shouldContainAllExpectedEmotionalStatesInStableOrder() {
        EmotionalState[] expected = {
                EmotionalState.NEUTRAL,
                EmotionalState.RESTED,
                EmotionalState.READY_FOR_SMALL_VILENESS
        };

        assertArrayEquals(expected, EmotionalState.values());
    }
}
