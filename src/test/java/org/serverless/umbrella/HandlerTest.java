package org.serverless.umbrella;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HandlerTest {

    private final Handler handler = new Handler();

    @Test
    void shouldReturnGreeting() {
        final var input = new APIGatewayProxyRequestEvent()
                .withBody("Adam Smith");

        final var actual = handler.handleRequest(input, null);

        assertEquals( 200, actual.getStatusCode());
        assertEquals( "Hello, Adam Smith!", actual.getBody());
    }
}
