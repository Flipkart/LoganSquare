package com.bluelinelabs.logansquare.demo.parsetasks;

import com.bluelinelabs.logansquare.demo.model.BufferPool;
import com.bluelinelabs.logansquare.demo.model.JsonReaderOptimal;
import com.bluelinelabs.logansquare.demo.model.Response;
import com.bluelinelabs.logansquare.demo.model.Response$TypeAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.vimeo.stag.generated.Stag;

import java.io.StringReader;


public class StagParser extends Parser {

    private final TypeAdapter<Response> typeAdapter;
    private final Gson gson;
    private final BufferPool bufferPool;


    public StagParser(ParseListener parseListener, String jsonString, Gson gson, Stag.Factory stagFactory, BufferPool bufferPool) {
        super(parseListener, jsonString);
        this.typeAdapter = stagFactory.getResponse$TypeAdapter(gson);
        this.gson = gson;
        this.bufferPool = bufferPool;
    }

    @Override
    protected int parse(String json) {
        try {
            JsonReaderOptimal jsonReaderOptimal = new JsonReaderOptimal(new StringReader(json), this.bufferPool);
            Response response = typeAdapter.read(jsonReaderOptimal);
            jsonReaderOptimal.close();
            return response.users.size();
        } catch (Exception e) {
            return 0;
        } finally {
            System.gc();
        }
    }
}