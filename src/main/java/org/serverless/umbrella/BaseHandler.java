package org.serverless.umbrella;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;

import static java.util.Collections.singletonMap;

@RequiredArgsConstructor
public abstract class BaseHandler<T, R> implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    protected final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    protected final Class<T> inputType;

    @Override
    public APIGatewayProxyResponseEvent handleRequest(final APIGatewayProxyRequestEvent input, final Context context) {
        try {
            log(context, "Starting processing request %s with input: %s", input.getRequestContext().getRequestId(), input.getBody());

            final var request = gson.fromJson(input.getBody(), inputType);
            final var response = gson.toJson(doHandleRequest(request));

            log(context, "Completing processing request %s with output: %s", input.getRequestContext().getRequestId(), response);

            return new APIGatewayProxyResponseEvent()
                    .withStatusCode(200)
                    .withHeaders(singletonMap("Content-Type", "application/json"))
                    .withBody(response);
        } catch (Exception e) {
            log(context, "Error occurred while processing request %s: %s", input.getRequestContext().getRequestId(), e.getMessage());
            return new APIGatewayProxyResponseEvent()
                    .withStatusCode(500)
                    .withHeaders(singletonMap("Content-Type", "application/json"))
                    .withBody("Error occurred while processing request: " + e.getMessage());
        }
    }

    protected abstract R doHandleRequest(final T input);

    protected void log(Context context, String message, Object... args) {
        context.getLogger().log(String.format(message, args));
    }
}
