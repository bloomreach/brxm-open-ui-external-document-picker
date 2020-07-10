package com.bloomreach.cms.openui.rest.infographics.adapters;

import com.bloomreach.cms.openui.rest.PickerItem;
import com.bloomreach.cms.openui.rest.infographics.model.InfographicsItem;

public class InfographicsPickerItemAdapter implements PickerItem<InfographicsItem> {

    private String id;
    private String title;
    private InfographicsItem data;
    private String description;
    private String image;

    public InfographicsPickerItemAdapter() {
        //necessary for derived function mapping
    }

    public InfographicsPickerItemAdapter(InfographicsItem item) {
        this.id = item.getId();
        this.title = item.getTitle();
        this.description = item.isPublished() ? "Published" : "Unpublished";
        this.data = item;
        this.image = item.getThumbnailUrl();
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public String getImage() {
        return image;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public InfographicsItem getData() {
        return this.data;
    }
}
