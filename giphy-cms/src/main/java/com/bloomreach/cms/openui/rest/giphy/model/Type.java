package com.bloomreach.cms.openui.rest.giphy.model;

import java.util.*;
import java.io.IOException;
import com.fasterxml.jackson.annotation.*;

public enum Type {
    GIF;

    @JsonValue
    public String toValue() {
        switch (this) {
        case GIF: return "gif";
        }
        return null;
    }

    @JsonCreator
    public static Type forValue(String value) throws IOException {
        if (value.equals("gif")) return GIF;
        throw new IOException("Cannot deserialize Type");
    }
}
