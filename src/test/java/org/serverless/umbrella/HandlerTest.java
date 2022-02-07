package org.serverless.umbrella;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HandlerTest {

    private Handler handler = new Handler();

    @Test
    void shouldReturnGreeting() {
        final var actual = handler.handleRequest("Adam Smith", null);
        assertEquals( "Hello, Adam Smith!", actual);
    }
}
