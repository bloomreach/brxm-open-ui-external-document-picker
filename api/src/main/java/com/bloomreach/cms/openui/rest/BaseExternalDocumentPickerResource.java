package com.bloomreach.cms.openui.rest;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import com.bloomreach.cms.openui.model.Agg;
import com.bloomreach.cms.openui.model.Result;

import static com.bloomreach.cms.openui.util.JsonConversionUtil.extractAggregationMapFromUriInfo;

public abstract class BaseExternalDocumentPickerResource implements ExternalDocumentPickerResource {

    private static final List<Agg> EMPTY_AGGS = Collections.EMPTY_LIST;

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
