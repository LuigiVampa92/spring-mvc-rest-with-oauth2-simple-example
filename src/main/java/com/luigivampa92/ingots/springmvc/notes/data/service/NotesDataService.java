package com.luigivampa92.ingots.springmvc.notes.data.service;

import com.luigivampa92.ingots.springmvc.error.BusinessException;
import com.luigivampa92.ingots.springmvc.error.ErrorType;
import com.luigivampa92.ingots.springmvc.notes.data.model.NoteModel;
import com.luigivampa92.ingots.springmvc.notes.rest.model.NoteDataRequest;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class NotesDataService {

    @Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    public List<NoteModel> getNotesByUsername(String username) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(NoteModel.class);
        criteria.add(Restrictions.eq("username", username));
        return criteria.list();
    }

    public void createNote(NoteDataRequest request, String username) {
        Session session = sessionFactory.getCurrentSession();
        NoteModel model = new NoteModel();

        model.setText(request.getText());
        model.setComment(request.getComment());
        model.setImportant(request.isImportant() != null && request.isImportant() == true);
        model.setCreationDate(new Date());
        model.setDone(false);
        model.setUsername(username);
        session.save(model);
    }

    public void updateNote(NoteDataRequest request, String username) throws BusinessException {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(NoteModel.class);
        criteria.add(Restrictions.eq("id", request.getId()));
        criteria.add(Restrictions.eq("username", username));
        NoteModel model = (NoteModel) criteria.uniqueResult();

        if (model != null) {
            if (request.getText() != null && !request.getText().equals(""))
                model.setText(request.getText());
            if (request.getComment() != null && !request.getComment().equals(""))
                model.setComment(request.getComment());
            if (request.isDone() != null && request.isDone() != model.isDone())
                model.setDone(request.isDone());
            if (request.isImportant() != null && request.isImportant() != model.isImportant())
                model.setImportant(request.isImportant());

            session.update(model);
        }
        else {
            throw new BusinessException(ErrorType.RECORD_NOT_FOUND);
        }
    }

    public void deleteNote(BigInteger id, String username) throws BusinessException {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(NoteModel.class);
        criteria.add(Restrictions.eq("id", id));
        criteria.add(Restrictions.eq("username", username));
        NoteModel model = (NoteModel) criteria.uniqueResult();

        if (model != null) {
            session.delete(model);
        }
        else {
            throw new BusinessException(ErrorType.RECORD_NOT_FOUND);
        }
    }
}
