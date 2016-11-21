package com.luigivampa92.ingots.springmvc.auth.service;

import com.luigivampa92.ingots.springmvc.auth.data.UserDataDAO;
import com.luigivampa92.ingots.springmvc.auth.data.model.UserEmailModel;
import com.luigivampa92.ingots.springmvc.auth.mail.RegistrationConfirmMailSender;
import com.luigivampa92.ingots.springmvc.auth.model.RegistrationConfirmRequest;
import com.luigivampa92.ingots.springmvc.error.BusinessException;
import com.luigivampa92.ingots.springmvc.error.ErrorType;
import com.luigivampa92.ingots.springmvc.auth.model.RegistrationRequestRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserRegistrationService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDataDAO userDAO;

    @Autowired
    private RegistrationConfirmMailSender confirmMailSender;

    @Value("${email.confirm.url}")
    private String emailConformationUrl;

    public void initiateRegistration(RegistrationRequestRequest request) throws BusinessException {
        checkIfAlreadyRegistered(request.getLogin(), request.getEmail());

        String encryptedPassword = passwordEncoder.encode(request.getPassword());
        String emailConformationCode = UUID.randomUUID().toString();

        userDAO.createNewUser(request.getLogin(), encryptedPassword, request.getEmail(), emailConformationCode);
        String url = emailConformationUrl + "?login=" + request.getLogin() + "&conformationCode=" +emailConformationCode;
        confirmMailSender.sendEmail(request.getEmail(), url);
    }

    public void confirmRegistration(RegistrationConfirmRequest request) throws BusinessException {
        List<UserEmailModel> list = userDAO.getByUsername(request.getLogin());

        for (UserEmailModel model: list) {
            if (request.getConformationCode().equals(model.getEmailConformationCode())) {
                userDAO.updateUserEnabled(request.getLogin(), true);
                return;
            }
        }

        throw new BusinessException(ErrorType.USER_CONFORMATION_ERROR);
    }

    private void checkIfAlreadyRegistered(String login, String email) throws BusinessException {
        List<UserEmailModel> list = userDAO.getByUsernameOrEmail(login, email);

        for (UserEmailModel model: list) {
            if ((login.equals(model.getUsername()) ^ email.equals(model.getEmail())) || model.getEmailConformationCode() == null) {
                throw new BusinessException(ErrorType.USER_ALREADY_REGISTERED);
            }
        }
    }
}
