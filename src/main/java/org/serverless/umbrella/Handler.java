package org.serverless.umbrella;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static java.util.Collections.singletonMap;

public class Handler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {

        log(context, "Starting processing request {} with input: {}", input.getRequestContext().getRequestId(), input.getBody());

        final var request = gson.fromJson(input.getBody(), DataMaskingRequest.class);
        final var response = doMaskData(request);

        log(context, "Completing processing request {} with output: {}", input.getRequestContext().getRequestId(), response);

        return new APIGatewayProxyResponseEvent()
                .withStatusCode(200)
                .withHeaders(singletonMap("Content-Type", "application/json"))
                .withBody(gson.toJson(response));
    }

    protected DataMaskingResponse doMaskData(final DataMaskingRequest request) {
        return new DataMaskingResponse(EmailMasker.mask(request.data));
    }

    protected void log(Context context, String message, Object... args) {
        context.getLogger().log(String.format(message, args));
    }

    @Getter
    @RequiredArgsConstructor
    static class DataMaskingResponse {
        private final String data;
    }

    @Getter
    @RequiredArgsConstructor
    static class DataMaskingRequest {
        private final String data;
    }
}
