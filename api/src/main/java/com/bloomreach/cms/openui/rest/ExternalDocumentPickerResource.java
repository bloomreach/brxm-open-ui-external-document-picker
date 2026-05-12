package com.bloomreach.cms.openui.rest;

import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.UriInfo;

import com.bloomreach.cms.openui.model.Result;

public interface ExternalDocumentPickerResource<T extends PickerItem> {

    @GET
    @Path("/search")
    @Produces({MediaType.APPLICATION_JSON})
    Result<T> search(@Context UriInfo uriInfo,
                     @QueryParam("query") String query,
                     @QueryParam("page") @DefaultValue("1") int page,
                     @QueryParam("pageSize") @DefaultValue("16") int pageSize,
                     @QueryParam("documentLocale") String locale,
                     @QueryParam("documentId") String documentId);
}
