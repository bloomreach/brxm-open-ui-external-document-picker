package com.bloomreach.cms.openui.rest;


public interface PickerItem<T> {

    public String getId();

    public String getTitle();

    public String getImage();

    public String getDescription();

    public T getData();

}
