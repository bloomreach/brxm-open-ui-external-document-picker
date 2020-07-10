package com.bloomreach.cms.openui.derived;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.jcr.RepositoryException;
import javax.jcr.Value;

import org.apache.commons.lang3.StringUtils;
import org.hippoecm.repository.ext.DerivedDataFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bloomreach.cms.openui.rest.infographics.adapters.InfographicsPickerItemAdapter;
import com.fasterxml.jackson.databind.ObjectMapper;

public class InfographicsDerivedFunction extends DerivedDataFunction {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static Logger log = LoggerFactory.getLogger(InfographicsDerivedFunction.class);

    @Override
    public Map<String, Value[]> compute(final Map<String, Value[]> map) {
        try {
            if (map.containsKey("openuid") && map.get("openuid") != null) {
                String openuidString = map.get("openuid")[0].getString();
                if (StringUtils.isNotEmpty(openuidString)) {
                    List<InfographicsPickerItemAdapter> items = MAPPER.readValue(openuidString, MAPPER.getTypeFactory().constructCollectionType(List.class, InfographicsPickerItemAdapter.class));
                    Value[] values = items.stream()
                            .filter(pickerItemAdapter -> pickerItemAdapter.getData().isPublished() && pickerItemAdapter.getData().getPublicUrl() != null)
                            .map(pickerItemAdapter -> StringUtils.substringAfterLast(pickerItemAdapter.getData().getPublicUrl(), "/"))
                            .map(publicId -> getValueFactory().createValue(publicId))
                            .toArray(Value[]::new);
                    map.put("publicId", values);
                } else {
                    map.put("publicId", new Value[]{});
                }
            }
        } catch (RepositoryException | IOException e) {
            log.error("error while trying to derive path", e);
        }
        return map;
    }
}
