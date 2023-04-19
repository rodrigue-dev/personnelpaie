package com.example.gpaie.Service;

import com.example.gpaie.Model.EmailDetails;

public interface MailService {
    String sendMail(EmailDetails emailDetails);
    String sendMailAttachement(EmailDetails emailDetails);
}
