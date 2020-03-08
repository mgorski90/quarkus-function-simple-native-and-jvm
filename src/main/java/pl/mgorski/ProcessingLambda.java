package pl.mgorski;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import lombok.RequiredArgsConstructor;

import javax.inject.Named;

@Named("capitalizer")
@RequiredArgsConstructor
public class ProcessingLambda implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private static final int SUCCESS_CODE = 200;

    private final JacksonSerializer jacksonSerializer;
    private final Capitalizer capitalizer;

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent request, Context context) {
        String body = request.getBody();
        Message message = jacksonSerializer.from(body);
        Message processedMessage = capitalizer.capitalize(message);
        String response = jacksonSerializer.toJson(processedMessage);
        return new APIGatewayProxyResponseEvent()
                .withBody(response)
                .withStatusCode(SUCCESS_CODE);
    }

}
