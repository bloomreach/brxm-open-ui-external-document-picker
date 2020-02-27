package com.bloomreach.cms.openui.rest;

import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

public interface ExternalDocumentPickerResource<T extends PickerItem> {

    @GET
    @Path("/search")
    @Produces({MediaType.APPLICATION_JSON})
    List<T> search(@QueryParam("query") String query,
                   @QueryParam("page") @DefaultValue("1") int page,
                   @QueryParam("pageSize") @DefaultValue("16") int pageSize,
                   @QueryParam("documentLocale") String locale,
                   @QueryParam("documentId") String documentId);
}
