package com.luigivampa92.ingots.springmvc.notes.rest.model;

import com.luigivampa92.ingots.springmvc.notes.data.model.NoteModel;

import java.util.List;

public class NotesListResponse {

    private List<NoteModel> response;

    public NotesListResponse() {}

    public NotesListResponse(List<NoteModel> response) {
        this.response = response;
    }

    public List<NoteModel> getResponse() {
        return response;
    }

    public void setResponse(List<NoteModel> response) {
        this.response = response;
    }
}
