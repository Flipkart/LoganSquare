package com.bluelinelabs.logansquare.demo.parsetasks;


import com.bluelinelabs.logansquare.demo.model.BufferPool;
import com.bluelinelabs.logansquare.demo.model.JsonReaderOptimal;
import com.bluelinelabs.logansquare.demo.model.Response;
import com.google.gson.Gson;

import java.io.StringReader;

public class GsonParser extends Parser {

    private final Gson gson;
    private final BufferPool bufferPool;

    public GsonParser(ParseListener parseListener, String jsonString, Gson gson, BufferPool bufferPool) {
        super(parseListener, jsonString);
        this.gson = gson;
        this.bufferPool = bufferPool;
    }

    @Override
    protected int parse(String json) {
        try {
            JsonReaderOptimal jsonReaderOptimal = new JsonReaderOptimal(new StringReader(json), this.bufferPool);
            Response response = gson.fromJson(jsonReaderOptimal, Response.class);
            jsonReaderOptimal.close();
            //Response response = gson.fromJson(json, Response.class);
            return response.users.size();
        } catch (Exception e) {
            return 0;
        } finally {
            System.gc();
        }
    }

}
