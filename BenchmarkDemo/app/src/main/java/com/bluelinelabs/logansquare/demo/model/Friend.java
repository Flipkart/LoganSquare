package com.bluelinelabs.logansquare.demo.model;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;
import com.vimeo.stag.UseStag;

@UseStag
@JsonObject
public class Friend {

    @JsonField
    public int id;

    @JsonField
    public String name;
}
