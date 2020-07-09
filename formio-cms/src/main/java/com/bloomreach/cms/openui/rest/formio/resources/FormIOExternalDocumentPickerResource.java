package com.bloomreach.cms.openui.rest.formio.resources;

import java.util.ArrayList;
import java.util.Collections;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.HttpClientErrorException;

import com.bloomreach.cms.openui.model.Agg;
import com.bloomreach.cms.openui.rest.BaseExternalDocumentPickerResource;
import com.bloomreach.cms.openui.rest.PickerItem;
import com.bloomreach.cms.openui.rest.formio.adapters.FormIOPickerItemAdapter;
import com.bloomreach.cms.openui.rest.formio.model.FormIOItem;

@Path("/formio")
public class FormIOExternalDocumentPickerResource extends BaseExternalDocumentPickerResource {

    private static Logger log = LoggerFactory.getLogger(FormIOExternalDocumentPickerResource.class);
    private static final List<Agg> EMPTY_AGGS = new ArrayList<>();

    @Override
    public List<Agg> getAggregations() {
        return EMPTY_AGGS;
    }

    @Override
    public List<PickerItem> getData(final String query, final Map<String, String> aggregations, final int page, final int pageSize, final String locale, final String documentId) {
        final Map<String, Object> pathVars = new HashMap<>();
        pathVars.put("query", query);
        pathVars.put("limit", pageSize);
        pathVars.put("skip", (page-1)*pageSize);

        ResourceServiceBroker broker = CrispHstServices.getDefaultResourceServiceBroker(HstServices.getComponentManager());
        Resource content = null;
        try {
//
//            to make use of authenticated requests uncomment the following section
//
//            ExchangeHint exchangeHint = ExchangeHintBuilder.create()
//                    .methodName("POST")
//                    .requestHeader("Content-Type", "application/json;charset=UTF-8")
//                    .requestBody("{\"data\": {" +
//                            "        \"email\": \"username@form.io\"," +
//                            "        \"password\": \"password\"" +
//                            "    }}")
//                    .build();
//            Resource response = broker.findResources("formio", "user/login", exchangeHint);
//            List<String> jwtTokenList = exchangeHint.getResponseHeaders().get("x-jwt-token");
//
            content = broker.resolve("formio", "form?type=form&name__regex=/^{query}/i&limit={limit}&skip={skip}&select=title,name,path", pathVars /*,exchangeHint*/);
            ResourceCollection children = content.getChildren();
            ResourceBeanMapper formIO = broker.getResourceBeanMapper("formio");

            return formIO.mapCollection(children, FormIOItem.class)
                    .stream().map(item -> new FormIOPickerItemAdapter(item))
                    .collect(Collectors.toList());

        } catch (ResourceException e) {
            if (e.getCause() instanceof HttpClientErrorException && 416 == ((HttpClientErrorException)e.getCause()).getRawStatusCode()) {
                if (log.isDebugEnabled()) {
                    log.warn("pagination error on the backend", e);
                }
            } else {
                throw new WebApplicationException(Response.status(501).entity(e.getCause().getMessage() + ", Issue reaching the FormIO server!").build());
            }
        }
        return Collections.emptyList();
    }

}
