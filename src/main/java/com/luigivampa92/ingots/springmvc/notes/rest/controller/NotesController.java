package com.luigivampa92.ingots.springmvc.notes.rest.controller;

import com.luigivampa92.ingots.springmvc.error.BusinessException;
import com.luigivampa92.ingots.springmvc.notes.data.model.NoteModel;
import com.luigivampa92.ingots.springmvc.notes.data.service.NotesDataService;
import com.luigivampa92.ingots.springmvc.notes.rest.model.NoteDataRequest;
import com.luigivampa92.ingots.springmvc.notes.rest.model.NoteIdRequest;
import com.luigivampa92.ingots.springmvc.notes.rest.model.NoteStatusResponse;
import com.luigivampa92.ingots.springmvc.notes.rest.model.NotesListResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "api/notes", produces = MediaType.APPLICATION_JSON_VALUE)
public class NotesController {

    private static final Logger LOG = LoggerFactory.getLogger(NotesController.class);

    @Autowired
    private NotesDataService notesDataService;

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    @ResponseBody
    public NotesListResponse getAllNotes() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User principal = (User) authentication.getPrincipal();

        List<NoteModel> notes = notesDataService.getNotesByUsername(principal.getUsername());
        LOG.warn("User \"" + principal.getUsername() +"\" requested list of notes. Retruned " +
                String.valueOf(notes.size()) + " values");
        return new NotesListResponse(notes);
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.POST)
    @ResponseBody
    public NoteStatusResponse createNote(@RequestBody NoteDataRequest request) throws BusinessException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User principal = (User) authentication.getPrincipal();

        notesDataService.createNote(request, principal.getUsername());
        LOG.warn("User \"" + principal.getUsername() +"\" created new note with text: " + request.getText());
        return new NoteStatusResponse();
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.PUT)
    @ResponseBody
    public NoteStatusResponse updateNote(@RequestBody NoteDataRequest request) throws BusinessException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User principal = (User) authentication.getPrincipal();

        notesDataService.updateNote(request, principal.getUsername());
        LOG.warn("User \"" + principal.getUsername() +"\" updated note with id: " + request.getId().toString());
        return new NoteStatusResponse();
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.DELETE)
    @ResponseBody
    public NoteStatusResponse deleteNote(@RequestBody NoteIdRequest request) throws BusinessException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User principal = (User) authentication.getPrincipal();

        notesDataService.deleteNote(request.getId(), principal.getUsername());
        LOG.warn("User \"" + principal.getUsername() +"\" deleted note with id: " + request.getId().toString());
        return new NoteStatusResponse();
    }
}
