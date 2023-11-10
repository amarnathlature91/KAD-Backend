package com.kad.service.Impl;

import com.kad.entity.ContactForm;
import com.kad.repository.ContactFormRepository;
import com.kad.service.ContactFormService;
import com.kad.utils.EmailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactFormServiceImpl implements ContactFormService {

    @Autowired
    private ContactFormRepository cfrp;

    @Autowired
    private EmailUtils emu;


    @Override
    public void SendMessage(ContactForm cf) {
        emu.sendEmail(cf);
    }
}
