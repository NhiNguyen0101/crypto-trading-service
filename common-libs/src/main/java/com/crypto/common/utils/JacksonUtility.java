package com.crypto.common.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

@UtilityClass
public class JacksonUtility {
    private static final ObjectMapper JSON_MAPPER = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .setSerializationInclusion(JsonInclude.Include.NON_NULL)
            .registerModule(new JavaTimeModule())
            .registerModule(new SimpleModule(
                    "LocalDateISOSerializer",
                    Version.unknownVersion(),
                    Collections.singletonList(new LocalDateSerializer())));

    /**
     * Standard Jackson ObjectMapper with the required properties and modules
     * <p>
     * - Includes JavaTime module
     * - Does not fail on deserializing unknown properties
     * - Only serializes non-null properties (ignores null properties during serialization)
     *
     * @return pre-initialized ObjectMapper
     */
    public static ObjectMapper getStandardJsonMapper() {
        return JSON_MAPPER;
    }

    private static class LocalDateSerializer extends StdSerializer<LocalDate> {
        public LocalDateSerializer() {
            super(LocalDate.class);
        }

        @Override
        public void serialize(LocalDate value, JsonGenerator gen, SerializerProvider provider) throws IOException {
            gen.writeString(value.format(DateTimeFormatter.ISO_LOCAL_DATE));
        }
    }
}
