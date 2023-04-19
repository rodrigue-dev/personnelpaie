package com.example.gpaie.Service.Impl;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.gpaie.Model.EmailDetails;
import com.example.gpaie.Service.MailService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
@Service
public class MailServiceImpl implements MailService{

    @Value("${files.path}")
    private String filesPath;
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;
    @Override
    public String sendMail(EmailDetails emailDetails) {
    // Creating a mime message
    MimeMessage mimeMessage = javaMailSender.createMimeMessage();
    MimeMessageHelper mimeMessageHelper;

    try {

        mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom(sender);
        mimeMessageHelper.setTo(emailDetails.getRecipient());
        mimeMessageHelper.setText(emailDetails.getMsgBody());
        mimeMessageHelper.setSubject(
            emailDetails.getSubject());

        // Sending the mail
        javaMailSender.send(mimeMessage);
        return "Mail sent Successfully";
    }

    // Catch block to handle MessagingException
    catch (MessagingException e) {

        // Display message when exception occurred
        return "Error while sending mail!!!";
    }
    }

    @Override
    public String sendMailAttachement(EmailDetails emailDetails) {
        // Creating a mime message
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try {

            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(emailDetails.getRecipient());
            mimeMessageHelper.setText(emailDetails.getMsgBody());
            mimeMessageHelper.setSubject(
                emailDetails.getSubject());

            // Adding the attachment
            FileSystemResource file = new FileSystemResource(
                    new File(emailDetails.getAttachment()));

            mimeMessageHelper.addAttachment(
                    file.getFilename(), file);

            // Sending the mail
            javaMailSender.send(mimeMessage);
            return "Mail sent Successfully";
        }

        // Catch block to handle MessagingException
        catch (MessagingException e) {

            // Display message when exception occurred
            return "Error while sending mail!!!";
        }
    }
    
}
