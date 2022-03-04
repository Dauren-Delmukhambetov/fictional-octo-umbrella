package org.serverless.umbrella;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailMaskerTest {

    @Test
    @DisplayName("should replace email in input string")
    void shouldReplaceEmailInString() {
        final var input = "Hello, adam.smith@example.com";
        final var expected = "Hello, masked@email.com";
        assertEquals(expected, EmailMasker.mask(input));
    }

    @Test
    @DisplayName("should return the same string when no email")
    void shouldReturnTheSameString() {
        final var input = "Hello, Adele!";
        assertEquals(input, EmailMasker.mask(input));
    }
}
