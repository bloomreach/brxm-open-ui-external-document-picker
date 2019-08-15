package com.bloomreach.cms.openui.rest.brsm;

import com.bloomreach.cms.openui.rest.PickerItem;
import com.bloomreach.cms.openui.rest.brsm.model.PickerProductItem;

public class ProductPickerItemAdapter implements PickerItem<PickerProductItem> {

    private String id;
    private String title;
    private String image;
    private String description;
    private PickerProductItem data;

    public ProductPickerItemAdapter(final PickerProductItem pickerProductItem) {
        this.data = pickerProductItem;
        this.id = pickerProductItem.getPID();
        this.title = pickerProductItem.getTitle();
        this.image = pickerProductItem.getThumbImage();
        this.description = pickerProductItem.getDescription();
    }

    public ProductPickerItemAdapter() {
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
    public PickerProductItem getData() {
        return data;
    }
}