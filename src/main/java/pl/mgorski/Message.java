package pl.mgorski;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Builder;
import lombok.Data;

@RegisterForReflection
@Data
@JsonDeserialize(builder = Message.MessageBuilder.class)
@Builder(builderClassName = "MessageBuilder", toBuilder = true)
public class Message {

    private String content;

    @JsonPOJOBuilder(withPrefix = "")
    @RegisterForReflection
    public static class MessageBuilder {
        // Lombok will add constructor, setters, build method
    }

}
