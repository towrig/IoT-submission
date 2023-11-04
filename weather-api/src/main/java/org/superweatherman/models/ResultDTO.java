package org.superweatherman.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ResultDTO {

    public Object data;

    //Had to resort to this, as xml mapping dependecy was not allowing JSON as the return type, no matter what I did.
    @Override
    public String toString() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return "";
        }
    }
}
