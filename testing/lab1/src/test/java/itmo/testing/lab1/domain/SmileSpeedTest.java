package itmo.testing.lab1.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class SmileSpeedTest {

    @Test
    void shouldContainAllExpectedSmileSpeedsInStableOrder() {
        SmileSpeed[] expected = {
                SmileSpeed.NOT_SMILING,
                SmileSpeed.VERY_SLOW,
                SmileSpeed.NORMAL
        };

        assertArrayEquals(expected, SmileSpeed.values());
    }
}
