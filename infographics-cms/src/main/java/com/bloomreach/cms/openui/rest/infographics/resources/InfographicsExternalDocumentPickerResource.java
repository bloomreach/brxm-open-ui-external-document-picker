package com.bloomreach.cms.openui.rest.infographics.resources;

import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.xml.bind.DatatypeConverter;

import org.hippoecm.hst.site.HstServices;
import org.onehippo.cms7.crisp.api.broker.ResourceServiceBroker;
import org.onehippo.cms7.crisp.api.resource.Resource;
import org.onehippo.cms7.crisp.api.resource.ResourceBeanMapper;
import org.onehippo.cms7.crisp.api.resource.ResourceCollection;
import org.onehippo.cms7.crisp.api.resource.ResourceException;
import org.onehippo.cms7.crisp.hst.module.CrispHstServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bloomreach.cms.openui.model.Agg;
import com.bloomreach.cms.openui.rest.BaseExternalDocumentPickerResource;
import com.bloomreach.cms.openui.rest.PickerItem;
import com.bloomreach.cms.openui.rest.infographics.adapters.InfographicsPickerItemAdapter;
import com.bloomreach.cms.openui.rest.infographics.model.InfographicsItem;

@Path("/infographics")
public class InfographicsExternalDocumentPickerResource extends BaseExternalDocumentPickerResource {

    private static Logger log = LoggerFactory.getLogger(InfographicsExternalDocumentPickerResource.class);
    private static final List<Agg> EMPTY_AGGS = new ArrayList<>();
    private static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";

    private String urlBase;
    private String apiKey;
    private String apiSecret;

    public InfographicsExternalDocumentPickerResource(final String urlBase, final String apiKey, final String apiSecret) {
        this.urlBase = urlBase;
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
    }

    @Override
    public List<Agg> getAggregations() {
        return EMPTY_AGGS;
    }

    @Override
    public List<PickerItem> getData(final String query, final Map<String, String> aggregations, final int page, final int pageSize, final String locale, final String documentId) {

        ResourceServiceBroker broker = CrispHstServices.getDefaultResourceServiceBroker(HstServices.getComponentManager());

        final Map<String, Object> pathVars = new LinkedHashMap<>();
        pathVars.put("api_key", apiKey);
        final String apiSig = computeSignature(urlBase,"GET", apiSecret, pathVars);
        pathVars.put("api_sig",apiSig);
        Resource content = null;
        try {
            content = broker.resolve("infographics","infographics?api_key={api_key}&api_sig={api_sig}", pathVars);
            ResourceCollection children = content.getChildren();
            ResourceBeanMapper resourceBeanMapper = broker.getResourceBeanMapper("infographics");

            return resourceBeanMapper.mapCollection(children, InfographicsItem.class)
                    .stream().map(item -> new InfographicsPickerItemAdapter(item))
                    .skip((page-1) * pageSize)
                    .limit(pageSize)
                    .collect(Collectors.toList());

        } catch (ResourceException e) {
            throw new WebApplicationException(Response.status(501).entity(e.getCause().getMessage()).build());
        }
    }

    private String computeSignature(final String baseUrl, final String requestMethod, final String apiSecret,
                                    final Map<String,Object> parameters) {
        try {
            String sigBase = URLEncoder.encode(requestMethod, "UTF-8") + "&"
                    + URLEncoder.encode(baseUrl, "UTF-8");

            String paramString = encodedParameterStringFromList(parameters);

            sigBase += "&" + URLEncoder.encode(paramString, "UTF-8");

            byte[] sigBaseHashed = calculateRFC2104HMAC(sigBase, apiSecret);

            return DatatypeConverter.printBase64Binary(sigBaseHashed);

        } catch (java.io.UnsupportedEncodingException| InvalidKeyException e) {
            log.error("signature computing error",e);
            return null;
        }
    }

    private static String encodedParameterStringFromList(final Map<String,Object> parameters) {
        try {
            String s = "";
            for (Map.Entry<String,Object> p : parameters.entrySet()) {
                if (s.length() > 0)
                    s += "&";
                s += URLEncoder.encode(p.getKey(), "UTF-8") + "="
                        + URLEncoder.encode(p.getValue().toString(), "UTF-8").replace("+", "%20");
                // (need spaces to be %20 but are + by default)
            }
            return s;
        } catch (java.io.UnsupportedEncodingException e) {
            log.error("encoding error",e);
            return null;
        }
    }

    public static byte[] calculateRFC2104HMAC(final String data, final String key)
            throws InvalidKeyException {

        try {
            SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), HMAC_SHA1_ALGORITHM);
            Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
            mac.init(signingKey);
            return mac.doFinal(data.getBytes());
        } catch (NoSuchAlgorithmException e) {
            log.error("calculate digest error", e);
            return null;
        }
    }
}
