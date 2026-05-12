package com.bloomreach.cms.openui.util;

import java.util.Map;

import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.UriInfo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class JsonConversionUtil {
    private static final Logger log = LoggerFactory.getLogger(JsonConversionUtil.class);

    private static final ObjectMapper MAPPER = new ObjectMapper();

    //todo move to query param using com.bloomreach.cms.openui.rest.JacksonJsonParamConverterProvider
    public static  Map<String, String> extractAggregationMapFromUriInfo(final UriInfo info) {
        MultivaluedMap<String, String> queryParameters = info.getQueryParameters();
        return queryParameters.containsKey("aggs") ? convertJsonStringToMap(queryParameters.get("aggs").get(0)) : null;
    }

    @SuppressWarnings("unchecked")
    public static  Map<String, String> convertJsonStringToMap(final String json) {
        try {
            return MAPPER.readValue(json, Map.class);
        } catch (JsonProcessingException e) {
            log.error("Error converting to map", e);
        }
        return null;
    }

}
