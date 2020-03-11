package com.bloomreach.cms.openui.rest.unsplashed;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import com.bloomreach.cms.openui.model.Agg;
import com.bloomreach.cms.openui.model.Option;
import com.bloomreach.cms.openui.rest.BaseExternalDocumentPickerResource;
import com.bloomreach.cms.openui.rest.PickerItem;
import com.bloomreach.cms.openui.rest.unsplashed.model.UnsplashedItem;

import org.apache.commons.lang.StringUtils;
import org.hippoecm.hst.site.HstServices;
import org.onehippo.cms7.crisp.api.broker.ResourceServiceBroker;
import org.onehippo.cms7.crisp.api.resource.Resource;
import org.onehippo.cms7.crisp.api.resource.ResourceBeanMapper;
import org.onehippo.cms7.crisp.api.resource.ResourceCollection;
import org.onehippo.cms7.crisp.api.resource.ResourceException;
import org.onehippo.cms7.crisp.hst.module.CrispHstServices;


@Path("/unsplashed")
public class UnsplashedExternalDocumentPickerResource extends BaseExternalDocumentPickerResource {

    private static final List<Agg> UNSPLASHED_AGGS = new ArrayList<>();

    static {
        Agg color = new Agg();
        color.setLabel("Color");
        color.setName("color");
        color.setType("SingleSelect");
        color.setValues(Arrays.asList(new Option("black_and_white", "Black and White"),
                new Option("black", "Black"),
                new Option("white", "White"),
                new Option("yellow", "Yellow"),
                new Option("orange", "Orange"),
                new Option("red", "Red"),
                new Option("purple", "Purple"),
                new Option("magenta", "Magenta"),
                new Option("green", "Green"),
                new Option("teal", "Teal"),
                new Option("blue", "Blue")));


        Agg orientation = new Agg();
        orientation.setLabel("Orientation");
        orientation.setName("orientation");
        orientation.setType("SingleSelect");
        orientation.setValues(Arrays.asList(
                new Option("landscape", "Landscape"),
                new Option("portrait", "Portrait"),
                new Option("squarish", "Squarish")));

        Agg orderBy = new Agg();
        orderBy.setLabel("Order By");
        orderBy.setName("order_by");
        orderBy.setType("SingleSelect");
        orderBy.setValues(Arrays.asList(
                new Option("latest", "Latest"),
                new Option("relevant", "Relevant")));

        UNSPLASHED_AGGS.add(color);
        UNSPLASHED_AGGS.add(orientation);
        UNSPLASHED_AGGS.add(orderBy);

    }


    @Override
    public List<Agg> getAggregations() {
        return UNSPLASHED_AGGS;
    }

    @Override
    public List<PickerItem> getData(final String query, final Map<String, String> aggregations, final int page, final int pageSize, final String locale, final String documentId) {
        final Map<String, Object> pathVars = new HashMap<>();
        pathVars.put("query", query);
        pathVars.put("per_page", pageSize);
        pathVars.put("page", page);
        StringBuffer requestQuery = new StringBuffer();
        addToQueryParam("color", aggregations, pathVars, requestQuery);
        addToQueryParam("orientation", aggregations, pathVars, requestQuery);
        addToQueryParam("order_by", aggregations, pathVars, requestQuery);

        ResourceServiceBroker broker = CrispHstServices.getDefaultResourceServiceBroker(HstServices.getComponentManager());
        Resource content = null;
        try {
            content = broker.resolve("unsplashed", "&query={query}&per_page={per_page}&page={page}" + requestQuery.toString(), pathVars);
        } catch (ResourceException e) {
            throw new WebApplicationException(Response.status(501).entity(e.getCause().getMessage() + " limit reached!").build());
        }

        Resource results = (Resource)content.getValue("results");

        ResourceCollection children = results.getChildren();
        ResourceBeanMapper unsplash = broker.getResourceBeanMapper("unsplashed");

        return unsplash.mapCollection(children, UnsplashedItem.class)
                .stream().map(unsplashedItem -> new UnsplashedPickerItemAdapter(unsplashedItem))
                .collect(Collectors.toList());
    }

    public void addToQueryParam(final String key, final Map<String, String> aggregations, final Map<String, Object> pathVars, final StringBuffer requestQuery) {
        if (aggregations.containsKey(key) && StringUtils.isNotEmpty(aggregations.get(key))) {
            pathVars.put(key, aggregations.get(key));
            requestQuery.append(String.format("&%s={%s}", key, key));
        }
    }
}
