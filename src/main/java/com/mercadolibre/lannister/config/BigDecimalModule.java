package com.mercadolibre.lannister.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.IOException;
import java.math.BigDecimal;

public class BigDecimalModule extends SimpleModule {
    public BigDecimalModule() {
        super("BigDecimalModule");
        this.addDeserializer(BigDecimal.class, new BigDecimalDeserializer());
    }

    class BigDecimalDeserializer extends JsonDeserializer<BigDecimal> {
        BigDecimalDeserializer() {
        }

        public BigDecimal deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            return new BigDecimal(p.getEmbeddedObject().toString());
        }
    }
}

