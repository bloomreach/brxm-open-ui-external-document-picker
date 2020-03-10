package com.bloomreach.cms.openui.rest;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

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
