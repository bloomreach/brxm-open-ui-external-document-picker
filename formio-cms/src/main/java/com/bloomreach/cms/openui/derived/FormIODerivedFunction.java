package com.bloomreach.cms.openui.derived;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.jcr.RepositoryException;
import javax.jcr.Value;

import org.apache.commons.lang.StringUtils;
import org.hippoecm.repository.ext.DerivedDataFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bloomreach.cms.openui.rest.formio.adapters.FormIOPickerItemAdapter;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FormIODerivedFunction extends DerivedDataFunction {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static Logger log = LoggerFactory.getLogger(FormIODerivedFunction.class);

    @Override
    public Map<String, Value[]> compute(final Map<String, Value[]> map) {
        try {
            if (map.containsKey("openuid") && map.get("openuid") != null) {
                String openuidString = map.get("openuid")[0].getString();
                if (StringUtils.isNotEmpty(openuidString)) {
                    List<FormIOPickerItemAdapter> items = MAPPER.readValue(openuidString, MAPPER.getTypeFactory().constructCollectionType(List.class, FormIOPickerItemAdapter.class));
                    Value[] values = items.stream()
                            .map(pickerItemAdapter -> getValueFactory().createValue(pickerItemAdapter.getData().getPath()))
                            .toArray(Value[]::new);
                    map.put("path", values);
                } else {
                    map.put("path", new Value[]{});
                }
            }
        } catch (RepositoryException | IOException e) {
            log.error("error while trying to derive path for formio:", e);
        }
        return map;
    }
}
