package com.aircompany.parsers;

import com.aircompany.db.entity.Entity;
import com.aircompany.db.entity.User;
import com.aircompany.servlets.AuthServlet;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class JsonParser {

    public static String toJsonEntity(Entity entity) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(entity);
    }

    public static User parseUser(String json) throws JsonProcessingException {
        if(json.isEmpty()){
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<User> reference = new TypeReference<User>() {};
        return mapper.readValue(json, reference);
    }

    public static String toJsonEntities(List<Entity> entities) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(entities);
    }

    public static class EntityParser<T>{
        public T parseJson(String json) throws Exception{
            if(json.isEmpty()){
                return null;
            }
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<T> reference = new TypeReference<T>() {};
            return mapper.readValue(json, reference);
        }
    }
}
