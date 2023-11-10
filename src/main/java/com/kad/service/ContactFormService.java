package com.kad.service;

import com.kad.entity.ContactForm;
import com.kad.repository.ContactFormRepository;
import com.kad.utils.EmailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


public interface ContactFormService {

    public void SendMessage(ContactForm cf);

}
