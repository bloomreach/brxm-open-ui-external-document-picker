package com.bloomreach.cms.openui.rest;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Map;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.Providers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.cxf.jaxrs.client.UrlEncodingParamConverter;

//todo to convert query parameter to object
@Provider
public class JacksonJsonParamConverterProvider implements ParamConverterProvider {

    @Context
    private Providers providers;

    @Override
    public <T> ParamConverter<T> getConverter(final Class<T> rawType, final Type genericType, final Annotation[] annotations) {
        // Check whether we can convert the type with Jackson.
        final MessageBodyReader<T> mbr = providers.getMessageBodyReader(rawType, genericType, annotations,
                MediaType.APPLICATION_JSON_TYPE);
        if (mbr == null || !mbr.isReadable(rawType, genericType, annotations, MediaType.APPLICATION_JSON_TYPE)) {
            return null;
        }

        if (rawType.isAssignableFrom(Map.class)) {
// Obtain custom ObjectMapper for special handling.
            final ContextResolver<ObjectMapper> contextResolver = providers
                    .getContextResolver(ObjectMapper.class, MediaType.APPLICATION_JSON_TYPE);

            final ObjectMapper mapper = contextResolver != null ?
                    contextResolver.getContext(rawType) : new ObjectMapper();

            // Create ParamConverter.
            return new ParamConverter<T>() {

                @Override
                public T fromString(final String value) {
                    try {
                        return mapper.readValue(value, rawType);
                    } catch (IOException e) {
                        throw new ProcessingException(e);
                    }
                }

                @Override
                public String toString(final T value) {
                    try {
                        return mapper.writer().writeValueAsString(value);
                    } catch (JsonProcessingException e) {
                        throw new ProcessingException(e);
                    }
                }
            };
        } else {
            return (ParamConverter<T>) new UrlEncodingParamConverter();
        }
    }
}
