package com.bloomreach.cms.openui.rest.giphy.derived;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.jcr.RepositoryException;
import javax.jcr.Value;

import com.bloomreach.cms.openui.rest.giphy.GiphyPickerItemAdapter;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.lang.StringUtils;
import org.hippoecm.repository.ext.DerivedDataFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GiphyImageFunction extends DerivedDataFunction {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static Logger log = LoggerFactory.getLogger(GiphyImageFunction.class);

    @Override
    public Map<String, Value[]> compute(final Map<String, Value[]> map) {
        try {
            if (map.containsKey("openuid") && map.get("openuid") != null) {
                String openuidString = map.get("openuid")[0].getString();
                if (StringUtils.isNotEmpty(openuidString)) {
                    List<GiphyPickerItemAdapter> items = MAPPER.readValue(openuidString, MAPPER.getTypeFactory().constructCollectionType(List.class, GiphyPickerItemAdapter.class));
                    Value[] values = items.stream()
                            .map(giphyPickerItemAdapter -> getValueFactory().createValue(giphyPickerItemAdapter.getData().getImages().getOriginal().getURL()))
                            .toArray(Value[]::new);
                    map.put("images", values);
                } else {
                    map.put("images", new Value[]{});
                }
            }

        } catch (RepositoryException | IOException e) {
            log.error("error while trying to derive images for unsplashed:", e);
        }
        return map;
    }
}
