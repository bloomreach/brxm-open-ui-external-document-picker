package com.bloomreach.cms.openui.rest;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.UriInfo;

import com.bloomreach.cms.openui.model.Agg;
import com.bloomreach.cms.openui.model.Result;

import static com.bloomreach.cms.openui.util.JsonConversionUtil.extractAggregationMapFromUriInfo;

public abstract class BaseExternalDocumentPickerResource implements ExternalDocumentPickerResource {

    

    public abstract List<Agg> getAggregations();

    @GET
    @Path("/search")
    @Produces({MediaType.APPLICATION_JSON})
    public Result<PickerItem> search(@Context UriInfo uriInfo,
                                     @QueryParam("query") String query,
                                     @QueryParam("page") @DefaultValue("1") int page,
                                     @QueryParam("pageSize") @DefaultValue("16") int pageSize,
                                     @QueryParam("documentLocale") String locale,
                                     @QueryParam("documentId") String documentId) {
        Map<String, String> aggregations = extractAggregationMapFromUriInfo(uriInfo);
        Result<PickerItem> result = new Result<>(getData(query, aggregations, page, pageSize, locale, documentId));
        result.setAggs(getAggregations());
        return result;
    }

    public abstract List<PickerItem> getData(final String query,
                                             final Map<String, String> aggregations,
                                             final int page,
                                             final int pageSize,
                                             final String locale,
                                             final String documentId);
}
