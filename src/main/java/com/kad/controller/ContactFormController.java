package com.kad.controller;

import com.kad.entity.ContactForm;
import com.kad.service.ContactFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class ContactFormController {

    @Autowired
    private ContactFormService cfe;

    @PostMapping("/send")
    public ResponseEntity<?> sendMessage(@RequestBody ContactForm cf){
        cfe.SendMessage(cf);
        return new ResponseEntity<>("We'll Connect You Soon...!", HttpStatus.OK);
    }
}
