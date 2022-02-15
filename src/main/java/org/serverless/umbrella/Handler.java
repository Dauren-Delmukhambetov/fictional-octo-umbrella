package org.serverless.umbrella;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

import static java.lang.String.format;
import static java.util.Collections.singletonMap;

public class Handler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {

        final var greetedPerson = input.getBody();

        return new APIGatewayProxyResponseEvent()
                .withStatusCode(200)
                .withHeaders(singletonMap("Content-Type", "application/json"))
                .withBody(format("Hello, %s!", greetedPerson));
    }
}
