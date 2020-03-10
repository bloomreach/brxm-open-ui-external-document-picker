package com.bloomreach.cms.openui.util;

import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonConversionUtil {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static final Map<String, String> extractAggregationMapFromUriInfo(final UriInfo info) {
        MultivaluedMap<String, String> queryParameters = info.getQueryParameters();
        return queryParameters.containsKey("aggs") ? convertJsonStringToMap(queryParameters.get("aggs").get(0)) : null;
    }

    public static final Map<String, String> convertJsonStringToMap(final String json) {
        try {
            return MAPPER.readValue(json, Map.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
