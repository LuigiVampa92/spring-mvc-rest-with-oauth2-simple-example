package com.luigivampa92.ingots.springmvc.notes.rest.model;

import java.math.BigInteger;

public class NoteDataRequest {

    private BigInteger id;
    private String text;
    private String comment;
    private Boolean done;
    private Boolean important;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Boolean isDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public Boolean isImportant() {
        return important;
    }

    public void setImportant(Boolean important) {
        this.important = important;
    }
}
