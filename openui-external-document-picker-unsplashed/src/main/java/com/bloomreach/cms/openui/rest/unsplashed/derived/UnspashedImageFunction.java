package com.bloomreach.cms.openui.rest.unsplashed.derived;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.jcr.RepositoryException;
import javax.jcr.Value;

import com.bloomreach.cms.openui.rest.unsplashed.UnsplashedPickerItemAdapter;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.hippoecm.repository.ext.DerivedDataFunction;

public class UnspashedImageFunction extends DerivedDataFunction {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Override
    public Map<String, Value[]> compute(final Map<String, Value[]> map) {
        try {
            String openuidString = map.get("openuid")[0].getString();
            List<UnsplashedPickerItemAdapter> items = MAPPER.readValue(openuidString, MAPPER.getTypeFactory().constructCollectionType(List.class, UnsplashedPickerItemAdapter.class));
            Value[] values = items.stream()
                    .map(unsplashedPickerItemAdapter -> getValueFactory().createValue(unsplashedPickerItemAdapter.getData().getUrls().getFull()))
                    .toArray(Value[]::new);
            map.put("images", values);
        } catch (RepositoryException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }
}
