package com.bloomreach.cms.openui.rest.unsplashed;

import com.bloomreach.cms.openui.rest.PickerItem;
import com.bloomreach.cms.openui.rest.unsplashed.model.UnsplashedItem;

import org.apache.commons.lang.StringUtils;

public class UnsplashedPickerItemAdapter implements PickerItem<UnsplashedItem> {

    private String id;
    private String title;
    private String image;
    private String description;
    private UnsplashedItem data;

    public UnsplashedPickerItemAdapter(final UnsplashedItem unsplashedItem) {
        this.data = unsplashedItem;
        this.id = unsplashedItem.getID();
        this.title = StringUtils.isEmpty(unsplashedItem.getDescription()) ? unsplashedItem.getDescription() : unsplashedItem.getAltDescription();
        this.image = unsplashedItem.getUrls().getRegular();
        this.description = unsplashedItem.getAltDescription();
    }

    public UnsplashedPickerItemAdapter() {
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getImage() {
        return image;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public UnsplashedItem getData() {
        return data;
    }
}
