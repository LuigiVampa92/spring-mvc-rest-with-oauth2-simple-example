package com.luigivampa92.ingots.springmvc.notes.rest.model;

import java.math.BigInteger;

public class NoteIdRequest {

    private BigInteger id;

    public NoteIdRequest() {}

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }
}
