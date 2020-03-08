package pl.mgorski;

import org.apache.commons.lang3.StringUtils;

import javax.inject.Singleton;

@Singleton
public class Capitalizer {

    Message capitalize(Message message) {
        return message.toBuilder()
                .content(StringUtils.capitalize(message.getContent()))
                .build();
    }

}
