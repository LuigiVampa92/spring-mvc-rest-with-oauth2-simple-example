package com.luigivampa92.ingots.springmvc.auth.data.impl;

import com.luigivampa92.ingots.springmvc.auth.data.UserDataDAO;
import com.luigivampa92.ingots.springmvc.auth.data.model.UserEmailModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class UserDataDAOImpl implements UserDataDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<UserEmailModel> getByUsername(String login) {
        return jdbcTemplate.query("SELECT * FROM users_email ue WHERE ue.username = ?",
                new String[] {login}, UserEmailModel.USER_EMAIL_MODEL_ROW_MAPPER);
    }

    @Override
    public List<UserEmailModel> getByUsernameOrEmail(String login, String email) {
        return jdbcTemplate.query("SELECT * FROM users_email ue WHERE ue.username = ? OR ue.email = ?",
                new String[] {login, email}, UserEmailModel.USER_EMAIL_MODEL_ROW_MAPPER);
    }

    @Override
    public void createNewUser(String login, String password, String email, String emailConformationCode) {
        List<UserEmailModel> list = jdbcTemplate.query("SELECT * FROM users_email ue WHERE ue.username = ? AND ue.email = ?",
                new String[] {login, email}, UserEmailModel.USER_EMAIL_MODEL_ROW_MAPPER);

        if (list.isEmpty()) {
            jdbcTemplate.update("INSERT INTO users (username, password, enabled) VALUES (?, ?, ?)",
                    new Object[]{login, password, false});
            jdbcTemplate.update("INSERT INTO authorities (username, authority) VALUES (?, ?)",
                    new String[] {login, "ROLE_USER"});
            jdbcTemplate.update("INSERT INTO users_email (username, email, email_conformation_code) VALUES(?, ?, ?)",
                    new String[]{login, email, emailConformationCode});
        }
        else {
            jdbcTemplate.update("UPDATE users_email SET email_conformation_code = ? WHERE username = ?",
                    new String[] {emailConformationCode, login});
        }
    }

    @Override
    public void updateUserEnabled(String login, boolean enabled) {
        jdbcTemplate.update("UPDATE users SET enabled = ? WHERE username = ?", new Object[] {enabled, login});
        jdbcTemplate.update("UPDATE users_email SET email_conformation_code = ? WHERE username = ?", new String[] {null, login});
    }
}
