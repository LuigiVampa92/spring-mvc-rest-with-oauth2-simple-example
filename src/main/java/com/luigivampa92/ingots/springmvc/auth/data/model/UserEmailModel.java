package com.luigivampa92.ingots.springmvc.auth.data.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserEmailModel {

    private String username;
    private String email;
    private String emailConformationCode;

    public UserEmailModel(String username, String email, String emailConformationCode) {
        this.username = username;
        this.email = email;
        this.emailConformationCode = emailConformationCode;
    }

    public static final RowMapper<UserEmailModel> USER_EMAIL_MODEL_ROW_MAPPER = new RowMapper<UserEmailModel>() {
        @Override
        public UserEmailModel mapRow(ResultSet resultSet, int i) throws SQLException {
            String username = resultSet.getString(1);
            String email = resultSet.getString(2);
            String emailConformationCode = resultSet.getString(3);
            return new UserEmailModel(username, email, emailConformationCode);
        }
    };

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getEmailConformationCode() {
        return emailConformationCode;
    }
}
