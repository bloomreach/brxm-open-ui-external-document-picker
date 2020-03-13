package com.bloomreach.cms.openui.rest;

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

public abstract class BaseExternalDocumentPickerResource<T extends PickerItem>  implements ExternalDocumentPickerResource<T> {

    public abstract List<Agg> getAggregations();


    @SuppressWarnings("RestParamTypeInspection")
    @GET
    @Override
    @Path("/search")
    @Produces({MediaType.APPLICATION_JSON})
    public Result<T> search(@Context UriInfo uriInfo,
                                     @QueryParam("query") String query,
                                     @QueryParam("page") @DefaultValue("1") int page,
                                     @QueryParam("pageSize") @DefaultValue("16") int pageSize,
                                     @QueryParam("documentLocale") String locale,
                                     @QueryParam("documentId") String documentId,
                                     @QueryParam("aggs") Map<String, String> aggregations) {
//        Map<String, String> aggregations = extractAggregationMapFromUriInfo(uriInfo);
        Result<T> result = new Result<>(getData(query, aggregations, page, pageSize, locale, documentId));
        result.setAggs(getAggregations());
        return result;
    }

    public abstract List<T> getData(final String query,
                                             final Map<String, String> aggregations,
                                             final int page,
                                             final int pageSize,
                                             final String locale,
                                             final String documentId);
}
