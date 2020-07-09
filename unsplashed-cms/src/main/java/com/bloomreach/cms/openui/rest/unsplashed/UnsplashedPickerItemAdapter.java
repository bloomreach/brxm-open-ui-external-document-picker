package com.bloomreach.cms.openui.rest.unsplashed;

import com.bloomreach.cms.openui.rest.PickerItem;
import com.bloomreach.cms.openui.rest.unsplashed.model.UnsplashedItem;

public class UnsplashedPickerItemAdapter implements PickerItem<UnsplashedItem> {

    private String id;
    private String title;
    private String image;
    private String description;
    private UnsplashedItem data;

    public UnsplashedPickerItemAdapter(final UnsplashedItem unsplashedItem) {
        this.data = unsplashedItem;
        this.id = unsplashedItem.getID();
        this.title = unsplashedItem.getAltDescription();
        this.image = unsplashedItem.getUrls().getRegular();
        this.description = unsplashedItem.getDescription();
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
