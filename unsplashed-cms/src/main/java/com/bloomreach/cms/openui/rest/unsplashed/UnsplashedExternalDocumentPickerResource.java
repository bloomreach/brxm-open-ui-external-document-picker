package com.bloomreach.cms.openui.rest.unsplashed;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.bloomreach.cms.openui.model.Agg;
import com.bloomreach.cms.openui.model.Result;
import com.bloomreach.cms.openui.rest.ExternalDocumentPickerResource;
import com.bloomreach.cms.openui.rest.PickerItem;
import com.bloomreach.cms.openui.rest.unsplashed.model.UnsplashedItem;

import org.apache.commons.lang.StringUtils;
import org.hippoecm.hst.site.HstServices;
import org.onehippo.cms7.crisp.api.broker.ResourceServiceBroker;
import org.onehippo.cms7.crisp.api.resource.Resource;
import org.onehippo.cms7.crisp.api.resource.ResourceBeanMapper;
import org.onehippo.cms7.crisp.api.resource.ResourceCollection;
import org.onehippo.cms7.crisp.hst.module.CrispHstServices;
import org.onehippo.cms7.essentials.components.rest.BaseRestResource;

import static com.bloomreach.cms.openui.util.JsonConversionUtil.extractAggregationMapFromUriInfo;


@Path("/unsplashed")
public class UnsplashedExternalDocumentPickerResource extends BaseRestResource implements ExternalDocumentPickerResource {

    @GET
    @Path("/search")
    @Produces({MediaType.APPLICATION_JSON})
    public Result<PickerItem> search(@Context UriInfo uriInfo,
                                     @QueryParam("query") String query,
                                     @QueryParam("page") @DefaultValue("1") int page,
                                     @QueryParam("pageSize") @DefaultValue("20") int pageSize,
                                     @QueryParam("documentLocale") String locale,
                                     @QueryParam("documentId") String documentId) {


        Map<String, String> aggregations = extractAggregationMapFromUriInfo(uriInfo);
        final Map<String, Object> pathVars = new HashMap<>();
        pathVars.put("query", query);
        pathVars.put("per_page", pageSize);
        pathVars.put("page", page);
        StringBuffer requestQuery = new StringBuffer();
        if (aggregations.containsKey("color") && StringUtils.isNotEmpty(aggregations.get("color"))) {
            pathVars.put("color", aggregations.get("color"));
            requestQuery.append("&color={color}");
        }
        if (aggregations.containsKey("orientation") && StringUtils.isNotEmpty(aggregations.get("orientation"))) {
            pathVars.put("orientation", aggregations.get("orientation"));
            requestQuery.append("&orientation={orientation}");
        }
        if (aggregations.containsKey("order_by") && StringUtils.isNotEmpty(aggregations.get("order_by"))) {
            pathVars.put("order_by", aggregations.get("order_by"));
            requestQuery.append("&order_by={order_by}");
        }


        ResourceServiceBroker broker = CrispHstServices.getDefaultResourceServiceBroker(HstServices.getComponentManager());
        Resource content = null;
        try {
            content = broker.resolve("unsplashed", "&query={query}&per_page={per_page}&page={page}" + requestQuery.toString(), pathVars);
        } catch (Exception e) {
            e.printStackTrace();
            throw new WebApplicationException(Response.status(501).entity("Too many requests...").build());
        }

        Resource results = (Resource)content.getValue("results");

        ResourceCollection children = results.getChildren();
        ResourceBeanMapper unsplash = broker.getResourceBeanMapper("unsplashed");

        Result<PickerItem> result = new Result<>(unsplash.mapCollection(children, UnsplashedItem.class)
                .stream().map(unsplashedItem -> new UnsplashedPickerItemAdapter(unsplashedItem))
                .collect(Collectors.toList()));

        List<Agg> aggs = new ArrayList<>();

        Agg color = new Agg();
        color.setLabel("Color");
        color.setName("color");
        color.setType("SingleSelect");
        color.setValues(Arrays.asList("black_and_white", "black", "white", "yellow", "orange", "red", "purple", "magenta", "green", "teal", "blue"));


        Agg orientation = new Agg();
        orientation.setLabel("Orientation");
        orientation.setName("orientation");
        orientation.setType("SingleSelect");
        orientation.setValues(Arrays.asList("landscape", "portrait", "squarish"));

        Agg orderBy = new Agg();
        orderBy.setLabel("Order By");
        orderBy.setName("order_by");
        orderBy.setType("SingleSelect");
        orderBy.setValues(Arrays.asList("latest", "relevant"));

        aggs.add(color);
        aggs.add(orientation);
        aggs.add(orderBy);

        result.setAggs(aggs);

        return result;

    }


}
