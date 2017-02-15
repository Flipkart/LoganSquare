package com.bluelinelabs.logansquare.demo.serializetasks;

import com.bluelinelabs.logansquare.demo.model.Response;
import com.google.gson.Gson;
import com.vimeo.stag.generated.Stag;

/**
 * Created by anirudh.r on 20/01/17.
 */

public class StagSerializer extends Serializer {

    private final Gson gson;
    private final Stag.Factory stagFactory;

    public StagSerializer(SerializeListener parseListener, Response response, Stag.Factory factory, Gson gson) {
        super(parseListener, response);
        this.gson = gson;
        this.stagFactory = factory;
    }

    @Override
    protected String serialize(Response response) {
        try {
            return stagFactory.getResponse$TypeAdapter(gson).toJson(response);
        } catch (Exception e) {
            return null;
        } finally {
            System.gc();
        }
    }
}