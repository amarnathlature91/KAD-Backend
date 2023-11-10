package com.kad.utils;

import com.kad.entity.ContactForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.ByteArrayInputStream;

public class EmailUtils {
    @Autowired
    private JavaMailSender ms ;

    public void sendEmail(ContactForm cf) {

        String body="Contact Information\n" +
                "        Name: "+cf.getName()+"\n" +
                "        Designation: "+cf.getDesignation()+"\n" +
                "        Company Name: "+cf.getCompanyName()+"\n" +
                "        Phone: "+cf.getPhone()+"\n" +
                "        Email:" +cf.getEmail()+"\n" +
                "        Message: "+cf.getMessage()+"\n";

       try {
           SimpleMailMessage message = new SimpleMailMessage();
           message.setFrom(cf.getEmail());
           message.setTo("amarnathlature9130@gmail.com");
           message.setText(body);
           ms.send(message);


       }catch (Exception e){
           e.printStackTrace();
       }
    }

    public void sendEmailWithAttachment(String jobTitle,String description,
                                        byte[] pdfBytes, String pdfFileName,
                                        String fullName, String experience,
                                        String skills) throws MessagingException {

        String body=
                "For Postition = "+jobTitle+","
                +"Full Name = "+fullName+","
                +"Skills = "+skills+","
                +"Experience = "+experience+","
                +"Description = "+description;


        MimeMessage message = ms.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo("amarnathlature9130@gmail.com");
        helper.setSubject("About Job Application");
        helper.setText(body);
        helper.addAttachment(pdfFileName, () -> new ByteArrayInputStream(pdfBytes));

        ms.send(message);
    }
}
