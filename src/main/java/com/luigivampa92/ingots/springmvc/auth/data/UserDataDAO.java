package com.luigivampa92.ingots.springmvc.auth.data;

import com.luigivampa92.ingots.springmvc.auth.data.model.UserEmailModel;

import java.util.List;

public interface UserDataDAO {

    List<UserEmailModel> getByUsername(String login);

    List<UserEmailModel> getByUsernameOrEmail(String login, String email);

    void createNewUser(String login, String password, String email, String emailConformationCode);

    void updateUserEnabled(String login, boolean enabled);
}
