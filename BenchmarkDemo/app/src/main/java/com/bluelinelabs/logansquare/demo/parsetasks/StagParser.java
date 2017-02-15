package com.bluelinelabs.logansquare.demo.parsetasks;

import com.bluelinelabs.logansquare.demo.model.Response;
import com.google.gson.Gson;
import com.vimeo.stag.generated.Stag;

public class StagParser extends Parser {

    private final Stag.Factory factory;
    private final Gson gson;

    public StagParser(ParseListener parseListener, String jsonString, Stag.Factory factory, Gson gson) {
        super(parseListener, jsonString);
        this.factory = factory;
        this.gson = gson;
    }

    @Override
    protected int parse(String json) {
        try {
            Response response = factory.getResponse$TypeAdapter(gson).fromJson(json);
            return response.users.size();
        } catch (Exception e) {
            return 0;
        } finally {
            System.gc();
        }
    }
}