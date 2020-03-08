package pl.mgorski;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import java.io.IOException;

@Singleton
public class JacksonSerializer {

    private ObjectMapper mapper;

    @PostConstruct
    private void init() {
        mapper = new ObjectMapper();
    }

    public Message from(String rawMessage) {
        try {
            return mapper.readValue(rawMessage, Message.class);
        } catch (IOException e) {
            throw new RuntimeException("Error parsing input object", e);
        }
    }

    public String toJson(Message rawMessage) {
        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rawMessage);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error marshalling object", e);
        }
    }

}
