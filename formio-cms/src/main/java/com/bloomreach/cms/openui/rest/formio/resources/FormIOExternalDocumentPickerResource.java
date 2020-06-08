package com.bloomreach.cms.openui.rest.formio.resources;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.hippoecm.hst.site.HstServices;
import org.onehippo.cms7.crisp.api.broker.ResourceServiceBroker;
import org.onehippo.cms7.crisp.api.resource.Resource;
import org.onehippo.cms7.crisp.api.resource.ResourceBeanMapper;
import org.onehippo.cms7.crisp.api.resource.ResourceCollection;
import org.onehippo.cms7.crisp.api.resource.ResourceException;
import org.onehippo.cms7.crisp.hst.module.CrispHstServices;

import com.bloomreach.cms.openui.model.Agg;
import com.bloomreach.cms.openui.rest.BaseExternalDocumentPickerResource;
import com.bloomreach.cms.openui.rest.PickerItem;
import com.bloomreach.cms.openui.rest.formio.adapters.FormIOPickerItemAdapter;
import com.bloomreach.cms.openui.rest.formio.model.FormIOItem;


@Path("/formio")
public class FormIOExternalDocumentPickerResource extends BaseExternalDocumentPickerResource {

    private static final List<Agg> EMPTY_AGGS = new ArrayList<>();

    @Override
    public List<Agg> getAggregations() {
        return EMPTY_AGGS;
    }

    @Override
    public List<PickerItem> getData(final String query, final Map<String, String> aggregations, final int page, final int pageSize, final String locale, final String documentId) {
        final Map<String, Object> pathVars = new HashMap<>();


        ResourceServiceBroker broker = CrispHstServices.getDefaultResourceServiceBroker(HstServices.getComponentManager());
        Resource content = null;
        try {

            content = broker.resolve("formio", "form?select=type,title,name,path", pathVars);
        } catch (ResourceException e) {
            throw new WebApplicationException(Response.status(501).entity(e.getCause().getMessage() + ", Issue reaching the FormIO server!").build());
        }

        ResourceCollection children = content.getChildren();
        ResourceBeanMapper formIO = broker.getResourceBeanMapper("formio");

        return formIO.mapCollection(children, FormIOItem.class)
                .stream().map(item -> new FormIOPickerItemAdapter(item))
                .collect(Collectors.toList());
    }

}
