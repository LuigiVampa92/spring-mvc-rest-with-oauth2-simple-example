package com.luigivampa92.ingots.springmvc.notes.rest.model;

public class NoteStatusResponse {

    private String status;

    public NoteStatusResponse() {
        this("OK");
    }

    public NoteStatusResponse(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
