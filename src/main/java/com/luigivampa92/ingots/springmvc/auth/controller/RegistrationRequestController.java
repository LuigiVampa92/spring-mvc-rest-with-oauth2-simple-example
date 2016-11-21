package com.luigivampa92.ingots.springmvc.auth.controller;

import com.luigivampa92.ingots.springmvc.auth.model.RegistrationConfirmRequest;
import com.luigivampa92.ingots.springmvc.auth.model.RegistrationConfirmResponse;
import com.luigivampa92.ingots.springmvc.error.BusinessException;
import com.luigivampa92.ingots.springmvc.auth.model.RegistrationRequestRequest;
import com.luigivampa92.ingots.springmvc.auth.model.RegistrationRequestResponse;
import com.luigivampa92.ingots.springmvc.auth.service.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "register/", produces = MediaType.APPLICATION_JSON_VALUE)
public class RegistrationRequestController {

    @Autowired
    private UserRegistrationService registrationService;

    @RequestMapping(value = {"request", "request/"}, method = RequestMethod.POST)
    @ResponseBody
    public RegistrationRequestResponse requestRegistration(@RequestBody RegistrationRequestRequest request) throws BusinessException {
        registrationService.initiateRegistration(request);
        return new RegistrationRequestResponse();
    }

    @RequestMapping(value = {"confirm", "confirm/"}, method = RequestMethod.GET)
    @ResponseBody
    public RegistrationConfirmResponse confirmRegistration(RegistrationConfirmRequest request) throws BusinessException {
        registrationService.confirmRegistration(request);
        return new RegistrationConfirmResponse();
    }
}
