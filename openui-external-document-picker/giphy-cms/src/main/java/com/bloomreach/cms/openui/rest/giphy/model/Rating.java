package com.bloomreach.cms.openui.rest.giphy.model;

import java.util.*;
import java.io.IOException;
import com.fasterxml.jackson.annotation.*;

public enum Rating {
    G, PG;

    @JsonValue
    public String toValue() {
        switch (this) {
        case G: return "g";
        case PG: return "pg";
        }
        return null;
    }

    @JsonCreator
    public static Rating forValue(String value) throws IOException {
        if (value.equals("g")) return G;
        if (value.equals("pg")) return PG;
        throw new IOException("Cannot deserialize Rating");
    }
}
