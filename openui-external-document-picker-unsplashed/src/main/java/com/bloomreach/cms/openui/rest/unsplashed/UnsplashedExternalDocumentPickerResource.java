package com.bloomreach.cms.openui.rest.unsplashed;


import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.bloomreach.cms.openui.rest.ExternalDocumentPickerResource;
import com.bloomreach.cms.openui.rest.PickerItem;
import com.bloomreach.cms.openui.rest.unsplashed.model.UnsplashedItem;

import org.hippoecm.hst.site.HstServices;
import org.onehippo.cms7.crisp.api.broker.ResourceServiceBroker;
import org.onehippo.cms7.crisp.api.resource.Resource;
import org.onehippo.cms7.crisp.api.resource.ResourceBeanMapper;
import org.onehippo.cms7.crisp.api.resource.ResourceCollection;
import org.onehippo.cms7.crisp.hst.module.CrispHstServices;
import org.onehippo.cms7.essentials.components.rest.BaseRestResource;


@Path("/unsplashed")
public class UnsplashedExternalDocumentPickerResource extends BaseRestResource implements ExternalDocumentPickerResource {

    @GET
    @Path("/search")
    @Produces({MediaType.APPLICATION_JSON})
    public List<PickerItem> search(@QueryParam("query") String query,
                                   @QueryParam("page") @DefaultValue("1") int page,
                                   @QueryParam("pageSize") @DefaultValue("20") int pageSize) {
        final Map<String, Object> pathVars = new HashMap<>();
        pathVars.put("query", query);
        pathVars.put("per_page", pageSize);
        pathVars.put("page", page);

        ResourceServiceBroker broker = CrispHstServices.getDefaultResourceServiceBroker(HstServices.getComponentManager());
        Resource content = broker.resolve("unsplashed", "?query={query}&per_page={per_page}&page={page}&client_id=56203cf6dbc6c347a71b1614c517ee922a662aea9672d267e5380d32221aa755", pathVars);
        Resource results = (Resource)content.getValue("results");

        ResourceCollection children = results.getChildren();
        ResourceBeanMapper unsplasehd = broker.getResourceBeanMapper("unsplashed");

        Collection<UnsplashedItem> items = unsplasehd.mapCollection(children, UnsplashedItem.class);

        return items.stream().map(unsplashedItem -> new UnsplashedPickerItemAdapter(unsplashedItem)).collect(Collectors.toList());
    }


}
