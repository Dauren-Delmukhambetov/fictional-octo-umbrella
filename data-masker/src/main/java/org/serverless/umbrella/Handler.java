package org.serverless.umbrella;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import static java.lang.String.format;

public class Handler implements RequestHandler<String, String> {

    @Override
    public String handleRequest(String input, Context context) {
        return format("Hello, %s!", input);
    }
}
