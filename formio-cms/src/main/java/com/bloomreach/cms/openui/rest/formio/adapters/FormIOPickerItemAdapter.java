package com.bloomreach.cms.openui.rest.formio.adapters;

import com.bloomreach.cms.openui.rest.PickerItem;
import com.bloomreach.cms.openui.rest.formio.model.FormIOItem;

public class FormIOPickerItemAdapter implements PickerItem<FormIOItem> {

    private String id;
    private String title;
    private FormIOItem data;
    private String description;
    private String image;

    public FormIOPickerItemAdapter() {
        //necessary for derived function mapping
    }
    public FormIOPickerItemAdapter(FormIOItem item) {
        this.id = item.getPath();
        this.title = item.getTitle();
        this.description = item.getTitle();
        this.data = item;
        this.image = "https://raw.githubusercontent.com/bloomreach/brxm-open-ui-external-document-picker/master/formio-cms/src/main/resources/form.png";
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
    public FormIOItem getData() {
        return this.data;
    }
}
