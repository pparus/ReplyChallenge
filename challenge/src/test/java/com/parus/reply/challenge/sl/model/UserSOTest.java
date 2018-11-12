package com.parus.reply.challenge.sl.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;


public class UserSOTest {

    @Test
    public void unmarshallingAndMarshallingTest() throws IOException {

        String jsonString = "{\"userId\":null,\"name\":\"user\",\"age\":40,\"gender\":\"MALE\"," +
                "\"mail\":\"user@mail.com\",\"demands\":[]}";

        //crete JSONString
        ObjectMapper mapper = new ObjectMapper();
        UserSO user = mapper.readValue(jsonString, UserSO.class);
        String resultString = mapper.writeValueAsString(user);
        //check
        assertThat(jsonString).isEqualTo(resultString);

    }

}