package com.bloomreach.cms.openui.rest.giphy;

import com.bloomreach.cms.openui.rest.PickerItem;
import com.bloomreach.cms.openui.rest.giphy.model.GiphyItem;

public class GiphyPickerItemAdapter implements PickerItem<GiphyItem> {

    private String id;
    private String title;
    private String image;
    private String description;
    private GiphyItem data;

    public GiphyPickerItemAdapter(final GiphyItem giphyItem) {
        this.data = giphyItem;
        this.id = giphyItem.getID();
        this.title = giphyItem.getTitle();
        this.image = giphyItem.getImages().getOriginal().getURL();
        this.description = giphyItem.getUser()!=null?giphyItem.getUser().getUsername():"";
    }

    public GiphyPickerItemAdapter() {
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
    public GiphyItem getData() {
        return data;
    }
}
