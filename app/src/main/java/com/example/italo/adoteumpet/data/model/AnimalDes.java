package com.example.italo.adoteumpet.data.model;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by Alexandre on 05/11/2016.
 */
public class AnimalDes implements JsonDeserializer{
    @Override
    public Object deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonElement element = json.getAsJsonObject();
        if(json != null){
            element = json.getAsJsonObject();
        }
        return (new Gson().fromJson(element, Animal.class));
    }
}
