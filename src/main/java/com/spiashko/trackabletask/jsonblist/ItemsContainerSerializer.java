package com.spiashko.trackabletask.jsonblist;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

@SuppressWarnings("rawtypes")
public class ItemsContainerSerializer extends JsonSerializer<ItemsContainer> {

    public ItemsContainerSerializer() {
        super();
    }

    @Override
    public void serialize(ItemsContainer value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeObject(value.getItems());
    }

}
