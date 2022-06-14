package org.serverless.umbrella;

import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class Handler
        extends BaseHandler<Handler.DataMaskingRequest, Handler.DataMaskingResponse>
        implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    public Handler() { super(DataMaskingRequest.class); }

    @Override
    protected DataMaskingResponse doHandleRequest(DataMaskingRequest input) {
        return new DataMaskingResponse(EmailMasker.mask(input.data));
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
