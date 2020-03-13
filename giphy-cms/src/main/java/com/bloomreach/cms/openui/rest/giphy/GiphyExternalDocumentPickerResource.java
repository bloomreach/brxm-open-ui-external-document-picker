package com.bloomreach.cms.openui.rest.giphy;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import com.bloomreach.cms.openui.model.Result;
import com.bloomreach.cms.openui.rest.ExternalDocumentPickerResource;
import com.bloomreach.cms.openui.rest.PickerItem;
import com.bloomreach.cms.openui.rest.giphy.model.GiphyItem;

import org.hippoecm.hst.site.HstServices;
import org.onehippo.cms7.crisp.api.broker.ResourceServiceBroker;
import org.onehippo.cms7.crisp.api.resource.Resource;
import org.onehippo.cms7.crisp.api.resource.ResourceBeanMapper;
import org.onehippo.cms7.crisp.api.resource.ResourceCollection;
import org.onehippo.cms7.crisp.hst.module.CrispHstServices;
import org.onehippo.cms7.essentials.components.rest.BaseRestResource;


@Path("/giphy")
public class GiphyExternalDocumentPickerResource implements ExternalDocumentPickerResource<PickerItem> {

    @SuppressWarnings("RestParamTypeInspection")
    @GET
    @Path("/search")
    @Produces({MediaType.APPLICATION_JSON})
    public Result<PickerItem> search(@Context UriInfo uriInfo,
                                     @QueryParam("query") String query,
                                     @QueryParam("page") @DefaultValue("1") int page,
                                     @QueryParam("pageSize") @DefaultValue("20") int pageSize,
                                     @QueryParam("documentLocale") String locale,
                                     @QueryParam("documentId") String documentId,
                                     @QueryParam("aggs") Map<String, String> aggregations) {
        final Map<String, Object> pathVars = new HashMap<>();

        int limit = pageSize;
        int offset = getOffset(page, pageSize);

        pathVars.put("q", query);
        pathVars.put("limit", limit);
        pathVars.put("offset", offset);

        ResourceServiceBroker broker = CrispHstServices.getDefaultResourceServiceBroker(HstServices.getComponentManager());
        Resource content = broker.resolve("giphy", "&q={q}&limit={limit}&offset={offset}", pathVars);
        Resource results = (Resource)content.getValue("data");

        ResourceCollection children = results.getChildren();
        ResourceBeanMapper unsplashed = broker.getResourceBeanMapper("giphy");

        List<PickerItem> items = unsplashed.mapCollection(children, GiphyItem.class)
                .stream().map(giphyItem -> new GiphyPickerItemAdapter(giphyItem))
                .collect(Collectors.toList());

        Result<PickerItem> result = new Result<>(items);

        return result;

    }

    public int getOffset(int pageNumber, int pageSize) {
        int start = (pageNumber - 1) * pageSize;
        return start;
    }

}
