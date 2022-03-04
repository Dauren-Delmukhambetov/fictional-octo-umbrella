package org.serverless.umbrella;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class HandlerTest {

    private final Handler handler = new Handler();
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Test
    void shouldMaskData() {
        final var request = new Handler.DataMaskingRequest("Hello, world@example.com!");
        final var expected = new Handler.DataMaskingResponse("Hello, masked@email.com!");
        final var requestContext = new APIGatewayProxyRequestEvent.ProxyRequestContext()
                .withRequestId(UUID.randomUUID().toString());
        final var apiGatewayProxyRequestEvent = new APIGatewayProxyRequestEvent()
                .withRequestContext(requestContext)
                .withBody(gson.toJson(request));

        final var actual = handler.handleRequest(apiGatewayProxyRequestEvent, new LambdaTestContext());

        assertEquals( 200, actual.getStatusCode());
        assertEquals(gson.toJson(expected), actual.getBody());
    }
}
