package com.luigivampa92.ingots.springmvc.notes.data.model;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

@Entity
@Table(name = "NOTES")
@SequenceGenerator(name = "NOTES_SEQ", sequenceName = "NOTES_SEQ")
public class NoteModel {

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "NOTES_SEQ")
    private BigInteger id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "CREATION_DATE")
    private Date creationDate;

    @Column(name = "TEXT")
    private String text;

    @Column(name = "COMMENT")
    private String comment;

    @Column(name = "DONE")
    private boolean done;

    @Column(name = "IMPORTANT")
    private boolean important;

    public BigInteger getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
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

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public boolean isImportant() {
        return important;
    }

    public void setImportant(boolean important) {
        this.important = important;
    }
}
